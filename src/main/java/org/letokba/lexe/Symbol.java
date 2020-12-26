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
        return TokenHelp.isOperationalToken(token);
    }

    public boolean isLeftBracket() {
        return token == Token.lBracket;
    }

    public boolean isRightBracket() {
        return token == Token.rBracket;
    }

    public boolean isVariable() {
        return token == Token.letter;
    }

    public boolean lowTo(Symbol symbol) {
        return this.token.priority < symbol.token.priority;
    }

    @Override
    public String toString() {
        return "[" + data + " : " + token.priority + "]";
    }



}
