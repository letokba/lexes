package org.letokba.lexe;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnalyzerTest {
    Analyzer analyzer = new Analyzer();

    @Test
    public void analyzed() {
        String sample = "1 + 2_";
        SymbolQueue queue = analyzer.analyzed(sample);
        queue.forEach(System.out::println);
    }

    @Test
    public void analyzed2() {
        SymbolQueue queue = new SymbolQueue();
        String sample = "a$2 = 222 + 2.12";
        String a$2 = "";
        analyzer.unsafeMoreAnalyzed(StringUtils.clearBlank(sample), queue);
        queue.forEach(System.out::println);
    }
}