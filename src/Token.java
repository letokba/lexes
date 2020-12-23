/**
 * @author Wait
 * @date 2020/12/23
 */
public enum Token {
    lBracket(16), rBracket(8), add(2), sub(2), mul(1), dev(1), num(0);

    int priority;

    Token(int i) {
        this.priority = i;
    }


}
