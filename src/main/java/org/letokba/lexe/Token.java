package org.letokba.lexe;

/**
 * @author Wait
 * @date 2020/12/23
 */
public enum Token {
    equal(16), dot(8), lBracket(4), rBracket(4), add(2), sub(2), mul(1), dev(1), num(0), letter(0);

    int priority;

    Token(int i) {
        this.priority = i;
    }


}
