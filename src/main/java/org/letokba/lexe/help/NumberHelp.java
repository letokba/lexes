package org.letokba.lexe.help;

import org.letokba.lexe.core.Token;
import org.letokba.lexe.core.TokenHelp;

import static org.letokba.lexe.core.Token.equal;
import static org.letokba.lexe.core.Token.rBracket;

/**
 * @author Wait
 * @date 2020/12/26
 */
public class NumberHelp implements SymbolHelp {

    @Override
    public boolean checkAfter(Token token) {
        return token != equal && (TokenHelp.isOperationalToken(token) || token == rBracket);

    }
}
