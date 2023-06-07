public class Calculator {
    public int add(int... numbers) {

        int result = 0;

        for (int i : numbers) {

            result += i;

        }
        return result;

    }
}