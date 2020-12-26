package org.letokba.lexe.Translate;

import org.letokba.lexe.analyse.Analyzer;
import org.letokba.lexe.calculate.Calculator;

/**
 * @author Wait
 * @date 2020/12/26
 */
public abstract class Translator<T> {
    private final Analyzer analyzer;
    private Calculator<T> calculator;

    public Translator(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    public void setCalculator(Calculator<T> calculator) {
        this.calculator = calculator;
    }

    public Analyzer getAnalyzer() {
        return analyzer;
    }

    public Calculator<T> getCalculator() {
        return calculator;
    }

    public abstract T translated(String expression);
}
