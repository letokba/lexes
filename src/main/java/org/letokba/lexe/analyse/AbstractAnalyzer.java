package org.letokba.lexe.analyse;

import org.letokba.lexe.Symbol;
import org.letokba.lexe.SymbolQueue;

import java.util.Iterator;

/**
 * @author Wait
 * @date 2020/12/25
 */
public abstract class AbstractAnalyzer implements Analyzer{

    public int tryMatchDigit(char[] array, int j) {
        boolean isDigit = false;
        boolean hasDot = false;
        while(j < array.length){
            if(Character.isDigit(array[j])) {
                j++;
                isDigit = true;
            }
            else if(array[j] == '.' && isDigit && !hasDot) {
                j++;
                isDigit = false;
                hasDot = true;
            }else {
                break;
            }
        }
        return j;
    }

}
