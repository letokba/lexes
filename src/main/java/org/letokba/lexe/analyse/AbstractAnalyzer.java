package org.letokba.lexe.analyse;

import org.letokba.lexe.Symbol;
import org.letokba.lexe.SymbolQueue;
import org.letokba.lexe.TokenHelp;

import java.util.Iterator;

/**
 * @author Wait
 * @date 2020/12/25
 */
public abstract class AbstractAnalyzer implements Analyzer{

    public int tryMatchDigit(char[] array, int j) {
        boolean isDigit = false;
        boolean hasDot = false;
        while(j < array.length){
            if(Character.isDigit(array[j])) {
                j++;
                isDigit = true;
            }
            else if(array[j] == '.' && isDigit && !hasDot) {
                j++;
                isDigit = false;
                hasDot = true;
            }else {
                break;
            }
        }
        return j;
    }

    public void check(SymbolQueue queue) {
        if(queue.isEmpty()) {
            return;
        }
        Iterator<Symbol> it = queue.iterator();
        Symbol symbol = it.next();
        boolean bracketState = false;
        while (it.hasNext()) {
            Symbol next = it.next();
            boolean result = TokenHelp.getChecker(symbol).checkAfter(next.getToken());
            if(! result) {
                throw new IllegalArgumentException("illegal expression: " + next.getData() + " is not allow after " + symbol.getData());
            }
            bracketState = checkBracket(symbol, bracketState);
            symbol = next;
        }
        bracketState = checkBracket(symbol, bracketState);
    }

    private boolean checkBracket(Symbol symbol, boolean state) {
        if(symbol.isLeftBracket()) {
            if(state){
                throw new IllegalArgumentException("illegal expression: " + "lack the ')'");
            }
            state = true;
        }else if(symbol.isRightBracket()) {
            if(!state) {
                throw new IllegalArgumentException("illegal expression: " + "')' is more");
            }
            state = false;
        }
        return state;
    }

}
