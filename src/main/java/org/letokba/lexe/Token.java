package org.letokba.lexe;

import static org.letokba.lexe.CheckerFactory.*;
/**
 * @author Wait
 * @date 2020/12/23
 */
public enum Token {
    /**
     *
     */
    equal(16, '=', equalChecker),
//    dot(8, '.'),
    lBracket(4, '(', leftBracketChecker),
    rBracket(4, ')', rightBracketChecker),
    add(2, '+', operationChecker),
    sub(2, '-', operationChecker),
    mul(1, '*', operationChecker),
    div(1, '/', operationChecker),
    mod(1, '%', operationChecker),
    num(0, '0', numberChecker),
    letter(0, 'a', variableChecker);

    int priority;

    char sign;

    Checker checker;

    Token(int priority, char sign, Checker checker) {
        this.priority = priority;
        this.sign = sign;
    }

    Token(int priority) {
        this.priority = priority;
    }


    public boolean allowAfter(Token token) {
        return this.checker.checkAfter(token);
    }


}
