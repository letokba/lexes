package org.letokba.lexe;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Optional;
import java.util.function.DoubleBinaryOperator;

import static org.letokba.lexe.Token.*;

/**
 * @author Wait
 * @date 2020/12/25
 */
public class TokenHelp {

    static EnumSet<Token> operationSet = EnumSet.of(add, sub, mul, div, mod);
    static EnumSet<Token> tokens = EnumSet.allOf(Token.class);
    static EnumMap<Token, DoubleBinaryOperator> operatorEnumMap = new EnumMap<>(Token.class);

    static {
        operatorEnumMap.put(add, new Add());
        operatorEnumMap.put(div, new Div());
        operatorEnumMap.put(sub, new Sub());
        operatorEnumMap.put(mul, new Mul());
        operatorEnumMap.put(mod, new Mod());
    }

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


    static class Add implements DoubleBinaryOperator {
        @Override
        public double applyAsDouble(double left, double right) {
            return left + right;
        }
    }

    static class Sub implements DoubleBinaryOperator {
        @Override
        public double applyAsDouble(double left, double right) {
            return left - right;
        }
    }


    static class Mul implements DoubleBinaryOperator {
        @Override
        public double applyAsDouble(double left, double right) {
            return left * right;
        }
    }

    static class Div implements DoubleBinaryOperator {
        @Override
        public double applyAsDouble(double left, double right) {
            return left / right;
        }
    }

    static class Mod implements DoubleBinaryOperator {
        @Override
        public double applyAsDouble(double left, double right) {
            return left % right;
        }
    }


    public static double operate(Token operator, double a, double b) {
        if (!operatorEnumMap.containsKey(operator)) {
            throw new IllegalArgumentException(operator.sign + "not support bit-operator");
        }
        return operatorEnumMap.get(operator).applyAsDouble(a, b);
    }
}
