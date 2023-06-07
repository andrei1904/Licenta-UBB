public class DivisionExpression extends ComplexExpression{
    public DivisionExpression(Operation operation, Complex[] args) {
        super(operation, args);
    }

    @Override
    public Complex executeOneOperation(Complex complex1, Complex complex2) {
        complex1.divide(complex2);
        return complex1;
    }
}
