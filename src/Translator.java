/**
 * @author Wait
 * @date 2020/12/23
 */
public class Translator {
    static Analyzer analyzer = new Analyzer();
    static Operator operator = new Operator();

    public static double computeExpression(String expression) {
        SymbolQueue queue = analyzer.analyzed(expression);
        SymbolTree symbolTree = SymbolTree.builder(queue);

        return operator.cal(symbolTree);
    }
}
