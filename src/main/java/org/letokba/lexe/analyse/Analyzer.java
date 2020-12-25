package org.letokba.lexe.analyse;

import org.letokba.lexe.SymbolQueue;

/**
 * @author Wait
 * @date 2020/12/25
 */
public interface Analyzer {

    /**
     * 不安全的分析
     * @param text 待分析的文本
     * @return Symbol Queue
     */
    public SymbolQueue analyzed(String text);
}
