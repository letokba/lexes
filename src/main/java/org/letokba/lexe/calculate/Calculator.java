package org.letokba.lexe.calculate;

import org.letokba.lexe.core.Symbol;
import org.letokba.lexe.core.SymbolTree;

/**
 * @author Wait
 * @date 2020/12/25
 */
public  interface Calculator<T> {


    T calculated(SymbolTree tree);

    T transformValue(Symbol symbol);


}
