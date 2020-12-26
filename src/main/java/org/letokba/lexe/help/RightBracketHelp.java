package org.letokba.lexe.help;

import org.letokba.lexe.Symbol;
import org.letokba.lexe.Token;
import org.letokba.lexe.TokenHelp;

import static org.letokba.lexe.Token.rBracket;

/**
 * @author Wait
 * @date 2020/12/26
 */
public class RightBracketHelp implements SymbolHelp {

    @Override
    public boolean checkAfter(Token token) {
        return TokenHelp.isOperationalToken(token) || token == rBracket;
    }
}
