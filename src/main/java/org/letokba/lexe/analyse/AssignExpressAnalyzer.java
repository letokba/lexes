package org.letokba.lexe.analyse;

import org.letokba.lexe.core.Symbol;
import org.letokba.lexe.core.SymbolQueue;
import org.letokba.lexe.core.Token;
import org.letokba.lexe.core.TokenHelp;
import org.letokba.lexe.util.StringUtils;

/**
 * @author Wait
 * @date 2020/12/25
 */
public class AssignExpressAnalyzer extends AbstractAnalyzer{



    @Override
    public SymbolQueue analyzed(String singeLine) {
        SymbolQueue queue = new SymbolQueue();
        singeLine = StringUtils.clearMoreWhitespace(singeLine);
        singeLine = StringUtils.strip(singeLine);
        int i = unsafeMoreAnalyzed(singeLine, queue);
        check(queue);
        return queue;
    }


    public int unsafeMoreAnalyzed(String expression, SymbolQueue queue) {
        int i = 0;
        char[] array = expression.toCharArray();

        while (i < array.length) {
            if(Character.isWhitespace(array[i])) {
                i++;
                continue;
            }

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
            char ch = array[i];
            if(!Character.isWhitespace(ch)) {
                Token token = TokenHelp.queryToken(ch);
                queue.add(new Symbol(token, ch));
            }
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
