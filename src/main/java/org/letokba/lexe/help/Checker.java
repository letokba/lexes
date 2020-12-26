package org.letokba.lexe.help;


import org.letokba.lexe.Symbol;
import org.letokba.lexe.Token;

/**
 * @author Wait
 * @date 2020/12/25
 */
public interface Checker  {

    /**
     * checking the next Symbol is or not allow in the current Symbol
     * @param after: next Symbol's Token
     * @return if allowing is true, else is false
     */
    boolean checkAfter(Token after);

}
