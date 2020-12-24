package org.letokba.lexe;

import org.junit.Test;

import static org.junit.Assert.*;

public class TranslatorTest {

    static String[] samples = {
            "1 + 2",                            // 3
            "2 + 3 * 2",                        // 8
            "3 - 2 / 2 * 4 + 4",                // 3
            "(1 + 2)",                          // 3
            "(1 + 2) * 3",                      // 9
            "(2 - 1) + (3 + 1)",                // 5
            "(2.5 - 0.5) * 2",                  // 4
            "((3.0 + 2) * 2) + 0 + (2 * 3)"     // 16
    };


    @Test
    public void computeExpression() {
        for(String sample : samples) {
            double ans = Translator.computeExpression(sample);
            System.out.println(sample + " = " + ans);
        }

    }
}