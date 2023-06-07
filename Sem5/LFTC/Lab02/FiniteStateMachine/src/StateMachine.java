import java.util.Set;
import java.util.stream.Collectors;

public class StateMachine {

    private final Set<State> states;
    private final Set<String> alphabet;
    private final Set<Transition> transitions;

    public StateMachine(Set<State> states, Set<String> alphabet, Set<Transition> transitions) {
        this.states = states;
        this.alphabet = alphabet;
        this.transitions = transitions;
    }

    public Set<State> getStates() {
        return states;
    }

    public Set<String> getAlphabet() {
        return alphabet;
    }

    public Set<Transition> getTransitions() {
        return transitions;
    }

    public Set<State> getFinalStates() {
        return states.stream()
                .filter(state -> state.getType().equals(StateType.FINAL))
                .collect(Collectors.toSet());
    }

    public boolean isAccepted(String input) {
        State currentState = findInitialState();

        String[] symbols = input.split("");
        int lenght = symbols.length - 1;
        Transition t;

        int i = 0;
        for (String symbol : symbols) {
            State state = currentState;

            t = null;

            if (i ==  lenght) {
                for (Transition transition : transitions) {
                    if (transition.getInitialState().equals(state) &&
                            transition.getSymbol().equals(symbol)) {
                        if (transition.getFinalState().getType() == StateType.FINAL) {
                            t = transition;
                            break;
                        }
                    }
                }
            } else {
                for (Transition transition : transitions) {
                    if (transition.getInitialState().equals(state) &&
                            transition.getSymbol().equals(symbol)) {
                        t = transition;
                    }
                }
            }

            if (t == null) {
                break;
            }

            currentState = t.getFinalState();
            i++;
        }

        return currentState.getType() == StateType.FINAL;
    }

    public State findInitialState() {
        return states.stream()
                .filter(state -> state.getType() == StateType.INITIAL)
                .findFirst()
                .orElseThrow();
    }

    public String longestAcceptedPrefix(String input) {
        State currentState = findInitialState();

        String[] symbols = input.split("");
        StringBuilder partialResult = new StringBuilder();
        StringBuilder result = new StringBuilder();
        boolean wasFinal = false;

        int lenght = symbols.length - 1;
        Transition t;
        int i = 0;

        for (String symbol : symbols) {
            State state = currentState;

            t = null;
            if (i ==  lenght) {
                for (Transition transition : transitions) {
                    if (transition.getInitialState().equals(state) &&
                            transition.getSymbol().equals(symbol)) {
                        if (transition.getFinalState().getType() == StateType.FINAL) {
                            t = transition;
                            break;
                        }
                    }
                }
            } else {
                for (Transition transition : transitions) {
                    if (transition.getInitialState().equals(state) &&
                            transition.getSymbol().equals(symbol)) {
                        t = transition;
                    }
                }
            }

            if (t == null) {
                break;
            }

            currentState = t.getFinalState();
            partialResult.append(symbol);
            if (currentState.getType() == StateType.FINAL) {
                result.append(partialResult);
                wasFinal = true;
                partialResult.delete(0, partialResult.length());
            }

            i++;
        }

        if (wasFinal) {
            return result.toString();
        } else {
            return null;
        }
    }
}
