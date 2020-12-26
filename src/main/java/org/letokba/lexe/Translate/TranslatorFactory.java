package org.letokba.lexe.Translate;

import org.letokba.lexe.analyse.Analyzer;
import org.letokba.lexe.analyse.AssignExpressAnalyzer;
import org.letokba.lexe.analyse.OperationalExpressionAnalyzer;
import org.letokba.lexe.calculate.AssignCalculator;
import org.letokba.lexe.calculate.Calculator;
import org.letokba.lexe.calculate.NumberCalculator;

/**
 * @author Wait
 * @date 2020/12/26
 */
public class TranslatorFactory {

    public Translator<Double> getTranslator() {
        return getTranslator(false);
    }

    public Translator<Double> getTranslator(boolean cached) {
        if(cached){
            return createCachedTranslator();
        }
        return createNumberTranslator();
    }

    private Translator<Double> createCachedTranslator() {
        Analyzer analyzer = new AssignExpressAnalyzer();
        CachedTranslator translator = new CachedTranslator(analyzer);
        Calculator<Double> calculator = new AssignCalculator(translator.getCache());
        translator.setCalculator(calculator);
        return translator;
    }

    private Translator<Double> createNumberTranslator() {
        Analyzer analyzer = new OperationalExpressionAnalyzer();
        NumberTranslator translator = new NumberTranslator(analyzer);
        Calculator<Double> calculator = new NumberCalculator();
        translator.setCalculator(calculator);
        return translator;
    }
}
