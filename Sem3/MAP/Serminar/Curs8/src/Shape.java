public class Shape {
    public String type = " s ";
    public Shape() {
        System.out.println("shape ");
    }
}

class Rectangle extends Shape {
    public Rectangle() {
        System.out.println("rectangle ");
    }

    public static void main(String[] args) {
        new Rectangle().go();
    }

    void go() {
        type = "r";
        System.out.println(this.type + super.type);
    }
}
