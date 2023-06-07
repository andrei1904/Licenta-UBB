import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static int N, M, n, m, p;
    public static double[][] matrix;
    public static double[][] filter;
    public static CyclicBarrier barrier;

    public static void readData(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            N = scanner.nextInt();
            M = scanner.nextInt();
            n = scanner.nextInt();
            m = scanner.nextInt();

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public static void readMatrix(String fileName, int n, int m, double[][] matrix) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = scanner.nextDouble();
                }
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public static void writeMatrix(double[][] matrix, int n, int m, String fileName) {
        DecimalFormat df = new DecimalFormat("0.00");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("");

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    writer.append(String.valueOf(df.format(matrix[i][j]))).append(" ");
                }

                writer.append("\n");
            }
            writer.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static double applyFilter(double[][] matrix, int x, int y) {
        double sum = 0;

        int pos_x = x - n / 2;
        int pos_y = y - m / 2;

        for (int i = pos_x, posIF = 0; i < n + pos_x; i++, posIF++) {
            for (int j = pos_y, posJF = 0; j < m + pos_y; j++, posJF++) {

                if (i < 0 && j >= 0 && j <= M - 1) {
                    sum += matrix[0][j] * filter[posIF][posJF];
                } else if (i >= 0 && i <= N - 1 && j < 0) {
                    sum += matrix[i][0] * filter[posIF][posJF];
                } else if (i >= N && j >= 0 && j <= M - 1) {
                    sum += matrix[N - 1][j] * filter[posIF][posJF];
                } else if (i >= 0 && i <= N - 1 && j >= M) {
                    sum += matrix[i][M - 1] * filter[posIF][posJF];
                } else if (i < 0 && j < 0) {
                    sum += matrix[0][0] * filter[posIF][posJF];
                } else if (i >= N && j >= M) {
                    sum += matrix[N - 1][M - 1] * filter[posIF][posJF];
                } else if (i < 0 && j >= M) {
                    sum += matrix[0][M - 1] * filter[posIF][posJF];
                } else if (i >= N && j < 0) {
                    sum += matrix[N - 1][0] * filter[posIF][posJF];
                } else {
                    sum += matrix[i][j] * filter[posIF][posJF];
                }
            }
        }

        return sum;
    }

    public static void resolveParaller() {

        int start = 0, end;
        int noLines = N / p, remainingLines = N % p;

        MyThread[] threads = new MyThread[p];

        for (int i = 0; i < p; i++) {
            end = start + noLines + (remainingLines > 0 ? 1 : 0);
            remainingLines -= 1;

            threads[i] = new MyThread(start, end, N, M, n, matrix, barrier);
            threads[i].start();

            start = end;
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

        p = Integer.parseInt(args[0]);
        readData("C:\\Fac\\Sem5\\PPD\\Teme\\Lab02\\Java\\in_data.txt");

        matrix = new double[N][M];
        filter = new double[n][m];

        readMatrix("C:\\Fac\\Sem5\\PPD\\Teme\\Lab01\\date.txt", N, M, matrix);
        readMatrix("C:\\Fac\\Sem5\\PPD\\Teme\\Lab02\\Java\\filter.txt", n, m, filter);

        barrier = new CyclicBarrier(p);

        double startTime = System.currentTimeMillis();
        resolveParaller();
        double endTime = System.currentTimeMillis();

        writeMatrix(matrix, N, M, "C:\\Fac\\Sem5\\PPD\\Teme\\Lab02\\Java\\resultParallel.txt");

        System.out.println();
        System.out.println(endTime - startTime);
    }

    public static class MyThread extends Thread {

        private final int left, right;
        private final int N;
        private final int M;
        private final int n;
        private final double[][] matrix, utilMatrix;
        private final CyclicBarrier barrier;

        public MyThread(int start, int end, int N, int M, int n, double[][] matrix, CyclicBarrier barrier) {
            this.left = start;
            this.right = end;
            this.N = N;
            this.M = M;
            this.n = n;
            this.matrix = matrix;
            this.utilMatrix = new double[end - start + n][M];
            this.barrier = barrier;
        }

        public int copyData() {
            int line = 0;

            if (left != 0 && left - n / 2 >= 0) {
                for (int i = left - n / 2; i < left; i++) {
                    if (M >= 0) System.arraycopy(matrix[i], 0, utilMatrix[line], 0, M);

                    line++;
                }
            }

            int index = line;
            for (int i = left; i < right; i++) {
                if (M >= 0) System.arraycopy(matrix[i], 0, utilMatrix[index], 0, M);

                index++;
            }

            if (right != N && right + n / 2 <= N) {
                for (int i = right; i < right + n / 2; i++) {
                    if (M >= 0) System.arraycopy(matrix[i], 0, utilMatrix[index], 0, M);

                    index++;
                }
            }

            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException exception) {
                exception.printStackTrace();
            }

            return line;
        }

        @Override
        public void run() {
            int line = copyData();

            for (int i = left; i < right; i++) {
                for (int j = 0; j < M; j++) {

                    double result = applyFilter(utilMatrix, i - left + line, j);

                    matrix[i][j] = result;
                }
            }
        }
    }
}
