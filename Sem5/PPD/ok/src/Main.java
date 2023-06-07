public class Main {
    public static void main(String[] args) throws InterruptedException {
        AtomicNr a = new AtomicNr(5);

        for (int i = 0; i < 2; i++) {
            Thread t1 = new Thread(()->{ a.Add(3); });
            Thread t2 = new Thread(()->{ a.Add(2); });
            Thread t3 = new Thread(()->{ a.Minus(1); });
            Thread t4 = new Thread(()->{ a.Minus(1); });

            t1.run(); t2.run(); t3.run(); t4.run();
            //t1.start(); t2.start(); t3.start(); t4.start();
            t1.join(); t2.join(); t3.join(); t4.join();
        }
        System.out.println("a = " + a);
    }
};

class AtomicNr{
    private int nr;
    public AtomicNr(int nr){ this.nr = nr; }
    public void Add(int nr) { this.nr += nr; }
    public void Minus(int nr){ this.nr -= nr; }

    @Override
    public String toString() { return "" + this.nr; }
};