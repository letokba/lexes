package org.letokba.lexe;

import org.letokba.lexe.help.Checker;

/**
 * @author Wait
 * @date 2020/12/23
 */
public enum Token {
    /**
     *
     */
    equal(16, '='),
//    dot(8, '.'),
    lBracket(4, '('),
    rBracket(4, ')'),
    add(2, '+'),
    sub(2, '-'),
    mul(1, '*'),
    div(1, '/'),
    mod(1, '%'),
    num(0, '0'),
    letter(0, 'a');

    int priority;

    char sign;

    Checker checker;

    Token(int priority, char sign) {
        this.priority = priority;
        this.sign = sign;
    }

    Token(int priority) {
        this.priority = priority;
    }





}
