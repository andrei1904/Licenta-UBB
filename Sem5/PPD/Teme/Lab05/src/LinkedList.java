import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class LinkedList {

    private final Node head;
    private final Node end;

    LinkedList() {
        this.head = new Node(null);
        this.end = new Node(null);
    }

    public void add(Node node) {

        if (head.getNext() == null) {
            head.lock.lock();

            head.setNext(node);
            node.setNext(end);

            head.lock.unlock();
            return;
        }


        Node previous = head.getNext();
        Node current = head.getNext();
        while (!current.equals(end) &&
                current.getMonomial().getExponent() > node.getMonomial().getExponent()) {
            previous.lock.lock();
            current.lock.lock();
            current.getNext().lock.lock();

            Node prePrevious = previous;
            previous = current;
            current = current.getNext();

            prePrevious.lock.unlock();
            previous.lock.unlock();
            current.lock.unlock();
        }

        if (current.equals(end)) {
            previous.lock.lock();

            previous.setNext(node);
            node.setNext(end);

            previous.lock.unlock();

        } else {

            if (current == head.getNext()) {
                if (current.getMonomial().getExponent() == node.getMonomial().getExponent()) {
                    current.lock.lock();

                    current.getMonomial().setCoefficient(current.getMonomial().getCoefficient()
                            + node.getMonomial().getCoefficient());

                    current.lock.unlock();

                    if (current.getMonomial().getCoefficient() == 0) {
                        current.lock.lock();
                        head.lock.lock();

                        head.setNext(current.getNext());

                        head.lock.unlock();
                        current.lock.unlock();
                    }
                } else {

                    current.lock.lock();
                    node.setNext(current);
                    current.lock.unlock();

                    head.lock.lock();
                    head.setNext(node);
                    head.lock.unlock();
                }

            } else {
                if (current.getMonomial().getExponent() == node.getMonomial().getExponent()) {
                    current.lock.lock();

                    current.getMonomial().setCoefficient(current.getMonomial().getCoefficient()
                            + node.getMonomial().getCoefficient());

                    current.lock.unlock();

                    if (current.getMonomial().getCoefficient() == 0) {
                        current.lock.lock();
                        previous.lock.lock();

                        previous.setNext(current.getNext());

                        previous.lock.unlock();
                        current.lock.unlock();
                    }
                } else {

                    current.lock.lock();
                    node.setNext(current);
                    current.lock.unlock();

                    previous.lock.lock();
                    previous.setNext(node);
                    previous.lock.unlock();
                }
            }
        }

    }

    public void writeListInFile(String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("");

            Node current = head.getNext();
            while (!current.equals(end)) {
                writer.append(String.valueOf(
                        new DecimalFormat("##.##")
                                .format(current.getMonomial().getCoefficient())))
                        .append(" * x^")
                        .append(String.valueOf(current.getMonomial().getExponent()))
                        .append(" + ");

                current = current.getNext();
            }

            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
