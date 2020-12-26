package org.letokba.lexe.analyse;

import org.letokba.lexe.StringUtils;
import org.letokba.lexe.SymbolQueue;
import org.letokba.lexe.Token;
import org.letokba.lexe.TokenHelp;
import org.letokba.lexe.Symbol;

/**
 * @author Wait
 * @date 2020/12/23
 *
 * 分析仅包含四则运算的表达式
 * 1 + (2 + 2 * (3.25 - 0.25))
 *
 */
public class OperationalExpressionAnalyzer extends AbstractAnalyzer{

    @Override
    public SymbolQueue analyzed(String expression) {
        expression = StringUtils.clearBlank(expression);
        SymbolQueue queue = new SymbolQueue();
        int i = unsafeAnalyzed(expression, queue);
        if(i < expression.length()) {
            throw new RuntimeException("expression is error where index = " + i + ", " + expression);
        }
        check(queue);
        return queue;
    }



    private int unsafeAnalyzed(String expression, SymbolQueue queue){
        int i = 0;
        char[] array = expression.toCharArray();

        while (i < array.length) {
            int j = tryMatchDigit(array, i);
            Object data;
            Token token;
            if(j == i) {
                token = queryToken(array[j]);
                data = array[j];
                i++;
            }else {
                token = Token.num;
                data = expression.substring(i, j);
                i = j;
            }
            queue.add(new Symbol(token, data));

        }
        return i;
    }

    private Token queryToken(char ch) {
        Token token = TokenHelp.queryToken(ch);
        if(token == Token.letter || token == Token.equal) {
            throw new IllegalArgumentException("illegal symbol: " + ch);
        }
        return token;
    }

}
