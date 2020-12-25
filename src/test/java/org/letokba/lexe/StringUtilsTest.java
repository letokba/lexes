package org.letokba.lexe;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class StringUtilsTest {
    static String[] samples = {
        "   1234   ",
        "12  34    ",
        "  1234",
        "12  34",
        "1234",
        "  ",
        ""
    };


    @Test
    public void strip() {
        Arrays.stream(samples).map(StringUtils::strip)
                .map((item) -> "^" + item + "$")
                .forEach(System.out::println);
    }
}