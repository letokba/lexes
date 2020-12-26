package org.letokba.lexe.calculate;

import org.letokba.lexe.core.Symbol;
import org.letokba.lexe.core.SymbolTree;
import org.letokba.lexe.core.TreeNode;

import java.util.Map;

/**
 * @author Wait
 * @date 2020/12/26
 */
public class AssignCalculator  extends NumberCalculator{
    private final Map<String, Double> cache;

    public AssignCalculator(Map<String, Double> cache) {
        this.cache = cache;
    }

    @Override
    public Double calculated(SymbolTree tree) {
        postOrder(tree.getRoot());
        TreeNode root = tree.getRoot();
        if(root.flag.isEqual()) {
            Symbol symbol = root.leftChild.flag;
            if(symbol.isVariable()) {
                String name = (String) symbol.getData();
                Double value = transformValue(root.rightChild.flag);
                cache.put(name, value);
                return value;
            }
        }
        return transformValue(root.flag);
    }

    @Override
    public Double transformValue(Symbol symbol) {
        if(symbol.isVariable()) {
            String name = (String) symbol.getData();
            return cache.get(name);
        }
        return super.transformValue(symbol);
    }
}
