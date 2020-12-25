package org.letokba.lexe.analyse;

import org.letokba.lexe.Symbol;
import org.letokba.lexe.SymbolQueue;
import org.letokba.lexe.Token;
import org.letokba.lexe.TokenHelp;

/**
 * @author Wait
 * @date 2020/12/25
 */
public class AssignExpressAnalyzer extends AbstractAnalyzer{



    @Override
    public SymbolQueue analyzed(String text) {
        return null;
    }


    public int unsafeMoreAnalyzed(String expression, SymbolQueue queue) {
        int i = 0;
        char[] array = expression.toCharArray();

        while (i < array.length) {
            int j = tryMatchDigit(array, i);
            if(j > i) {
                queue.add(new Symbol(Token.num, expression.substring(i, j)));
                i = j;
            }
            j = tryMatchVariableName(array, i);
            if(j > i) {
                queue.add(new Symbol(Token.letter, expression.substring(i, j)));
                i = j;
            }
            if(i >= array.length) {
                return i;
            }
            Token token = TokenHelp.queryToken(array[i]);
            queue.add(new Symbol(token, array[i]));
            i++;
        }

        return i;
    }


    private int tryMatchVariableName(char[] array, int i) {
        if(i >= array.length || !isLetterOrUnderLine(array[i])) {
            return i;
        }
        i++;
        while (i < array.length) {
            if(!Character.isJavaIdentifierPart(array[i])) {
                break;
            }
            i++;
        }
        return i;
    }

    private boolean isUnderline(char ch) {
        return ch == '_';
    }



    private boolean isLetterOrUnderLine(char ch) {
        return isUnderline(ch) || Character.isJavaIdentifierPart(ch);
    }

}
