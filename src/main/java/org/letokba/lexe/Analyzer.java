package org.letokba.lexe;
/**
 * @author Wait
 * @date 2020/12/23
 */
public class Analyzer {

    public SymbolQueue analyzed(String expression) {
        expression = StringUtils.clearBlank(expression);
        SymbolQueue queue = new SymbolQueue();
        int i = unsafeAnalyzed(expression, queue);
        if(i < expression.length()) {
            throw new RuntimeException("expression is error where index = " + i + ", " + expression);
        }
        return queue;
    }



    private int unsafeAnalyzed(String expression, SymbolQueue queue){
        int i = 0;
        char[] array = expression.toCharArray();

        while (i < array.length) {
            int j = tryMatchNumber(array, i);
            Object data;
            Token token;
            if(j == i) {
                token = TokenHelp.queryToken(array[j]);
                data = array[j];
                i++;
            }else {
                token = Token.num;
                data = Double.parseDouble(expression.substring(i, j));
                i = j;
            }
            queue.add(new Symbol(token, data));

        }
        return i;
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

    private int tryMatchDigit(char[] array, int j) {
        boolean isDigit = false;
        boolean hasDot = false;
        while(j < array.length){
            if(Character.isDigit(array[j])) {
                j++;
                isDigit = true;
            }
            else if(array[j] == '.' && isDigit && !hasDot) {
                j++;
                isDigit = false;
                hasDot = true;
            }else {
                break;
            }
        }
        return j;
    }



    public int tryMatchNumber(char[] array, int j) {
        boolean isDigit = false;
        while(j < array.length){
            if(Character.isDigit(array[j])) {
                j++;
                isDigit = true;
            }
            else if(array[j] == '.' && isDigit) {
                j++;
                isDigit = false;
            }else {
                break;
            }
        }
        return j;
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
