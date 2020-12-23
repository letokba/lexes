/**
 * @author Wait
 * @date 2020/12/23
 */
public class SymbolTree {
    private TreeNode root;
    private TreeNode subRoot;

    private static class TreeNode {
        Symbol flag;
        TreeNode leftChild;
        TreeNode rightChild;
        TreeNode prent;
        public TreeNode(Symbol symbol) {
            this.flag = symbol;
        }

        public void setPrent(TreeNode prent) {
            this.prent = prent;
        }

        public void setLeftChild(TreeNode leftChild) {
            this.leftChild = leftChild;
            leftChild.prent = this;
        }


        public void setRightChild(TreeNode rightChild) {
            this.rightChild = rightChild;
            rightChild.prent = this;
        }

        @Override
        public String toString() {
            return "(" + flag + ")";
        }
    }

    private SymbolTree() { }

    private void initialize() {
        root = new TreeNode(new Symbol(Token.lBracket, null));
        subRoot = root;
    }

    static public SymbolTree builder(SymbolQueue queue) {
        SymbolTree tree = new SymbolTree();
        tree.build(queue);
        return tree;
    }

    private void build(SymbolQueue queue) {
        initialize();

        while (queue.size() > 0) {
            Symbol symbol = queue.poll();
            TreeNode node = new TreeNode(symbol);




            if(symbol.isRightBracket()) {
                //回到 '('
                subRoot = subRoot.prent;
                // '(' 的右节点一定是 ')'
                subRoot.setRightChild(node);
                // 回到对应的左括号的前一个运算符位置
                subRoot = subRoot.prent;
            }

            if(symbol.isOperation()) {
                if(symbol.lowTo(subRoot.flag)) {
                    node.setLeftChild(subRoot.rightChild);
                    subRoot.setRightChild(node);
                }else {
                    TreeNode parent = subRoot.prent;
                    subRoot.prent = null;
                    node.setLeftChild(subRoot);
                    parent.setLeftChild(node);
                    subRoot = node;
                }
            }

            if(symbol.isNum() || symbol.isLeftBracket()) {
                if(subRoot.flag.isLeftBracket()) {
                    subRoot.setLeftChild(node);
                    subRoot = node;
                } else if(subRoot.flag.isOperation()) {
                    TreeNode t = this.subRoot;
                    while (t.rightChild != null) {
                        t = t.rightChild;
                    }
                    t.setRightChild(node);
                }
            }
        }
        this.root.setRightChild(new TreeNode(new Symbol(Token.rBracket, null)));
    }

    public double decode() {
        postOrder(this.root);
        return (double) this.root.flag.getData();
    }


    public void postOrder(TreeNode p) {
        if(p == null || (p.leftChild == null && p.rightChild == null)) {
            return;
        }

        postOrder(p.leftChild);
        postOrder(p.rightChild);

        if(p.flag.isOperation()) {
            double a = (double) p.leftChild.flag.getData();
            double b = (double) p.rightChild.flag.getData();
            double ans = operated(a, b, p.flag.getToken());
            p.flag = new Symbol(Token.num, ans);
        }

        if(p.flag.isLeftBracket()) {
            p.flag = p.leftChild.flag;
        }
    }



    public double operated(double a, double b, Token token) {
        switch (token) {
            case add: return a + b;
            case sub: return a - b;
            case mul: return a * b;
            case dev: return a / b;
            default: throw new RuntimeException("token is not support");
        }
    }

}
