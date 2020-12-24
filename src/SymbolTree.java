/**
 * @author Wait
 * @date 2020/12/23
 */
public class SymbolTree {
    private TreeNode root;

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
            if(leftChild == null) {
                return;
            }
            leftChild.prent = this;
        }


        public void setRightChild(TreeNode rightChild) {
            this.rightChild = rightChild;
            if(rightChild == null) {
                return;
            }
            rightChild.prent = this;
        }

        @Override
        public String toString() {
            return "(" + flag + ")";
        }
    }

    private SymbolTree() { }



    static public SymbolTree builder(SymbolQueue queue) {
        SymbolTree tree = new SymbolTree();
        tree.root = tree.build(queue);
        return tree;
    }


    private TreeNode build(SymbolQueue queue) {
        TreeNode p = new TreeNode(new Symbol(Token.lBracket, null));
        TreeNode root = p;
        while (! queue.isEmpty()) {
            Symbol symbol = queue.poll();
            TreeNode node  = new TreeNode(symbol);
            if(symbol.isLeftBracket()) {
                root.setLeftChild(node);
                root = node;
            }else if (symbol.isRightBracket()) {
                root.setRightChild(node);
                root = root.prent;
            }else {
                TreeNode t  = subBuild(symbol, queue);
                if(root.leftChild != null) {
                    t.setLeftChild(root.leftChild);
                }
                root.setLeftChild(t);
            }
        }
        return p.leftChild;

    }


    private TreeNode subBuild(Symbol numSymbol, SymbolQueue queue) {
        TreeNode root = new TreeNode(numSymbol);
        while (queue.size() > 0) {
            Symbol symbol = queue.peek();
            TreeNode node = new TreeNode(symbol);
            if(symbol.isOperation()) {
                if(symbol.lowTo(root.flag)) {
                    node.setLeftChild(root.rightChild);
                    root.setRightChild(node);
                }else {
                    node.setLeftChild(root);
                    root = node;
                }
            }else if(symbol.isNum()) {
                TreeNode t = root;
                while (t.rightChild != null) {
                    t = t.rightChild;
                }
                t.setRightChild(node);
            }else {
                break;
            }
            queue.poll();
        }
        return root;
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
