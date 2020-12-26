package org.letokba.lexe.help.operator;

import org.letokba.lexe.core.Symbol;


/**
 * @author Wait
 * @date 2020/12/26
 */
public class DivHelp extends OperatorHelp {



    @Override
    public Object binary(Symbol pre, Symbol next) {
        double a = (double) pre.getData();
        double b = (double) next.getData();
        return a / b;
    }
}
