public class Main {
    public static void main(String[] args) {

        ExpressionParser ep = new ExpressionParser(args);

        if (ep.isValid()) {
            System.out.println(ep.createComplexExpression());
        } else {
            System.out.println("Expresie invalida!");
        }
    }
}
