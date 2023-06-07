public class Transition {

    private State initialState;
    private State finalState;
    private String symbol;

    public Transition(State initialState, State finalState, String symbol) {
        this.initialState = initialState;
        this.finalState = finalState;
        this.symbol = symbol;
    }

    public State getInitialState() {
        return initialState;
    }

    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }

    public State getFinalState() {
        return finalState;
    }

    public void setFinalState(State finalState) {
        this.finalState = finalState;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
