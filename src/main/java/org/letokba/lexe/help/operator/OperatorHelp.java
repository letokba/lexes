package org.letokba.lexe.help.operator;

import org.letokba.lexe.core.Token;
import org.letokba.lexe.help.SymbolHelp;

import static org.letokba.lexe.core.Token.*;

/**
 * @author Wait
 * @date 2020/12/26
 */
public class OperatorHelp implements SymbolHelp {

    @Override
    public boolean checkAfter(Token token) {
        return token == letter || token == num || token == lBracket;
    }
}
