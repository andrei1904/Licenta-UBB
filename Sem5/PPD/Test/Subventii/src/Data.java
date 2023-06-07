public class Data {

    private int currentValue = 0;
    private int alocatedValue = 0;

    public Data(int currentValue, int alocatedValue) {
        this.currentValue = currentValue;
        this.alocatedValue = alocatedValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public int getAlocatedValue() {
        return alocatedValue;
    }

    public void setAlocatedValue(int alocatedValue) {
        this.alocatedValue = alocatedValue;
    }
}
