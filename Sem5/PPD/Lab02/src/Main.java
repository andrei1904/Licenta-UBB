import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int n = 10000000;
        int[] a = new int[n];
        int[] b = new int[n];
        int boundary = 100000;

        Random random = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(boundary);
            b[i] = random.nextInt(boundary);
        }

//        System.out.println(Arrays.toString(a));
//        System.out.println(Arrays.toString(b));

        long start, end;

        double[] c = new double[n];

        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            c[i] = Math.sqrt(a[i] * a[i] * a[i] * a[i] * a[i] +
                    b[i] * b[i] * b[i] * b[i] * b[i]);
        }
        end = System.currentTimeMillis();

//        System.out.println(Arrays.toString(c));

        System.out.println(end - start);

        int p = 4;
        int s = 0, e = 0;
        int cat = n / p, rest = n % p;
        double[] d = new double[n];
        MyThread[] threads = new MyThread[p];

        start = System.currentTimeMillis();
        for (int i = 0; i < p; i++) {
            e = s + cat + (rest > 0 ? 1 : 0);
            rest -= 1;

            threads[i] = new MyThread(s, e, a, b, d);
            threads[i].start();

//            System.out.println(s + " " + e);
            s = e;
        }

        for (int i = 0; i < p; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        end = System.currentTimeMillis();

//        System.out.println(Arrays.toString(c));
//        System.out.println(Arrays.toString(d));

        System.out.println(end - start);

//        double[] f = new double[n];
//        MyThread2[] threads2 = new MyThread2[p];
//
//        start = System.currentTimeMillis();
//        for (int i = 0; i < p; i++) {
//            e = s + cat + (rest > 0 ? 1 : 0);
//            rest -= 1;
//
//            threads2[i] = new MyThread2(p, i, s, n, a, b, f);
//            threads2[i].start();
//
////            System.out.println(s + " " + e);
//            s = e;
//        }

//        for (int i = 0; i < p; i++) {
//            try {
//                threads2[i].join();
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
//        }

//        end = System.currentTimeMillis();
//
//        System.out.println(end - start);
    }

    public static class MyThread extends Thread {
        private final int left, right;
        private final int[] a, b;
        private final double[] c;

        public MyThread(int l, int r, int[] a, int[] b, double[] c) {
            this.left = l;
            this.right = r;
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public void run() {
            for (int i = left; i < right; i++) {
                c[i] = Math.sqrt(a[i] * a[i] * a[i] * a[i] * a[i] +
                        b[i] * b[i] * b[i] * b[i] * b[i]);
            }
        }
    }

    public static class MyThread2 extends Thread {
        private final int left, right;
        private final int[] a, b;
        private final double[] c;
        private final int id;
        private final int p;

        public MyThread2(int p, int id, int l, int r, int[] a, int[] b, double[] c) {
            this.left = l;
            this.right = r;
            this.a = a;
            this.b = b;
            this.c = c;
            this.id = id;
            this.p = p;
        }

        @Override
        public void run() {
            for (int i = left; i < right; i++) {
                if (i % p == id) {
                    c[i] = Math.sqrt(a[i] * a[i] * a[i] * a[i] * a[i] +
                            b[i] * b[i] * b[i] * b[i] * b[i]);
                }
            }
        }
    }
}
