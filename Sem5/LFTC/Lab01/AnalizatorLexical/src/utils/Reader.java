package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Reader {

    public List<String> readLexicalAtomsFromFile(String fileName) {

        List<String> result = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String[] line = reader.nextLine().split(" ");
                for (String element : line) {
                    if (!element.equals("")) {
                        delimit(element, result);
                    }
                }
            }
            reader.close();

        } catch (FileNotFoundException exception) {
            System.out.println("File not found!");
        }

        return result;
    }

    public void delimit(String data, List<String> result) {

        // add ";" and check the rest of data
        if (data.contains(";")) {
            delimit(data.substring(0, data.indexOf(";")), result);
            result.add(";");
            delimit(data.substring(data.indexOf(";") + 1), result);
            return;
        }

        // add "(" and check the rest of data
        if (data.contains("(")) {
            delimit(data.substring(0, data.indexOf("(")), result);
            result.add("(");
            delimit(data.substring(data.indexOf("(") + 1), result);

            return;
        }

        // add ")" and check the rest of data
        if (data.contains(")")) {
            delimit(data.substring(0, data.indexOf(")")), result);
            result.add(")");
            delimit(data.substring(data.indexOf(")") + 1), result);

            return;
        }

        if (!data.equals("")) {
            result.add(data);
        }
    }

    public HashMap<String, Integer> readAtomCodeFromFile(String fileName) {
        HashMap<String, Integer> result = new HashMap<>();

        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String[] line = reader.nextLine().split(" ");
                result.put(line[0], Integer.valueOf(line[1]));
            }
            reader.close();

        } catch (FileNotFoundException exception) {
            System.out.println("File not found!");
        }

        return result;
    }


}
