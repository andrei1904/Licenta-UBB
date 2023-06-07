
public class Block {

    private double[][] values;
    private final String type;
    private final int i;
    private final int j;

    public Block(double[][] values, String type, int i, int j) {
        this.values = values;
        this.type = type;
        this.i = i;
        this.j = j;
    }

    public double[][] getValues() {
        return values;
    }

    public String getType() {
        return type;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setValues(double[][] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return
//                "Block{" +
//                "values=" + Arrays.toString(values) +
                ", type='" + type + '\'' +
                ", i=" + i +
                ", j=" + j +
                '}';
    }
}
