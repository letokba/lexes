package org.letokba.lexe;


import org.letokba.lexe.analyse.OperationalExpressionAnalyzer;

/**
 * @author Wait
 * @date 2020/12/23
 */
public class Translator {
    final static OperationalExpressionAnalyzer ANALYZER = new OperationalExpressionAnalyzer();

    public static double computeExpression(String expression) {
        SymbolQueue queue = ANALYZER.analyzed(expression);
        SymbolTree symbolTree = SymbolTree.builder(queue);

        return symbolTree.decode();
    }


}
