package org.letokba.lexe;

import org.junit.Before;
import org.junit.Test;
import org.letokba.lexe.Translate.Translator;
import org.letokba.lexe.Translate.TranslatorFactory;
import org.letokba.lexe.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

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

    static String[] cachedSamples = {
            "(1 + 2) * 3",                      // 9
            "(2 - 1) + (3 + 1)",                // 5
            "(2.5 - 0.5) * 2",                  // 4
            "((3.0 + 2) * 2) + 0 + (2 * 3)",    // 16
            "3 + (2 + 3) * 3",                  // 18
            "a = 2 + 3",                        // a = 5
            "b = a * 2",                        // b = 10
            "b = 10 - 2",                       // b = 8
            "b",                                // b = 8
            "c",                                // c is not defined
    };

    private Translator<Double> translator;

    @Before
    public void init() {
        translator = new TranslatorFactory().getTranslator(true);
    }


    @Test
    public void computeExpression() {
        for (String sample : samples) {
            double ans = translator.translated(sample);
            System.out.println(sample + " = " + ans);
        }
    }

    @Test
    public void defineVariable() {
        double a = translator.translated("a = ( 1 + 2) * 3");
        double b = translator.translated("b = a + 2");
        double c = translator.translated("c = b");
        System.out.println("a = " + a + " , b = " + b + " , c = " + c);
    }

    @Test
    public void cacheExpression() {
        for (String sample : cachedSamples) {
            try {
                double ans = translator.translated(sample);
                System.out.println(sample + " = " + ans);
            }catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @Test
    public void simpleTest() {
        String sample = "(1 + 2)";
        double ans = translator.translated(sample);
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
                .collect(Collectors.toList());

        List<Double> result = readFileResult();

        for(int i = 0; i < list.size(); i++) {
            String sample = list.get(i);
            Double d = translator.translated(sample);

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