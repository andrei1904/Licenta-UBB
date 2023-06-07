package domain;

public class FIPelement {
    private int atomCode;
    private int tsCode;

    public FIPelement(int atomCode, int tsCode) {
        this.atomCode = atomCode;
        this.tsCode = tsCode;
    }

    public int getAtomCode() {
        return atomCode;
    }

    public void setAtomCode(int atomCode) {
        this.atomCode = atomCode;
    }

    public int getTsCode() {
        return tsCode;
    }

    public void setTsCode(int tsCode) {
        this.tsCode = tsCode;
    }
}
