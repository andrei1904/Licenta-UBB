public class AdditionExpression extends ComplexExpression {

    public AdditionExpression(Operation operation, Complex[] args) {
        super(operation, args);
    }

    @Override
    public Complex executeOneOperation(Complex complex1, Complex complex2) {
        complex1.add(complex2);
        return complex1;
    }
}
