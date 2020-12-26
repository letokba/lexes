package org.letokba.lexe.help;

import org.letokba.lexe.Token;
import org.letokba.lexe.TokenHelp;

import static org.letokba.lexe.Token.*;
import java.util.EnumMap;

/**
 * @author Wait
 * @date 2020/12/26
 */
public class SymbolHelpFactory {
    private final EnumMap<Token, SymbolHelp> checkerMap = new EnumMap<Token, SymbolHelp>(Token.class);
    private  final SymbolHelp OPERATOR_CHECKER = new OperatorHelp();


    public SymbolHelpFactory() {
        init();
    }

    private void init() {

        checkerMap.put(equal, new EqualHelp());
        checkerMap.put(num, new NumberHelp());
        checkerMap.put(letter, new VariableHelp());
        checkerMap.put(lBracket, new LeftBracketHelp());
        checkerMap.put(rBracket, new RightBracketHelp());

    }



     public Checker getChecker(Token token) {
        if(TokenHelp.isOperationalToken(token)) {
            return OPERATOR_CHECKER;
        }
        return checkerMap.get(token);
    }
}
