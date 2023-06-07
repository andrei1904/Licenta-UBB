
public class ArrayBuilder {
    public int[] build() {
        int factor=13;
        int[] arrayOfNumbers = new int[10];
        for(int i=0; i<10; i++) {
            arrayOfNumbers[i] = factor*i;
        }
        return arrayOfNumbers;
    }
}
