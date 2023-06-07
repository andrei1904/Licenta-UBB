import java.io.*;
import java.text.DecimalFormat;
import java.util.Random;
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

    public static void generatePolynomials(String fileName, int maxMonomials, int maxDegree) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("");

            boolean[] exists = new boolean[maxDegree];
            for (int i = 0; i < maxDegree; i++) {
                exists[i] = false;
            }

            Random random = new Random();


            for (int i = 0; i < maxMonomials; i++) {

                double coeff = random.nextDouble() * 11;
                int degree = random.nextInt(maxDegree);

                while (coeff == 0) {
                    coeff = random.nextDouble() * 11;
                }

                if (random.nextInt() % 2 == 0) {
                    coeff *= -1;
                }

                while (exists[degree]) {
                    degree = random.nextInt(maxDegree);
                }
                exists[degree] = true;

                writer.append(String.valueOf(new DecimalFormat("##.##").format(coeff)))
                        .append(" ").append(String.valueOf(degree)).append(" ");

            }

            writer.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void resolveSequential(LinkedList polynomial, Queue monomials, int nrOfPolynomials) {
        for (int i = 1; i <= nrOfPolynomials; i++) {
            String fileName = readPath + i + ".txt";

            readPolynomialFromFile(fileName, monomials);
        }
        monomials.setMoreElements(false);

        while (true) {

            Monomial monomial = monomials.dequeue();
            if (monomial == null) {
                break;
            }

            polynomial.add(new Node(monomial));
        }
    }

    public static void resolveParallel(LinkedList polynomial, Queue monomials, int nrOfPolynomials, int p) {

        Reader reader = new Reader(nrOfPolynomials, monomials);
        reader.start();

        MyThread[] threads = new MyThread[p];
        for (int i = 0; i < p; i++) {

            threads[i] = new MyThread(polynomial, monomials);
            threads[i].start();
        }

        try {
            reader.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        for (int i = 0; i < p; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        int p = Integer.parseInt(args[0]);
        p -= 1;

        LinkedList polynomial = new LinkedList();

        Queue monomials = new Queue(500);

        int nrOfPolynomials = 5;
        readPath = "C:\\Fac\\Sem5\\PPD\\Teme\\Lab04\\data2\\pol";

        long startTime = System.currentTimeMillis();

//        resolveSequential(polynomial, monomials, nrOfPolynomials);
        resolveParallel(polynomial, monomials, nrOfPolynomials, p);

        long endTime = System.currentTimeMillis();

//        polynomial.writeListInFile("C:\\Fac\\Sem5\\PPD\\Teme\\Lab04\\result.txt");
        polynomial.writeListInFile("C:\\Fac\\Sem5\\PPD\\Teme\\Lab04\\resultParallel.txt");

        System.out.println();
        System.out.println(endTime - startTime);
    }

    public static class Reader extends Thread {
        private final int nrOfPolynomials;
        private final Queue monomials;


        public Reader(int nrOfPolynomials, Queue monomials) {
            this.nrOfPolynomials = nrOfPolynomials;
            this.monomials = monomials;
        }

        @Override
        public void run() {
            for (int i = 1; i <= nrOfPolynomials; i++) {
                String fileName = readPath + i + ".txt";

                readPolynomialFromFile(fileName, monomials);
            }
            monomials.setMoreElements(false);
        }
    }

    public static class MyThread extends Thread {
        private final LinkedList polynomial;
        private final Queue monomials;

        public MyThread(LinkedList polynomial, Queue monomials) {
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


