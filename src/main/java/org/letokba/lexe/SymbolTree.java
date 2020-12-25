package org.letokba.lexe;

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
        TreeNode parent;

        public TreeNode(Symbol symbol) {
            this.flag = symbol;
        }


        public void setLeftChild(TreeNode leftChild) {
            this.leftChild = leftChild;
            if (leftChild == null) {
                return;
            }
            leftChild.parent = this;
        }


        public void setRightChild(TreeNode rightChild) {
            this.rightChild = rightChild;
            if (rightChild == null) {
                return;
            }
            rightChild.parent = this;
        }

        @Override
        public String toString() {
            return "(" + flag + ")";
        }
    }

    private SymbolTree() {
    }


    static public SymbolTree builder(SymbolQueue queue) {
        SymbolTree tree = new SymbolTree();
        tree.root = tree.build(queue);
        return tree;
    }


    private TreeNode build(SymbolQueue queue) {
        return subBuild(queue);

    }


    private TreeNode subBuild(SymbolQueue queue) {
        TreeNode root = new TreeNode(new Symbol(Token.lBracket, 0));
        root.leftChild = new TreeNode(new Symbol(Token.rBracket, 0));
        TreeNode p = root;
        while (queue.size() > 0) {
            Symbol symbol = queue.poll();
            Symbol rootSymbol = root.flag;
            TreeNode node = new TreeNode(symbol);

            if (symbol.isRightBracket()) {
                root = root.parent;
                if(root.leftChild != null) {
                    throw new IllegalArgumentException("')' is more");
                }
                root.setLeftChild(node);
                continue;
            }
            if (symbol.isLeftBracket() || symbol.isNum()) {
                // we think the "(...)" is same with number
                // only one, the "(" must be the root to transmit the next symbol like number or "("
                // but number is mostly the rightChild unless not that it is the first by expression
                if (root.rightChild != null) {
                    root.rightChild.setRightChild(node);
                } else {
                    root.setRightChild(node);
                }
                if (rootSymbol.isLeftBracket() || symbol.isLeftBracket()) {
                    // root point move to the next 'root' and move down in tree.
                    root = node;
                }
                continue;
            }
            if (symbol.isOperation()) {
                TreeNode parent = root.parent;
                // like as root is "+", node is "*"
                // node should exchange root's rightChild not root
                // because "*" and "/” must be executed before "+" or "-"
                // After building, the calculating order is from down to up.
                if (rootSymbol.isOperation() && symbol.lowTo(rootSymbol)) {
                    node.setLeftChild(root.rightChild);
                    root.setRightChild(node);
                } else {
                    parent.setRightChild(node);
                    node.setLeftChild(root);
                    // here, the root point also move to the new 'root' but not move in tree.
                    // root = node;
                    root = parent.rightChild;
                }
            }
        }


        return p.rightChild;
    }

    public double decode() {
        postOrder(this.root);
        return (double) this.root.flag.getData();
    }


    public void postOrder(TreeNode p) {
        if (p == null || (p.leftChild == null && p.rightChild == null)) {
            return;
        }

        postOrder(p.leftChild);
        postOrder(p.rightChild);

        if (p.flag.isOperation()) {
            double a, b;
            try{
                a = (double) p.leftChild.flag.getData();
                b = (double) p.rightChild.flag.getData();
            }catch (NullPointerException e) {
                throw new IllegalArgumentException("expression error. please check!");
            }

            Token operator = p.flag.getToken();
            double ans = TokenHelp.operate(operator, a, b);
            p.flag = new Symbol(Token.num, ans);
        }

        if (p.flag.isLeftBracket()) {
            if(p.leftChild == null){
                throw new IllegalArgumentException("lack the ')'");
            }
            p.flag = p.rightChild.flag;
        }
    }




}
