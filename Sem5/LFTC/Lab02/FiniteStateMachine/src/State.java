public class State {

    private StateType type;
    private String symbol;

    public State(StateType type, String symbol) {
        this.type = type;
        this.symbol = symbol;
    }

    public StateType getType() {
        return type;
    }

    public void setType(StateType type) {
        this.type = type;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "State{" +
                "type=" + type +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
