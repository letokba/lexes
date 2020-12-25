package org.letokba.lexe;

import java.util.EnumSet;
import java.util.Optional;

import static org.letokba.lexe.Token.*;
/**
 * @author Wait
 * @date 2020/12/25
 */
public class TokenHelp {
    static EnumSet<Token> operationSet = EnumSet.of(add, sub, mul, div, mod);
    static EnumSet<Token> tokens = EnumSet.allOf(Token.class);


    static boolean isOperationalToken(Token token) {
        return operationSet.contains(token);
    }


    static Token queryToken(final char ch) {
        Optional<Token> first =  tokens.stream()
                        .filter((item) -> item.sign == ch)
                        .findFirst();
        if(first.isPresent()) {
            return first.get();
        }

        if(Character.isDigit(ch)) {
            return Token.num;
        }else if(Character.isLetter(ch)) {
            return Token.letter;
        }
        throw new IllegalArgumentException("illegal symbol: " + ch);
    }
}
