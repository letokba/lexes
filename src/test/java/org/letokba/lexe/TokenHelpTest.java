package org.letokba.lexe;

import org.junit.Test;

import java.util.function.DoubleBinaryOperator;

import static org.junit.Assert.*;

public class TokenHelpTest {


    @Test
    public void Test() {
       double d = TokenHelp.operate(Token.add, 3, 2);
       double c = TokenHelp.operate(Token.mod, 5 , 3);
       System.out.println(d + " " + c);
    }


}