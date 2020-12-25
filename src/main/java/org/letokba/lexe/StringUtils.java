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

    static public String strip(String s) {
        assert s != null;

        int len = s.length();
        int i = 0, j = len - 1;
        while ((i < s.length() && Character.isWhitespace(s.charAt(i)))) {
            i++;
        }

        while ((j > i && Character.isWhitespace(s.charAt(j)))) {
            j--;
        }
        return s.substring(i, j + 1);
    }

    public static String clearMoreWhitespace(String s) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);
            builder.append(ch);
            if(Character.isWhitespace(ch)) {
                int j = i + 1;
                while (j < s.length() && Character.isWhitespace(s.charAt(j))) {
                    j++;
                }
                i = j - 1;
            }
            i++;
        }
        return builder.toString();
    }


}
