package org.letokba.lexe;

/**
 * @author Wait
 * @date 2020/12/23
 */
public class Symbol {
    private final Token token;
    private final Object data;

    public Symbol(Token token, Object data) {
        this.token = token;
        this.data = data;
    }

    public Token getToken() {
        return token;
    }

    public Object getData() {
        return data;
    }

    public boolean isNum() {
        return token == Token.num;
    }

    public boolean isOperation() {
        return token == Token.add || token == Token.sub || token == Token.mul || token == Token.dev;
    }

    public boolean isLeftBracket() {
        return token == Token.lBracket;
    }

    public boolean isRightBracket() {
        return token == Token.rBracket;
    }

    public boolean lowTo(Symbol symbol) {
        return this.token.priority < symbol.token.priority;
    }

    @Override
    public String toString() {
        return "[" + data + " : " + token.priority + "]";
    }
}
