import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monomial {

    private int exponent;
    private double coefficient;
    public final Lock lock = new ReentrantLock();

    public Monomial(int exponent, double coefficient) {
        this.exponent = exponent;
        this.coefficient = coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return "Monomial{" +
                "exponent=" + exponent +
                ", coefficient=" + coefficient +
                '}';
    }
}
