package org.letokba.lexe;

import org.letokba.lexe.Translate.Translator;
import org.letokba.lexe.Translate.TranslatorFactory;
import org.letokba.lexe.util.StringUtils;

import java.util.Scanner;

/**
 * @author Wait
 * @date 2020/12/24
 */
public class Main {
    private static final String SUFFIX = "> ";

    private static final String QUIT = "quit()";

    private static final String WELCOME = "Welcome to Lexes !\n" +
            "You can define variable to save the result. \n";

    private Translator<Double> translator;

    private Main() {
        init();
    }

    public void init() {
        translator = new TranslatorFactory().getTranslator(true);
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner in = new Scanner(System.in);
        System.out.println(WELCOME);
        while (true) {
            printSuffix();
            String s = in.nextLine();
            String command = StringUtils.clearBlank(s);
            if(QUIT.equals(command)){
                System.exit(1);
            }if(! command.isEmpty()) {
                String reply = main.executed(command);
                System.out.println(reply);
            }
        }
    }

    public static void printSuffix() {
        System.out.print(SUFFIX);
    }

    public  String executed(String expression) {
        String reply;
        try {
            double v = translator.translated(expression);
            reply = String.valueOf(v);
        }catch (RuntimeException e) {
            reply = e.getMessage();
        }
        return reply;
    }
}
