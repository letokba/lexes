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
