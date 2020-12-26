package org.letokba.lexe.help;

import org.letokba.lexe.core.Symbol;

/**
 * @author Wait
 * @date 2020/12/26
 */
public interface SymbolAction {

    public  Object binary(Symbol pre, Symbol next);

    public  Object unary(Symbol next);
}
