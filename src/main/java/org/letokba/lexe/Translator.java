package org.letokba.lexe;


import org.letokba.lexe.analyse.Analyzer;
import org.letokba.lexe.analyse.OperationalExpressionAnalyzer;
import org.letokba.lexe.calculate.Calculator;
import org.letokba.lexe.calculate.NumberCalculator;

/**
 * @author Wait
 * @date 2020/12/23
 */
public class Translator {
    final static Analyzer ANALYZER = new OperationalExpressionAnalyzer();

    public static double computeExpression(String expression) {
        SymbolQueue queue = ANALYZER.analyzed(expression);
        SymbolTree symbolTree = SymbolTree.builder(queue);
        Calculator<Double> calculator = new NumberCalculator();
        return calculator.calculated(symbolTree);
    }


}
