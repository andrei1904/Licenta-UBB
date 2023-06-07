import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Node {
    private Monomial monomial;
    private Node next;
    public final Lock lock = new ReentrantLock();

    public Node(Monomial monomial) {
        this.monomial = monomial;
    }

    public Monomial getMonomial() {
        return monomial;
    }

    public void setMonomial(Monomial monomial) {
        this.monomial = monomial;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
