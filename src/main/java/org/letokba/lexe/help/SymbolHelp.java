package org.letokba.lexe.help;

import org.letokba.lexe.core.Symbol;

/**
 * @author Wait
 * @date 2020/12/26
 */
public interface SymbolHelp extends Checker, SymbolAction{

    @Override
    default Object binary(Symbol pre, Symbol next) {
        return null;
    }

    @Override
    default Object unary(Symbol next) {
        return null;
    }
}
