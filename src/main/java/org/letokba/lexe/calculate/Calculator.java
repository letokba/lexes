package org.letokba.lexe.calculate;

import org.letokba.lexe.SymbolTree;

/**
 * @author Wait
 * @date 2020/12/25
 */
public  interface Calculator<T> {


    abstract T calculated(SymbolTree tree);
}
