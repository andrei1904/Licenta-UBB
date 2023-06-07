import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class UiStateMachine {

    private StateMachine stateMachine;
    private final Scanner scanner;


    public UiStateMachine() {
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean run = true;

        while (run) {
            showMenu();
            String input = scanner.nextLine();

            try {
                switch (input) {
                    case "1" -> readFromConsole();
                    case "2" -> readFromFile();
                    case "3" -> showStates();
                    case "4" -> showAlphabet();
                    case "5" -> showTransitions();
                    case "6" -> showFinalStates();
                    case "7" -> showIfAccepted();
                    case "8" -> showLongestAcceptedPrefix();
                    default -> run = false;
                }
            } catch (FileNotFoundException ex) {
                System.out.println("File not found!");
            } catch (NullPointerException ex) {
                System.out.println("Add a state machine!");
            }
        }
    }

    public void showMenu() {
        System.out.println("Type the number of the option you want to chose: ");
        System.out.println("1 - Read from console");
        System.out.println("2 - Read from file");
        System.out.println("3 - Show state set");
        System.out.println("4 - Show alphabet");
        System.out.println("5 - Show transitions");
        System.out.println("6 - Show final states");
        System.out.println("7 - Check if a seqence is accepted");
        System.out.println("8 - Determine the longest prefix in a seqence");
        System.out.println("\n Write the number: ");
    }

    public void readFromConsole() {
        Set<State> states = new HashSet<>();
        Set<String> alphabet = new HashSet<>();
        Set<Transition> transitions = new HashSet<>();

        System.out.println("Write the alphabet of states: ");
        String[] input = scanner.nextLine().split(" ");
        String[] statesSymbol = input;

        System.out.println("Write the type for every element of the alphabet of states:" +
                " 1 - initial, 2 - final, 0 - other");
        input = scanner.nextLine().split(" ");
        for (int i = 0; i < input.length; i++) {
            StateType stateType = StateType.OTHER;

            switch (input[i]) {
                case "1" -> stateType = StateType.INITIAL;
                case "2" -> stateType = StateType.FINAL;
            }

            states.add(new State(stateType, statesSymbol[i]));
        }

        System.out.println("Write the number of transitions: ");
        int nrTransitions = Integer.parseInt(scanner.nextLine());

        System.out.println("Write the transitions: initial_satate final_state value");
        for (int i = 0; i < nrTransitions; i++) {
            input = scanner.nextLine().split(" ");

            transitions.add(new Transition(findState(states, input[0]),
                    findState(states, input[1]), input[2]));

            alphabet.add(input[2]);
        }

        stateMachine = new StateMachine(states, alphabet, transitions);
    }

    public void readFromFile() throws FileNotFoundException {
        System.out.println("Write the path to the file: ");

        Set<State> states = new HashSet<>();
        Set<String> alphabet = new HashSet<>();
        Set<Transition> transitions = new HashSet<>();

        String fileName = scanner.nextLine();
        File file = new File(fileName);
        Scanner fileScanner = new Scanner(file);

        String[] statesSymbol = fileScanner.nextLine().split(" ");

        String[] input = fileScanner.nextLine().split(" ");
        for (int i = 0; i < input.length; i++) {
            StateType stateType = StateType.OTHER;

            switch (input[i]) {
                case "1" -> stateType = StateType.INITIAL;
                case "2" -> stateType = StateType.FINAL;
            }

            states.add(new State(stateType, statesSymbol[i]));
        }

        int nrTransitions = Integer.parseInt(fileScanner.nextLine());

        for (int i = 0; i < nrTransitions; i++) {
            input = fileScanner.nextLine().split(" ");

            transitions.add(new Transition(findState(states, input[0]),
                    findState(states, input[1]), input[2]));

            alphabet.add(input[2]);
        }

        stateMachine = new StateMachine(states, alphabet, transitions);
    }

    private State findState(Set<State> states, String state) {
        for (State s : states) {
            if (s.getSymbol().equals(state)) {
                return s;
            }
        }

        return null;
    }

    public void showStates() throws NullPointerException {
        stateMachine.getStates().forEach(System.out::println);
    }

    public void showAlphabet() throws  NullPointerException {
        stateMachine.getAlphabet().forEach(System.out::println);
    }

    public void showTransitions() throws NullPointerException {
        stateMachine.getTransitions()
                .stream()
                .map(transition -> transition.getInitialState().getSymbol() + " -> " + transition.getFinalState().getSymbol() +
                        " : " + transition.getSymbol())
                .forEach(System.out::println);
    }

    public void showFinalStates() throws NullPointerException {
        stateMachine.getFinalStates().forEach(System.out::println);
    }

    public void showIfAccepted() {
        System.out.println("Write the seqence: ");
        String input = scanner.nextLine();

        if (stateMachine.isAccepted(input)) {
            System.out.println("It's accepted");
        } else {
            System.out.println("It's not accepted");
        }
    }

    public void showLongestAcceptedPrefix() {
        System.out.println("Write the seqence: ");
        String input = scanner.nextLine();

        String res = stateMachine.longestAcceptedPrefix(input);

        if (res != null) {
            System.out.println(res);
        } else {
            System.out.println("Doesn't exist");
        }
    }
}
