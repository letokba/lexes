package org.letokba.lexe.analyse;

import org.letokba.lexe.core.Symbol;
import org.letokba.lexe.core.SymbolQueue;
import org.letokba.lexe.core.Token;
import org.letokba.lexe.core.TokenHelp;

import java.util.Iterator;
import java.util.Stack;

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
        checkHead(symbol);
        checkBracket(queue);
        while (it.hasNext()) {
            Symbol next = it.next();
            boolean result = TokenHelp.getChecker(symbol).checkAfter(next.getToken());
            if(! result) {
                throw new IllegalArgumentException("illegal expression: " + next.getData() + " is not allow after " + symbol.getData());
            }
            symbol = next;
        }

        checkTail(symbol);
    }

    private void checkBracket(SymbolQueue queue) {
        Stack<Object> stack = new Stack<>();
        for (Symbol symbol : queue) {
            if (symbol.isLeftBracket()) {
                stack.push(symbol.getData());
                continue;
            }
            if (symbol.isRightBracket()) {
                if(stack.isEmpty()) {
                    throw new IllegalArgumentException("illegal expression: " + " ')' is more.");
                }
                stack.pop();
            }
        }
        if(stack.size() != 0){
            throw new IllegalArgumentException("illegal expression: " + "')' is less.");
        }
    }

    private void checkHead(Symbol symbol) {
        Token token = symbol.getToken();
        boolean result = token == Token.letter || token == Token.num || token == Token.lBracket;
        if(! result) {
            throw new IllegalArgumentException("illegal expression: " + "not allow starting at " + symbol.getData());
        }
    }

    private void checkTail(Symbol symbol) {
        Token token = symbol.getToken();
        boolean result = token == Token.letter || token == Token.num || token == Token.rBracket;
        if(! result) {
            throw new IllegalArgumentException("illegal expression: " + "not allow trailing at " + symbol.getData());
        }
    }

}
