package org.letokba.lexe;


import org.letokba.lexe.analyse.Analyzer;
import org.letokba.lexe.analyse.AssignExpressAnalyzer;
import org.letokba.lexe.analyse.OperationalExpressionAnalyzer;
import org.letokba.lexe.calculate.Calculator;
import org.letokba.lexe.calculate.NumberCalculator;

import java.util.Iterator;

/**
 * @author Wait
 * @date 2020/12/23
 */
public class Translator {
    private final static Analyzer ANALYZER = new OperationalExpressionAnalyzer();


    public static double computeExpression(String expression) {
        SymbolQueue queue = ANALYZER.analyzed(expression);
        SymbolTree symbolTree = SymbolTree.builder(queue);
        Calculator<Double> calculator = new NumberCalculator();
        return calculator.calculated(symbolTree);
    }


    public static double defineVariable(String expression) {
        Analyzer analyzer = new AssignExpressAnalyzer();
        SymbolQueue queue = analyzer.analyzed(expression);

        Iterator<Symbol> it = queue.iterator();


//        while (it.hasNext()) {
//            Symbol symbol = it.next();
//            if (symbol.isVariable()) {
//
//            }
//        }

        return  0;
    }


}
