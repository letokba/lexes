package org.letokba.lexe;

import java.util.Scanner;

/**
 * @author Wait
 * @date 2020/12/24
 */
public class Main {
    private static final String SUFFIX = "> ";

    private static final String QUIT = "quit()";


    private static final String WELCOME = "Welcome to Lexes !\n";


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(WELCOME);
        while (true) {
            printSuffix();
            String s = in.nextLine();
            String command = StringUtils.clearBlank(s);
            if(QUIT.equals(command)){
                System.exit(1);
            }if(! command.isEmpty()) {
                String reply = executed(command);
                System.out.println(reply);
            }
        }
    }

    public static void printSuffix() {
        System.out.print(SUFFIX);
    }

    public static String executed(String expression) {
        String reply;
        try {
            double v = Translator.computeExpression(expression);
            reply = String.valueOf(v);
        }catch (RuntimeException e) {
            reply = e.getMessage();
        }
        return reply;
    }
}
