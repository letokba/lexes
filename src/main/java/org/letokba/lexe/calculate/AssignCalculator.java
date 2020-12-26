package org.letokba.lexe.calculate;

import org.letokba.lexe.core.Symbol;

import java.util.Map;

/**
 * @author Wait
 * @date 2020/12/26
 */
public class AssignCalculator  extends NumberCalculator{
    private final Map<String, Double> cache;

    public AssignCalculator(Map<String, Double> cache) {
        this.cache = cache;
    }


    @Override
    public Double transformValue(Symbol symbol) {
        if(symbol.isVariable()) {
            String name = (String) symbol.getData();
            return cache.get(name);
        }
        return super.transformValue(symbol);
    }
}
