import domain.FIPelement;
import utils.AnalizatorException;
import utils.MyMap;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Analizator {

    private final List<String> data;

    private final HashMap<String, Integer> atomCode;

    private final MyMap<String, Integer> identificatorTS;

    private final MyMap<String, Integer> constantTS;

    private final List<FIPelement> FIP;

    private final List<String> keywords = Arrays.asList("#include", "<iostream>", "using", "namespace", "std", "int",
            "double", "struct", "void", "while", "if", "else", "cin", "cout", "return", "=", "<<", ">>");

    private final List<String> relationalOperators = Arrays.asList("<", ">", "==", "!=", "<=", ">=");

    private final List<String> arithmeticOperators = Arrays.asList("+", "-", "*", "/", "%");

    private final List<String> separators = Arrays.asList(",", ";", "(", ")", "{", "}");

    public Analizator(List<String> data, HashMap<String, Integer> atomCode) {
        this.data = data;
        this.atomCode = atomCode;
        this.identificatorTS = new MyMap<>();
        this.constantTS = new MyMap<>();
        this.FIP = new ArrayList<>();
    }

    // check if an atom is ID
    public boolean isIdentificator(String atom) {
        return atom.matches("^[a-zA-Z][a-zA-Z0-9]*") && atom.length() <= 8;
    }

    // check if an atom is CONST
    public boolean isConstant(String atom) {
        if (atom.equals("0") || atom.matches("^[+-]?[1-9][0-9]*")) {
            return true;
        }

        return atom.matches("^[+-]?[0-9]*\\.[0-9]*");
    }

    public void analyzeData() throws Exception {
        // generate TS for ID
        generateIdTS();
        // generate TS for CONST
        generateConstTS();

        for (String atom : data) {
            // check if the atom is a keyword, operator or separator
            if (keywords.contains(atom) || relationalOperators.contains(atom) ||
                    arithmeticOperators.contains(atom) || separators.contains(atom)) {

                FIP.add(new FIPelement(atomCode.get(atom), -1));

            } else {
                // check if the atom is ID
                if (isIdentificator(atom)) {
                    FIP.add(new FIPelement(atomCode.get("ID"), identificatorTS.get(atom)));

                    // check if the atom is CONST
                } else if (isConstant(atom)) {
                    FIP.add(new FIPelement(atomCode.get("CONST"), constantTS.get(atom)));
                } else {
                    throw new AnalizatorException(atom);
                }
            }
        }

        writeFipInFile(FIP, "fip.txt");
    }

    public void generateIdTS() {
        int i = 0;
        for (String atom : data) {
            if (!keywords.contains(atom) && isIdentificator(atom) &&
                    identificatorTS.get(atom) == null) {

                identificatorTS.put(atom, i);
                i++;
            }
        }

        writeInFile(identificatorTS, "idTS.txt");
    }

    public void generateConstTS() {
        int i = 0;
        for (String atom : data) {
            if (!keywords.contains(atom) && isConstant(atom) &&
                    constantTS.get(atom) == null) {

                constantTS.put(atom, i);
                i++;
            }
        }

        writeInFile(constantTS, "constTS.txt");
    }

    public void writeInFile(MyMap<String, Integer> data, String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("");

            for (String element : data.getKeys()) {
                writer.append(element).append(" ").append(String.valueOf(data.get(element)))
                        .append("\n");
            }
            writer.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void writeFipInFile(List<FIPelement> data, String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("");

            for (FIPelement element : data) {
                writer.append(String.valueOf(element.getAtomCode())).append(" ")
                        .append(String.valueOf(element.getTsCode())).append("\n");
            }
            writer.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
