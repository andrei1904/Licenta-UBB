import utils.Reader;

import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Reader reader = new Reader();

        List<String> data = reader.readLexicalAtomsFromFile("C:\\Fac\\Sem5\\LFTC\\Lab01\\suma.txt");
        HashMap<String, Integer> atomCode = reader.readAtomCodeFromFile("C:\\Fac\\Sem5\\LFTC\\Lab01\\atomCode.txt");
        Analizator analizator = new Analizator(data, atomCode);

        data.forEach(System.out::println);

        try {
            analizator.analyzeData();
        } catch (Exception ex) {
            System.out.println("Error at: " + ex.getMessage());
        }

    }
}
