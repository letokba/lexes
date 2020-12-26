package org.letokba.lexe.analyse;

import org.junit.Test;
import org.letokba.lexe.SymbolQueue;

import static org.junit.Assert.*;

public class AssignExpressAnalyzerTest {
    Analyzer analyzer = new AssignExpressAnalyzer();
    @Test
    public void analyzed() {
        String sample = "2 + ";

        SymbolQueue queue = analyzer.analyzed(sample);
        queue.forEach(System.out::println);
    }
}