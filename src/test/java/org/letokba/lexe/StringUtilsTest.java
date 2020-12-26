package org.letokba.lexe;

import org.junit.Test;
import org.letokba.lexe.util.StringUtils;

import java.util.Arrays;

public class StringUtilsTest {
    static String[] samples = {
            "   1234   ",
            "12  34    ",
            "  1234",
            "12  34",
            "1234",
            "  ",
            "",
            "a = 2  + 3    ",
            "b=2",
            " a=2",
            " a  =2 ",
            " a   2a 2"
    };


    @Test
    public void strip() {
        Arrays.stream(samples).map(StringUtils::strip)
                .map((item) -> "^" + item + "$")
                .forEach(System.out::println);
    }

    @Test
    public void clearMoreWhiteSpace() {
        Arrays.stream(samples).map(StringUtils::clearMoreWhitespace)
                .map(StringUtils::strip)
                .map((item) -> "^" + item + "$")
                .forEach(System.out::println);
    }
}