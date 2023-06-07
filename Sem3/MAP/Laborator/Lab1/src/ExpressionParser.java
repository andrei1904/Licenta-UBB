public class ExpressionParser {

    String[] args;
    Complex[] numbers;

    public ExpressionParser(String[] args) {
        this.args = args;
        numbers = new Complex[args.length / 2 + 1];
    }

    boolean isValid() {
        if (args.length < 3) {
            return false;
        }

        for (int i = 0; i < args.length; i++) {
            if (i % 2 == 0) {
                if (!args[i].matches("^[+-]*[0-9]*[+-]*[0-9]*\\*?i?$")) { // verific numarul
                    return false;
                }

            } else {
                if (!args[i].matches("^[+-/*]$")) { // verific operatorul
                    return false;
                }
            }
        }
        buildArgs();
        createComplexExpression();
        return true;
    }

    private void buildArgs() {
        double real;
        double imaginar;

        for (int i = 0; i < args.length; i += 2) {

            if (!args[i].matches(".*i")) { // are doar parte reala
                real = Double.parseDouble(args[i]);
                imaginar = 0;

            } else {
                if (!args[i].matches(".*\\*i")) {
                    if (args[i].matches(".*-i")) { // daca apare -i
                        if (args[i].length() > 2) // daca e si parte reala
                            real = Double.parseDouble(args[i].substring(0, args[i].length() - 2));
                        else
                            real = 0;

                        imaginar = -1;

                    } else { // daca apare +i

                        if (args[i].length() > 2)
                            real = Double.parseDouble(args[i].substring(0, args[i].length() - 2));
                        else
                            real = 0;

                        imaginar = 1;
                    }
                } else {
                    if (args[i].matches("^[+-]*[0-9]*\\*i$")) { // are doar parte imaginara *i
                        real = 0;
                        imaginar = Double.parseDouble(args[i].substring(0, args[i].length() - 2));

                    } else { // are si parte reala si imaginara
                        int index = 0;

                        while (args[i].charAt(index) != '+' && args[i].charAt(index) != '-') { // lungimea numarului
                            index++;
                        }

                        real = Double.parseDouble(args[i].substring(0, index));
                        imaginar = Double.parseDouble(args[i].substring(index, args[i].length() - 2));
                    }
                }
            }

            Complex new_number = new Complex(real, imaginar);
            numbers[i / 2] = new_number;
        }
    }


    public Complex createComplexExpression() {
        ExpressionFactory exp = ExpressionFactory.getInstance();
        return switch (args[1]) {
            case "+" -> exp.createExpression(Operation.ADDITION, numbers).execute();
            case "-" -> exp.createExpression(Operation.SUBSTRACTION, numbers).execute();
            case "*" -> exp.createExpression(Operation.MULTIPLICATION, numbers).execute();
            case "/" -> exp.createExpression(Operation.DIVISION, numbers).execute();
            default -> null;
        };
    }
}
