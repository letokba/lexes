package org.letokba.lexe.Translate;

import org.letokba.lexe.core.Symbol;
import org.letokba.lexe.core.SymbolQueue;
import org.letokba.lexe.core.SymbolTree;
import org.letokba.lexe.core.Token;
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
        Double ans;
        SymbolQueue queue = getAnalyzer().analyzed(expression);
        if(queue.size() == 1) {
            String name = (String) queue.peek().getData();
            return cache.get(name);
        }
        ans = definedVariable(queue);
        if(ans == null) {
            SymbolTree symbolTree = SymbolTree.builder(queue);
            ans = getCalculator().calculated(symbolTree);
        }
        return  ans;
    }

    private Double definedVariable(SymbolQueue queue) {
        if(queue.size() >= 3) {
            Symbol first = queue.get(0);
            Symbol second = queue.get(1);
            if(first.isVariable() && second.getToken() == Token.equal) {
                queue.poll();
                queue.poll();
                SymbolTree symbolTree = SymbolTree.builder(queue);
                Double data = getCalculator().calculated(symbolTree);
                String variableName = (String) first.getData();
                cache.put(variableName, data);
                return data;
            }
        }
        return null;
    }
}
