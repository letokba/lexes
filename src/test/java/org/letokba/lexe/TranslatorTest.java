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
        for (String sample : samples) {
            double ans = Translator.computeExpression(sample);
            System.out.println(sample + " = " + ans);
        }
    }

    @Test
    public void simpleTest() {
        String sample = "(1 + 2)";
        double ans = Translator.computeExpression(sample);
        System.out.println(sample + " = " + ans);
    }

    @Test
    public void readFileAndCompute() throws IOException {
        String filename = "samples.txt";
        Path path = Paths.get(filename);
        if (!Files.exists(path)) {
            System.err.println("File is not exist !!!");
        }
        List<String> list = Files.readAllLines(path)
                .stream()
                .map(StringUtils::clearBlank)
                .filter((item) -> !item.isEmpty())
//                .map(Translator::computeExpression)
                .collect(Collectors.toList());

        List<Double> result = readFileResult();

        for(int i = 0; i < list.size(); i++) {
            String sample = list.get(i);
            Double d = Translator.computeExpression(sample);

            System.out.format("%-20s = %5.2f %10s\n", sample, d, (d.equals(result.get(i))));
        }

    }


    public static List<Double> readFileResult() throws IOException {
        String filename = "result.txt";
        Path path = Paths.get(filename);
        return Files.readAllLines(path)
                .stream()
                .filter((item) -> !item.isEmpty())
                .map(Double::parseDouble)
                .collect(Collectors.toList());

    }

}