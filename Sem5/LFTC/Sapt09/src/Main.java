import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Gramatica gramatica = new Gramatica();
        gramatica.readFromFile();

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Scrieti un ne-terminal: \n");
            String element = scanner.nextLine();
            char c = element.charAt(0);

            if (c == '0') {
                break;
            }

            if (!Character.isLetter(c)) {
                break;
            }

            gramatica.gasesteNeterminalInMembrulStang(c);
        }
    }
}
