package org.letokba.lexe;

/**
 * @author Wait
 * @date 2020/12/24
 */
public class StringUtils {

    static public String clearBlank(String s) {
        StringBuilder builder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if(Character.isWhitespace(c)){
                continue;
            }
            builder.append(c);
        }
        return builder.toString();
    }
}
