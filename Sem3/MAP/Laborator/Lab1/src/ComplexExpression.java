public abstract class ComplexExpression {
    Operation operation;
    Complex[] args;

    public ComplexExpression(Operation operation, Complex[] args) {
        this.operation = operation;
        this.args = args;
    }

    public abstract Complex executeOneOperation(Complex complex1, Complex complex2);

    public Complex execute() {
        Complex result = new Complex(args[0].getReal(), args[0].getImaginar());
        for (int i = 1; i < args.length; i++) {
            result = executeOneOperation(result, args[i]);
        }
        return result;
    }

}
