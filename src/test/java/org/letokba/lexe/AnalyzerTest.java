package org.letokba.lexe;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnalyzerTest {
    Analyzer analyzer = new Analyzer();

    @Test
    public void analyzed() {
        String sample = "_";
        SymbolQueue queue = analyzer.analyzed(sample);
        queue.forEach(System.out::println);
    }
}