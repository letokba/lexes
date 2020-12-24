/**
 * @author Wait
 * @date 2020/12/23
 */
public class Translator {
    static Analyzer analyzer = new Analyzer();

    public static double computeExpression(String expression) {
        SymbolQueue queue = analyzer.analyzed(expression);
        SymbolTree symbolTree = SymbolTree.builder(queue);

        return symbolTree.decode();
    }
}
