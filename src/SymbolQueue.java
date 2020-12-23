import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Wait
 * @date 2020/12/23
 */
public class SymbolQueue extends AbstractQueue<Symbol> {
    private final LinkedList<Symbol> queue = new LinkedList<>();

    @Override
    public Iterator<Symbol> iterator() {
        return queue.iterator();
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean offer(Symbol symbol) {
        return queue.offer(symbol);
    }

    @Override
    public Symbol poll() {
        return queue.poll();
    }

    @Override
    public Symbol peek() {
        return queue.peek();
    }
}
