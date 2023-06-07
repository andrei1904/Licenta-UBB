import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double startTime = System.currentTimeMillis();

        Data data = readData(args[0]);

//        generateMatrix(data.N, data.M, 10);

        resolve(data);

        double endTime = System.currentTimeMillis();

        System.out.println("0");
        System.out.println(endTime - startTime);
    }

    public static void resolve(Data data) {
        double[][] matrix_F = new double[data.N][data.M];
        readMatrix(matrix_F, data.N, data.M);

        // resolve sequential
        double[][] matrix_V = new double[data.N][data.M];
        resolveSequential(matrix_F, matrix_V, data);
        writeResult(matrix_V, data.N, data.M, "C:\\Fac\\Sem5\\PPD\\Teme\\Lab01\\out_sequential_data.txt");

        if (sameFileContent("C:\\Fac\\Sem5\\PPD\\Teme\\Lab01\\date.txt", "C:\\Fac\\Sem5\\PPD\\Teme\\Lab01\\out_sequential_data.txt")) {
            System.out.println("Error filtering the image!");
            return;
        }

        // resolve parallel lines
//        double[][] matrix_V_PL = new double[data.N][data.M];
//        resolveParallelLines(matrix_F, matrix_V_PL, data);
//        writeResult(matrix_V_PL, data.N, data.M, "C:\\Fac\\Sem5\\PPD\\Teme\\Lab01\\out_parallel_lines_data.txt");
//
//        if (!sameFileContent("C:\\Fac\\Sem5\\PPD\\Teme\\Lab01\\out_sequential_data.txt", "C:\\Fac\\Sem5\\PPD\\Teme\\Lab01\\out_parallel_lines_data.txt")) {
//            System.out.println("Difference between sequential and parallel!");
//            return;
//        }

        // resolve parallel distributed
//        double[][] matrix_V_PD = new double[data.N][data.M];
//        resolveParallelDistributed(matrix_F, matrix_V_PD, data);
//        writeResult(matrix_V_PD, data.N, data.M, "C:\\Fac\\Sem5\\PPD\\Teme\\Lab01\\out_parallel_distributed_data.txt");
//
//        if (!sameFileContent("C:\\Fac\\Sem5\\PPD\\Teme\\Lab01\\out_sequential_data.txt", "C:\\Fac\\Sem5\\PPD\\Teme\\Lab01\\out_parallel_distributed_data.txt")) {
//            System.out.println("Difference between sequential and parallel!");
//
//        }

    }

    public static Data readData(String nrThreads) {
        int N = 0, M = 0, n = 0, m = 0, p = 0;

        try {
            File file = new File("C:\\Fac\\Sem5\\PPD\\Teme\\Lab01\\in_data.txt");
            Scanner scanner = new Scanner(file);

            N = Integer.parseInt(scanner.nextLine());
            M = Integer.parseInt(scanner.nextLine());
            n = Integer.parseInt(scanner.nextLine());
            m = Integer.parseInt(scanner.nextLine());
//            p = Integer.parseInt(scanner.nextLine());
            p = Integer.parseInt(nrThreads);

            scanner.close();

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

        return new Data(N, M, n, m, p);
    }

    public static void writeResult(double[][] matrix, int n, int m, String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("");

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    writer.append(String.valueOf(matrix[i][j])).append(" ");
                }

                writer.append("\n");
            }
            writer.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static boolean sameFileContent(String fileName1, String fileName2) {
        try {
            File file1 = new File(fileName1);
            File file2 = new File(fileName2);

            long size = Files.size(file1.toPath());
            if (size != Files.size(file2.toPath())) {
                return false;
            }

            if (size < 2048) {
                return Arrays.equals(Files.readAllBytes(file1.toPath()),
                        Files.readAllBytes(file2.toPath()));
            }

            // Compare character-by-character
            try (BufferedReader bf1 = Files.newBufferedReader(file1.toPath());
                 BufferedReader bf2 = Files.newBufferedReader(file2.toPath())) {
                int ch;
                while ((ch = bf1.read()) != -1) {
                    if (ch != bf2.read()) {
                        return false;
                    }
                }
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void generateMatrix(int n, int m, int bound) {
        Random random = new Random();

        double[][] matrix = new double[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = random.nextInt(bound);
            }
        }

        writeResult(matrix, n, m, "C:\\Fac\\Sem5\\PPD\\Teme\\Lab01\\date.txt");
    }

    public static void readMatrix(double[][] matrix, int n, int m) {
        try {
            File file = new File("C:\\Fac\\Sem5\\PPD\\Teme\\Lab01\\date.txt");
            Scanner scanner = new Scanner(file);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = scanner.nextDouble();
                }
            }

            scanner.close();

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public static void resolveSequential(double[][] input, double[][] output, Data data) {

        for (int i = 0; i < data.N; i++) {
            for (int j = 0; j < data.M; j++) {
                output[i][j] = applyFilter(input, data.n, data.m, data.N, data.M, i, j);
            }
        }
    }

    public static void resolveParallelLines(double[][] input, double[][] output, Data data) {
        int start = 0, end;
        int noLines = data.N / data.p, remainingLines = data.N % data.p;

        MyThreadLines[] threads = new MyThreadLines[data.p];

        for (int i = 0; i < data.p; i++) {
            end = start + noLines + (remainingLines > 0 ? 1 : 0);
            remainingLines -= 1;

            threads[i] = new MyThreadLines(start, end, data, input, output);
            threads[i].start();

            start = end;
        }

        for (int i = 0; i < data.p; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

    }

    public static void resolveParallelDistributed(double[][] input, double[][] output, Data data) {
        int start = 0, end = 0;
        int startI = 0, endI = 0;
        int startJ = 0, endJ = 0;
        int total = data.N * data.M;
        int elementsForThreads = total / data.p;
        int remainingElements = total % data.p;

        MyThreadDistributed[] threads = new MyThreadDistributed[data.p];

        for (int i = 0; i < data.p; i++) {
            start = end;
            startI = endI;
            startJ = endJ;
            if (startJ == data.M - 1) {
                startJ = 0;
            }
            end = start + elementsForThreads + (remainingElements > 0 ? 1 : 0);
            remainingElements -= 1;

            endI = end / data.M;
            endJ = end % data.M;
            if (endJ == 0) {
                endJ = data.M - 1;
            }

            threads[i] = new MyThreadDistributed(startI, endI, startJ, endJ, data, input, output);
            threads[i].start();
        }

        for (int i = 0; i < data.p; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static double applyFilter(double[][] matrix, int n, int m, int N, int M, int x, int y) {
        double sum = 0;

        int pos_x = x - n / 2;
        int pos_y = y - m / 2;

        for (int i = pos_x; i < n + pos_x; i++) {
            for (int j = pos_y; j < m + pos_y; j++) {

                if (i < 0 && j >= 0 && j <= M - 1) {
                    sum += matrix[0][j];
                } else if (i >= 0 && i <= N - 1 && j < 0) {
                    sum += matrix[i][0];
                } else if (i >= N && j >= 0 && j <= M - 1) {
                    sum += matrix[N - 1][j];
                } else if (i >= 0 && i <= N - 1 && j >= M) {
                    sum += matrix[i][M - 1];
                } else if (i < 0 && j < 0) {
                    sum += matrix[0][0];
                } else if (i >= N && j >= M) {
                    sum += matrix[N - 1][M - 1];
                } else if (i < 0 && j >= M) {
                    sum += matrix[0][M - 1];
                } else if (i >= N && j < 0) {
                    sum += matrix[N - 1][0];
                } else {
                    sum += matrix[i][j];
                }
            }
        }

        return sum / (n * m);
    }

    public static class Data {
        private final int N, M, n, m, p;

        public Data(int n, int m, int n1, int m1, int p) {
            N = n;
            M = m;
            this.n = n1;
            this.m = m1;
            this.p = p;
        }
    }

    public static class MyThreadLines extends Thread {
        private final int left, right;
        private final Data data;
        private final double[][] input, output;

        public MyThreadLines(int left, int right, Data data, double[][] input, double[][] output) {
            this.left = left;
            this.right = right;
            this.data = data;
            this.input = input;
            this.output = output;
        }

        @Override
        public void run() {
            for (int i = left; i < right; i++) {
                for (int j = 0; j < data.M; j++) {
                    output[i][j] = applyFilter(input, data.n, data.m, data.N, data.M, i, j);
                }
            }
        }
    }

    public static class MyThreadDistributed extends Thread {
        private final int startI, endI;
        private final int startJ, endJ;
        private final Data data;
        private final double[][] input, output;

        public MyThreadDistributed(int startI, int endI, int startJ, int endJ, Data data, double[][] input, double[][] output) {
            this.startI = startI;
            this.endI = endI;
            this.startJ = startJ;
            this.endJ = endJ;
            this.data = data;
            this.input = input;
            this.output = output;
        }

        @Override
        public void run() {
            for (int i = startI; i <= endI && i < data.N; i++) {
                int j;
                if (i == startI) {
                    j = startJ;
                } else {
                    j = 0;
                }
                while (j < data.M) {
                    if (j == endJ && i == endI) {
                        break;
                    }

                    try {
                        output[i][j] = applyFilter(input, data.n, data.m, data.N, data.M, i, j);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        System.out.println(i + " " + j);
                    }
                    j++;
                }
            }

        }
    }
}
