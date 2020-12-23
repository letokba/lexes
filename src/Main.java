/**
 * @author Wait
 * @date 2020/12/23
 */
public class Main {


    public static void main(String[] args) {
        String expression = "2 - 3 * 2 + 3 / 2";
        double ans = Translator.computeExpression(expression);
        System.out.println(ans);
    }
}
