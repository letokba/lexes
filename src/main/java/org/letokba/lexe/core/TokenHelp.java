package org.letokba.lexe.core;

import org.letokba.lexe.help.Checker;
import org.letokba.lexe.help.SymbolAction;
import org.letokba.lexe.help.SymbolHelpFactory;

import java.util.EnumSet;
import java.util.Optional;

import static org.letokba.lexe.core.Token.*;

/**
 * @author Wait
 * @date 2020/12/25
 */
public class TokenHelp {

    static EnumSet<Token> operationSet = EnumSet.of(add, sub, mul, div, mod);
    static EnumSet<Token> tokens = EnumSet.allOf(Token.class);
    static final SymbolHelpFactory FACTORY = new SymbolHelpFactory();



    public static boolean isOperationalToken(Token token) {
        return operationSet.contains(token);
    }


    public static Token queryToken(final char ch) {
        Optional<Token> first = tokens.stream()
                .filter((item) -> item.sign == ch)
                .findFirst();
        if (first.isPresent()) {
            return first.get();
        }

        if (Character.isDigit(ch)) {
            return Token.num;
        } else if (Character.isLetter(ch)) {
            return Token.letter;
        }
        throw new IllegalArgumentException("illegal symbol: " + ch);
    }

    public static boolean isDefinedToken(final char ch) {
        return queryToken(ch) != null;
    }

    public static Checker getChecker(Symbol symbol) {
        assert symbol != null;
        return getChecker(symbol.getToken());
    }

    public static Checker getChecker(Token token) {
        return FACTORY.getHelp(token);
    }

    public static SymbolAction getAction(Token token) {
        return FACTORY.getHelp(token);
    }

}
