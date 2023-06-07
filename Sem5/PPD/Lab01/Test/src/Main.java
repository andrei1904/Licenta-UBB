import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        int n = 10;

        for (int i = 0; i < n; i++) {
            a.add(i + 1);
            b.add(i + 1);
        }

        List<Integer> c = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            c.add(a.get(i) + b.get(i));
            System.out.println(c.get(i));
        }
    }
}
