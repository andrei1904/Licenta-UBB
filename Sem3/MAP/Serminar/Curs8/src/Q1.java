class A {
    public static boolean val;
    public A() {
        val = true;
    }
    public static void print() {
        System.out.println("Fisrt");
    }
}

public class Q1 {
    public static void main(String[] args) {
        A a = null;
        if (!a.val)
            a.print();
        else
            System.out.println("Second");
    }
}

