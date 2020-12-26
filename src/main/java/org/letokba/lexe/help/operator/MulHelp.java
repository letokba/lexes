package org.letokba.lexe.help.operator;

import org.letokba.lexe.core.Symbol;
import org.letokba.lexe.core.Token;
import org.letokba.lexe.help.SymbolHelp;

/**
 * @author Wait
 * @date 2020/12/26
 */
public class MulHelp  extends OperatorHelp {


    @Override
    public Object binary(Symbol pre, Symbol next) {
        double a = (double) pre.getData();
        double b = (double) next.getData();
        return a * b;
    }
}
