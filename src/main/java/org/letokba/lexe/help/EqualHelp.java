package org.letokba.lexe.help;

import org.letokba.lexe.core.Token;

import static org.letokba.lexe.core.Token.*;

/**
 * @author Wait
 * @date 2020/12/26
 */
public class EqualHelp implements SymbolHelp{

    @Override
    public boolean checkAfter(Token token) {
        return token == letter || token == num || token == lBracket;

    }
}
