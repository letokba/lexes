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

        public void setParent(TreeNode parent) {
            this.parent = parent;
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
        TreeNode p = root;
        while (queue.size() > 0) {
            Symbol symbol = queue.poll();
            Symbol rootSymbol = root.flag;
            TreeNode node = new TreeNode(symbol);

            if (symbol.isRightBracket()) {
                root = root.parent;
                root.setRightChild(node);
                continue;
            }

            if (symbol.isLeftBracket() || symbol.isNum()) {
                // we think the "(...)" is same with number
                // only one, the "(" must be the root to transmit the next symbol (number or "(" )
                // but number is mostly the rightChild unless not that it is the first by expression
                if (rootSymbol.isLeftBracket()) {
                    root.setLeftChild(node);
                    root = node;
                } else {
                    if (root.rightChild != null) {
                        root.rightChild.setRightChild(node);
                    } else {
                        root.setRightChild(node);
                    }
                    if (symbol.isLeftBracket()) {
                        root = node;
                    }
                }
                continue;
            }
            if (symbol.isOperation()) {
                TreeNode parent = root.parent;

                if (rootSymbol.isOperation()) {
                    // like root is "+", node is "*"
                    // node should exchange root's rightChild not root
                    // become "*" and "/” must executing before "+" or "-"
                    // After building, the calculating order is down to up.
                    if (symbol.lowTo(rootSymbol)) {
                        node.setLeftChild(root.rightChild);
                        root.setRightChild(node);
                    } else {
                        parent.setLeftChild(node);
                        node.setLeftChild(root);
                        root = node;
                    }
                } else {
                    if (parent.leftChild == root) {
                        parent.setLeftChild(node);
                    } else {
                        parent.setRightChild(node);
                    }
                    node.setLeftChild(root);
                    root = node;
                }

            }

        }
        return p.leftChild;
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
            double a = (double) p.leftChild.flag.getData();
            double b = (double) p.rightChild.flag.getData();
            double ans = operated(a, b, p.flag.getToken());
            p.flag = new Symbol(Token.num, ans);
        }

        if (p.flag.isLeftBracket()) {
            p.flag = p.leftChild.flag;
        }
    }


    public double operated(double a, double b, Token token) {
        switch (token) {
            case add:
                return a + b;
            case sub:
                return a - b;
            case mul:
                return a * b;
            case dev:
                return a / b;
            default:
                throw new RuntimeException("token is not support");
        }
    }

}
