import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class LinkedList {

    private Node head;

    LinkedList() {
        this.head = null;
    }

    public synchronized void add(Node node) {
        // Add if list is empty
        if (this.head == null) {
            this.head = node;
            return;
        }
        // before =  x1
        // current = x1
        // x1.exp = 5
        // node.exp = 6
        Node before = this.head;
        Node current = this.head;
        while (current != null &&
                current.getMonomial().getExponent() > node.getMonomial().getExponent()) {
            before = current;
            current = current.getNext();
        }


        // Add at end if list is not empty
        if (current == null) {
            before.setNext(node);
        } else {
            // Add at start if list in not empty
            if (current.equals(this.head)) {

                if (this.head.getMonomial().getExponent() == node.getMonomial().getExponent())
                    current.getMonomial().setCoefficient(current.getMonomial().getCoefficient()
                            + node.getMonomial().getCoefficient());
                else {
                    node.setNext(this.head);
                    this.head = node;
                }

            } else {
                // Add coefficient if exponent already exists somewhere
                if (current.getMonomial().getExponent() == node.getMonomial().getExponent()) {

                    current.getMonomial().setCoefficient(current.getMonomial().getCoefficient() +
                            node.getMonomial().getCoefficient());

                    // Delete if coeficient is 0
                    if (current.getMonomial().getCoefficient() == 0) {
                        if (current.getMonomial().getExponent() ==
                                head.getMonomial().getExponent()) {

                            this.head = this.head.getNext();

                        } else {

                            before.setNext(current.getNext());
                        }
                    }
                }
                // Add a new element
                else {
                    node.setNext(current);
                    before.setNext(node);
                }
            }
        }
    }

    public void writeListInFile(String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("");

            Node current = this.head;
            while (current != null) {

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
