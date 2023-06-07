abstract class Abstract {
    private int val;
    Abstract() {
        val = 10;
    }
    public void set(int val) {
        this.val = val+getValue();
    }

    public abstract int getValue();
    public int get() {
        return val;
    }

    protected int computeX(int a, int b) {
        return 1;
    }
}

class G5 extends Abstract {
    public int getValue() {
        return 1111;
    }

    @Override
    protected int computeX(Integer a, Integer b) {
        return 2;
    }

    public static void main(String[] args) {
        G5 obj = new G5();
        obj.set(11);
        System.out.println(obj.get());
    }
}
