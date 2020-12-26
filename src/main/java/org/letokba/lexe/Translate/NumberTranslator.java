package org.letokba.lexe.Translate;

import org.letokba.lexe.core.SymbolQueue;
import org.letokba.lexe.core.SymbolTree;
import org.letokba.lexe.analyse.Analyzer;

/**
 * @author Wait
 * @date 2020/12/26
 */
public class NumberTranslator extends Translator<Double>{


    public NumberTranslator(Analyzer analyzer) {
        super(analyzer);
    }

    @Override
    public Double translated(String expression) {
        SymbolQueue queue = getAnalyzer().analyzed(expression);
        SymbolTree tree = SymbolTree.builder(queue);
        return getCalculator().calculated(tree);
    }
}
