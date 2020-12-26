package org.letokba.lexe.Translate;

import org.letokba.lexe.core.SymbolQueue;
import org.letokba.lexe.core.SymbolTree;
import org.letokba.lexe.analyse.Analyzer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wait
 * @date 2020/12/26
 */
public class CachedTranslator extends Translator<Double>{
    private final Map<String, Double> cache;

    public CachedTranslator(Analyzer analyzer) {
        super(analyzer);
        this.cache = new HashMap<>();
    }

    public Map<String, Double> getCache() {
        return cache;
    }

    @Override
    public Double translated(String expression) {
        Double value;
        SymbolQueue queue = getAnalyzer().analyzed(expression);
        if(queue.size() == 1) {
            String name = (String) queue.peek().getData();
            return cache.get(name);
        }
        SymbolTree symbolTree = SymbolTree.builder(queue);
        value = getCalculator().calculated(symbolTree);
        return value;
    }


}
