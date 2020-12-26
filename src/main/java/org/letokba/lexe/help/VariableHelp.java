package org.letokba.lexe.help;

import org.letokba.lexe.Token;
import org.letokba.lexe.TokenHelp;

import static org.letokba.lexe.Token.equal;
import static org.letokba.lexe.Token.rBracket;

/**
 * @author Wait
 * @date 2020/12/26
 */
public class VariableHelp implements SymbolHelp {

    @Override
    public boolean checkAfter(Token token) {
        return TokenHelp.isOperationalToken(token) || token == rBracket || token == equal;
    }
}
