public class ExpressionFactory {
    private static ExpressionFactory instance = null;

    private ExpressionFactory() {
    }

    public static ExpressionFactory getInstance() {
        if (instance == null) {
            instance = new ExpressionFactory();
        }

        return instance;
    }

    public ComplexExpression createExpression(Operation operation, Complex[] args) {
        return switch (operation) {
            case ADDITION -> new AdditionExpression(operation, args);

            case SUBSTRACTION -> new SubstractionExpression(operation, args);

            case MULTIPLICATION -> new MultipliactionExpression(operation, args);

            case DIVISION -> new DivisionExpression(operation, args);
        };
    }
}
