public class ma602 {
    Integer a = 10;

    public int f(int x, int y) throws Exception {
        if (x * y < 0) {
            throw new Exception();
        }
        return x + y;
    }

    public int g(int x, int y) {
        try {
            a = f(x, y);
            return a;
        } catch (Exception ne) {
            a = a * a + 1;
            return a;
        } finally {
            a = a * a;
            return a + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ma602().g(1,-1));
    }
}
