package org.letokba.lexe;

import org.junit.Test;
import org.letokba.lexe.analyse.OperationalExpressionAnalyzer;

public class OperationalExpressionAnalyzerTest {
    OperationalExpressionAnalyzer analyzer = new OperationalExpressionAnalyzer();

    @Test
    public void analyzed() {
        String sample = "1 + 2_";
        SymbolQueue queue = analyzer.analyzed(sample);
        queue.forEach(System.out::println);
    }


}