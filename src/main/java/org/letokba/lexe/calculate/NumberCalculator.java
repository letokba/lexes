package org.letokba.lexe.calculate;

import org.letokba.lexe.core.Symbol;
import org.letokba.lexe.core.SymbolTree;
import org.letokba.lexe.core.Token;
import org.letokba.lexe.core.TokenHelp;
import org.letokba.lexe.core.TreeNode;

/**
 * @author Wait
 * @date 2020/12/25
 */
public class NumberCalculator implements Calculator<Double> {
    @Override
    public Double calculated(SymbolTree tree) {
        postOrder(tree.getRoot());
        return transformValue(tree.getRoot().flag);
    }

    protected void postOrder(TreeNode p) {
        if (p == null || (p.leftChild == null && p.rightChild == null)) {
            return;
        }
        postOrder(p.leftChild);
        postOrder(p.rightChild);
        p.flag = action(p);
    }

    private void updateValue(Symbol symbol) {
        Double data = transformValue(symbol);
        symbol.setData(data);
    }

    @Override
    public Double transformValue(Symbol symbol) {
        Object data = symbol.getData();
        if (data instanceof Double) {
            return (Double) data;
        }
        if (data instanceof String) {
            return Double.parseDouble((String) data);
        }
        throw new IllegalArgumentException(data + " is not number");
    }


    private Symbol action(TreeNode p) {
        Token token = p.flag.getToken();
        if (p.flag.isOperation() && ! p.flag.isEqual()) {
            updateValue(p.leftChild.flag);
            updateValue(p.rightChild.flag);
        }
        return TokenHelp.getAction(token).binary(p.leftChild.flag, p.rightChild.flag);
    }

}
