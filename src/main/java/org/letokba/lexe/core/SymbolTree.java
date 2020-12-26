package org.letokba.lexe.core;

/**
 * @author Wait
 * @date 2020/12/23
 */
public class SymbolTree {
    private TreeNode root;

    public TreeNode getRoot() {
        return root;
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
                root.setLeftChild(node);
                continue;
            }
            if (symbol.isLeftBracket() || symbol.isNum() || symbol.isVariable()) {
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









}
