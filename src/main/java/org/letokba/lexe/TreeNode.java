package org.letokba.lexe;

/**
 * @author Wait
 * @date 2020/12/25
 */
public class TreeNode {
    public Symbol flag;
    public TreeNode leftChild;
    public TreeNode rightChild;
    public TreeNode parent;

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
