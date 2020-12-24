package org.letokba.lexe;
/**
 * @author Wait
 * @date 2020/12/23
 */
public class Analyzer {

    public SymbolQueue analyzed(String expression) {
        expression = clearBlank(expression);
        SymbolQueue queue = new SymbolQueue();
        int i = unsafeAnalyzed(expression, queue);
        if(i < expression.length()) {
            throw new RuntimeException("expression is error where index = " + i + ", " + expression);
        }
        return queue;
    }

    public String clearBlank(String s) {
        StringBuilder builder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if(Character.isWhitespace(c)){
               continue;
            }
            builder.append(c);
        }
        return builder.toString();
    }

    private int unsafeAnalyzed(String expression, SymbolQueue queue){
        int i = 0;
        char[] array = expression.toCharArray();

        while (i < array.length) {
            int j = tryMatchNumber(array, i);
            Object data;
            Token token;
            try{
                if(j == i) {
                    token = transformToken(array[j]);
                    data = array[j];
                    i++;
                }else {
                    token = Token.num;
                    data = Double.parseDouble(expression.substring(i, j));
                    i = j;
                }
                queue.add(new Symbol(token, data));
            }catch (NumberFormatException e) {
                return i;
            }
        }
        return i;
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








    public boolean isOperationalToken(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }



    public Token transformToken(char c) {
        switch (c) {
            case '+': return Token.add;
            case '-': return Token.sub;
            case '*': return Token.mul;
            case '/': return Token.dev;
            case '(': return Token.lBracket;
            case ')': return Token.rBracket;
            default: return Token.num;
        }
    }



}
