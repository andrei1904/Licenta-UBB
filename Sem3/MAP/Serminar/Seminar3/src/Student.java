import java.util.Objects;

public class Student {
    private String nume;
    private double media;

    public Student(String nume, double media) {
        this.nume = nume;
        this.media = media;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nume='" + nume + '\'' +
                ", media=" + media +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Double.compare(student.getMedia(), getMedia()) == 0 &&
                getNume().equals(student.getNume());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNume(), getMedia());
    }
}
