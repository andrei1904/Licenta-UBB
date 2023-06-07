import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    private static String readPath = "";

    public static void readPolynomialFromFile(String fileName, Queue monomials) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                double coefficient = scanner.nextDouble();
                int exponent = scanner.nextInt();

                monomials.enqueue(new Monomial(exponent, coefficient));
            }

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public static void resolveSequential(LinkedList polynomial, Queue monomials, int nrOfPolynomials, int p1) {
        Reader[] readerThreads = new Reader[p1];

        int start = 1, end;
        int nOfFiles = nrOfPolynomials / p1, remaningFiles = nrOfPolynomials % p1;

        for (int i = 0; i < p1; i++) {
            end = start + nOfFiles + (remaningFiles > 0 ? 1 : 0);
            remaningFiles -= 1;

            readerThreads[i] = new Reader(monomials, start, end);
            readerThreads[i].start();

            start = end;
        }

        while (true) {

            Monomial monomial = monomials.dequeue();
            if (monomial == null) {
                break;
            }

            polynomial.add(new Node(monomial));
        }

        for (int i = 0; i < p1; i++) {
            try {
                readerThreads[i].join();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void resolveParallel(LinkedList polynomial, Queue monomials, int nrOfPolynomials, int p, int p1) {

        Reader[] readerThreads = new Reader[p1];

        int start = 1, end;
        int nOfFiles = nrOfPolynomials / p1, remaningFiles = nrOfPolynomials % p1;

        for (int i = 0; i < p1; i++) {
            end = start + nOfFiles + (remaningFiles > 0 ? 1 : 0);
            remaningFiles -= 1;

            readerThreads[i] = new Reader(monomials, start, end);
            readerThreads[i].start();

            start = end;
        }

        Worker[] workerThreads = new Worker[p];
        for (int i = 0; i < p; i++) {

            workerThreads[i] = new Worker(polynomial, monomials);
            workerThreads[i].start();
        }

        for (int i = 0; i < p1; i++) {
            try {
                readerThreads[i].join();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        for (int i = 0; i < p; i++) {
            try {
                workerThreads[i].join();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        int p = Integer.parseInt(args[0]);
        int p1 = 2;
        p -= p1;

        LinkedList polynomial = new LinkedList();

        Queue monomials = new Queue(3000, p1);

        int nrOfPolynomials = 2;
        readPath = "C:\\Fac\\Sem5\\PPD\\Teme\\Lab05\\small_data\\pol";

        long startTime = System.currentTimeMillis();

//        resolveSequential(polynomial, monomials, nrOfPolynomials, p1);
        resolveParallel(polynomial, monomials, nrOfPolynomials, p, p1);

        long endTime = System.currentTimeMillis();

//        polynomial.writeListInFile("C:\\Fac\\Sem5\\PPD\\Teme\\Lab05\\result.txt");
        polynomial.writeListInFile("C:\\Fac\\Sem5\\PPD\\Teme\\Lab05\\resultParallel.txt");

        System.out.println();
        System.out.println(endTime - startTime);
    }

    public static class Reader extends Thread {
        private final Queue monomials;
        private final int start;
        private final int end;

        public Reader(Queue monomials, int start, int end) {
            this.monomials = monomials;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                String fileName = readPath + i + ".txt";

                readPolynomialFromFile(fileName, monomials);
            }
            monomials.setNoMoreElements();
        }
    }

    public static class Worker extends Thread {
        private final LinkedList polynomial;
        private final Queue monomials;

        public Worker(LinkedList polynomial, Queue monomials) {
            this.polynomial = polynomial;
            this.monomials = monomials;
        }

        @Override
        public void run() {

            while (true) {

                Monomial monomial;
                monomial = monomials.dequeue();

                if (monomial == null) {
                    break;
                }

                polynomial.add(new Node(monomial));
            }
        }
    }
}


