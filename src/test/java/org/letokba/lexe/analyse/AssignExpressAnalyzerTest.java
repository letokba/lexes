package org.letokba.lexe.analyse;

import org.junit.Test;
import org.letokba.lexe.core.SymbolQueue;

public class AssignExpressAnalyzerTest {
    Analyzer analyzer = new AssignExpressAnalyzer();
    @Test
    public void analyzed() {
        String sample = "a";

        SymbolQueue queue = analyzer.analyzed(sample);
        queue.forEach(System.out::println);
    }
}