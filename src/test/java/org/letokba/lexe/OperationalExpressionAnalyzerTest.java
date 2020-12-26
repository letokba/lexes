package org.letokba.lexe;

import org.junit.Test;
import org.letokba.lexe.analyse.OperationalExpressionAnalyzer;
import org.letokba.lexe.core.SymbolQueue;

public class OperationalExpressionAnalyzerTest {
    OperationalExpressionAnalyzer analyzer = new OperationalExpressionAnalyzer();

    @Test
    public void analyzed() {
        String sample = "((3.0 + 2) * 2) + 0 + (2 * 3)";
        SymbolQueue queue = analyzer.analyzed(sample);
        queue.forEach(System.out::println);
    }


}