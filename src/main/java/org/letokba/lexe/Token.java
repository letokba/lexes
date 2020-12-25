package org.letokba.lexe;

import java.util.EnumSet;

/**
 * @author Wait
 * @date 2020/12/23
 */
public enum Token {
    /**
     *
     */
    equal(16, '='),
    dot(8, '.'),
    lBracket(4, '('),
    rBracket(4, ')'),
    add(2, '+'),
    sub(2, '-'),
    mul(1, '*'),
    div(1, '/'),
    mod(1, '%'),
    num(0),
    letter(0);

    int priority;

    char sign;

    Token(int priority, char sign) {
        this.priority = priority;
        this.sign = sign;
    }

    Token(int priority) {
        this.priority = priority;
    }



}
