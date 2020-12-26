package org.letokba.lexe.help.operator;

import org.letokba.lexe.core.Symbol;
import org.letokba.lexe.core.Token;


/**
 * @author Wait
 * @date 2020/12/26
 */
public class ModHelp  extends OperatorHelp  {


    @Override
    public Symbol binary(Symbol pre, Symbol next) {
        double a = (double) pre.getData();
        double b = (double) next.getData();
        return new Symbol(Token.num, a % b);

    }
}
