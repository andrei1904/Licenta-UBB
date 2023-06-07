public class Complex {
    private double real, imaginar;

    public Complex(double real, double imaginar) {
        this.real = real;
        this.imaginar = imaginar;
    }

    public double getReal() {
        return real;
    }

    public double getImaginar() {
        return imaginar;
    }

    @Override
    public String toString() {
        return "Complex{ " +
                "real= " + real +
                ", imaginar= " + imaginar +
                " }";
    }

    public void add(Complex other) {
        this.real += other.real;
        this.imaginar += other.imaginar;
    }

    public void substract(Complex other) {
        this.real -= other.real;
        this.imaginar -= other.imaginar;

    }

    public void multiply(Complex other) {
        double real = this.real;
        double imaginar = this.imaginar;

        this.real = this.real * other.real - this.imaginar * other.imaginar;
        this.imaginar = real * other.imaginar + other.real * imaginar;

    }

    public void divide(Complex other) {
        double real = this.real;
        double imaginar = this.imaginar;

        this.real = (this.real * other.real + this.imaginar * other.imaginar)
                / (other.real * other.real + other.imaginar * other.imaginar);
        this.imaginar = (other.real * imaginar - real * other.imaginar)
                / (other.real * other.real + other.imaginar * other.imaginar);

    }

    public Complex conjugat() {
        this.imaginar -= this.imaginar;
        return this;
    }
}
