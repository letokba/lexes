package org.letokba.lexe;

import static org.letokba.lexe.Token.*;


/**
 * @author Wait
 * @date 2020/12/25
 */
public class CheckerFactory {
    public static Checker operationChecker =  new Operator();
    public static Checker variableChecker =  new Variable();
    public static Checker numberChecker =  new Number();
    public static Checker equalChecker =  new Equal();
    public static Checker leftBracketChecker =  new LeftBracket();
    public static Checker rightBracketChecker =  new RightBracket();





    private static class Operator implements Checker {
        @Override
        public Boolean checkAfter(Token token) {
            return token == letter || token == num || token == lBracket;
        }
    }

    private static class Variable implements Checker {

        @Override
        public Boolean checkAfter(Token token) {
            return TokenHelp.isOperationalToken(token) || token == rBracket || token == equal;
        }
    }

    private static class Number implements Checker {
        @Override
        public Boolean checkAfter(Token token) {
            return TokenHelp.isOperationalToken(token) || token == rBracket;
        }
    }

    private static class LeftBracket implements Checker {
        @Override
        public Boolean checkAfter(Token token) {
            return token == letter || token == num || token == lBracket;
        }
    }

    private static class RightBracket implements Checker {
        @Override
        public Boolean checkAfter(Token token) {
            return TokenHelp.isOperationalToken(token) || token == rBracket;
        }
    }

    private static class Equal implements Checker {
        @Override
        public Boolean checkAfter(Token token) {
            return token == letter || token == num || token == lBracket;
        }
    }


}
