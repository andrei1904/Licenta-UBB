public class SubstractionExpression extends ComplexExpression {
    public SubstractionExpression(Operation operation, Complex[] args) {
        super(operation, args);
    }

    @Override
    public Complex executeOneOperation(Complex complex1, Complex complex2) {
        complex1.substract(complex2);
        return complex1;
    }
}
