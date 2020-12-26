package org.letokba.lexe.help;


import org.letokba.lexe.Symbol;
import org.letokba.lexe.Token;

/**
 * @author Wait
 * @date 2020/12/25
 */
public interface Checker  {

    public  boolean checkAfter(Token after);

}
