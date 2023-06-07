import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Gramatica {

    private List<String> productii;
    private final List<Character> simboluriNeterminale = new LinkedList<>();

    public void readFromFile() {
        productii = new LinkedList<>();

        try {
            File file = new File("C:\\Fac\\Sem5\\LFTC\\Sapt09\\src\\data.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                productii.add(line);
            }

            setSimboluriNeterminale(productii);

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    private void setSimboluriNeterminale(List<String> productii) {
        for (String productie : productii) {
            for (Character c : productie.toCharArray()) {
                if (c == '-') {
                    break;
                }

                simboluriNeterminale.add(c);
            }
        }
    }

    public void gasesteNeterminalInMembrulStang(char c) {

        if (simboluriNeterminale.contains(c)) {

            for (String productie : productii) {

                for (char simbol : productie.toCharArray()) {
                    if (simbol == '-') {
                        break;
                    }

                    if (simbol == c) {
                        System.out.println(productie);
                    }
                }
            }
        }
    }
}
