package org.letokba.lexe;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

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
            "((3.0 + 2) * 2) + 0 + (2 * 3)",    // 16
            "3 + (2 + 3) * 3"                   // 18
    };


    @Test
    public void computeExpression() {
        for(String sample : samples) {
            double ans = Translator.computeExpression(sample);
            System.out.println(sample + " = " + ans);
        }
    }

    @Test
    public void simpleTest() {
        String sample = "(2 + 3) + 2";
        double ans = Translator.computeExpression(sample);
        System.out.println(sample + " = " + ans);
    }

    @Test
    public void readFileAndCompute() throws IOException {
        String filename = "samples.txt";
        Path path = Paths.get(filename);
        if(! Files.exists(path)) {
            System.err.println("File is not exist !!!");
        }
        Files.readAllLines(path)
                .stream()
                .map(StringUtils::clearBlank)
                .filter((item) -> ! item.isEmpty())
                .forEach((sample) -> {
                    double ans = Translator.computeExpression(sample);
                    System.out.println(sample + " = " + ans + " ");
                });
    }


}