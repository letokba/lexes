package org.letokba.lexe;


import org.letokba.lexe.analyse.Analyzer;
import org.letokba.lexe.analyse.AssignExpressAnalyzer;
import org.letokba.lexe.analyse.OperationalExpressionAnalyzer;
import org.letokba.lexe.calculate.Calculator;
import org.letokba.lexe.calculate.NumberCalculator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wait
 * @date 2020/12/23
 */
public class Translator {
    private static final Analyzer ANALYZER = new OperationalExpressionAnalyzer();
    private static final Map<String, Object> cache = new HashMap<>();

    public static double computeExpression(String expression) {
        SymbolQueue queue = ANALYZER.analyzed(expression);
        SymbolTree symbolTree = SymbolTree.builder(queue);
        Calculator<Double> calculator = new NumberCalculator();
        return calculator.calculated(symbolTree);
    }


    public static double defineVariable(String expression) {
        Calculator<Double> calculator = new NumberCalculator();
        Analyzer analyzer = new AssignExpressAnalyzer();
        SymbolQueue queue = analyzer.analyzed(expression);

        if(queue.size() == 1) {
            String name = (String) queue.peek().getData();
            return (double) cache.get(name);
        }

        if(queue.size() > 3) {
            Symbol first = queue.get(0);
            Symbol second = queue.get(1);
            if(first.isVariable() && second.getToken() == Token.equal) {
                queue.poll();
                queue.poll();
                SymbolTree symbolTree = SymbolTree.builder(queue);
                Double data = calculator.calculated(symbolTree);
                String variableName = (String) first.getData();
                cache.put(variableName, data);
                return data;
            }
        }
        SymbolTree symbolTree = SymbolTree.builder(queue);
        return  calculator.calculated(symbolTree);
    }


}
