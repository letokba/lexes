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

    private void postOrder(TreeNode p) {
        if (p == null || (p.leftChild == null && p.rightChild == null)) {
            return;
        }

        postOrder(p.leftChild);
        postOrder(p.rightChild);

        if (p.flag.isOperation()) {
            double a, b;
            try{
                updateValue(p.leftChild.flag);
                updateValue(p.rightChild.flag);
            }catch (NullPointerException e) {
                throw new IllegalArgumentException("expression error. please check!");
            }

            Object ans = action(p);

            p.flag = new Symbol(Token.num, ans);
        }

        if (p.flag.isLeftBracket()) {
            p.flag = p.rightChild.flag;
        }
    }

    private void updateValue(Symbol symbol) {
        Double data = transformValue(symbol);
        symbol.setData(data);
    }

    @Override
    public Double transformValue(Symbol symbol) {
        Object data = symbol.getData();
        if(data instanceof Double) {
            return (Double) data;
        }
        if(data instanceof String) {
            return Double.parseDouble((String)data);
        }
        throw new IllegalArgumentException(data + " is not number");
    }



    private Object action(TreeNode p) {
        Token token = p.flag.getToken();
        return TokenHelp.getAction(token).binary(p.leftChild.flag, p.rightChild.flag);
    }

}
