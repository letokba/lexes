package org.letokba.lexe.help;

import org.letokba.lexe.core.Token;
import org.letokba.lexe.help.operator.*;

import static org.letokba.lexe.core.Token.*;
import java.util.EnumMap;

/**
 * @author Wait
 * @date 2020/12/26
 */
public class SymbolHelpFactory {
    private final EnumMap<Token, SymbolHelp> checkerMap = new EnumMap<Token, SymbolHelp>(Token.class);

    public SymbolHelpFactory() {
        init();
    }

    private void init() {

        checkerMap.put(equal, new EqualHelp());
        checkerMap.put(num, new NumberHelp());
        checkerMap.put(letter, new VariableHelp());
        checkerMap.put(lBracket, new LeftBracketHelp());
        checkerMap.put(rBracket, new RightBracketHelp());
        checkerMap.put(add, new AddHelp());
        checkerMap.put(sub, new SubHelp());
        checkerMap.put(mul, new MulHelp());
        checkerMap.put(div, new DivHelp());
        checkerMap.put(mod, new ModHelp());

    }



     public SymbolHelp getHelp(Token token) {
        return checkerMap.get(token);
    }
}
