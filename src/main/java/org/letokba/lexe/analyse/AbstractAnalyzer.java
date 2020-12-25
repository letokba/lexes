package org.letokba.lexe.analyse;

import org.letokba.lexe.SymbolQueue;

/**
 * @author Wait
 * @date 2020/12/25
 */
public abstract class AbstractAnalyzer {


    /**
     * 不安全的分析
     * @param text 待分析的文本
     * @return Symbol Queue
     */
    abstract SymbolQueue analyzed(String text);



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
