package org.letokba.lexe.calculate;

import org.letokba.lexe.Symbol;
import org.letokba.lexe.SymbolTree;
import org.letokba.lexe.Token;
import org.letokba.lexe.TokenHelp;
import org.letokba.lexe.TreeNode;

/**
 * @author Wait
 * @date 2020/12/25
 */
public class NumberCalculator implements Calculator<Double> {

    @Override
    public Double calculated(SymbolTree tree) {
        postOrder(tree.getRoot());
        return (Double) tree.getRoot().flag.getData();
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
                a = castNumber(p.leftChild.flag);
                b = castNumber(p.rightChild.flag);
            }catch (NullPointerException e) {
                throw new IllegalArgumentException("expression error. please check!");
            }

            Token operator = p.flag.getToken();
            double ans = TokenHelp.operate(operator, a, b);
            p.flag = new Symbol(Token.num, ans);
        }

        if (p.flag.isLeftBracket()) {
            p.flag = p.rightChild.flag;
        }
    }

    private double castNumber(Symbol symbol) {
        Object data = symbol.getData();
        if(data instanceof Double) {
            return (Double)data;
        }
        if(data instanceof String) {
            return Double.parseDouble((String)data);
        }
        throw new IllegalArgumentException(data + " is not number");
    }

}
