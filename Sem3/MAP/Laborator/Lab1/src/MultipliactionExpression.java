public class MultipliactionExpression extends ComplexExpression{
    public MultipliactionExpression(Operation operation, Complex[] args) {
        super(operation, args);
    }

    @Override
    public Complex executeOneOperation(Complex complex1, Complex complex2) {
        complex1.multiply(complex2);
        return complex1;
    }
}
