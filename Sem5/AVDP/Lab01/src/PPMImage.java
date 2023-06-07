import java.io.*;
import java.util.Scanner;

public class PPMImage {

    private int rows;
    private int cols;
    private int maxValue;

    private double[][] Y;
    private double[][] Cb;
    private double[][] Cr;

    public PPMImage() {}

    public PPMImage(int rows, int cols, int maxValue) {
        this.rows = rows;
        this.cols = cols;
        this.maxValue = maxValue;
        Y = new double[rows + 8][cols + 8];
        Cb = new double[rows + 8][cols + 8];
        Cr = new double[rows + 8][cols + 8];
    }

    public void readImage(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            scanner.nextLine();
            scanner.nextLine();

            cols = scanner.nextInt();
            rows = scanner.nextInt();
            maxValue = scanner.nextInt();

            Y = new double[rows + 8][cols + 8];
            Cb = new double[rows + 8][cols + 8];
            Cr = new double[rows + 8][cols + 8];

            System.out.println("Reading from: " + filename + " of size " + rows + " " + cols);

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    convertRgbToYCbCr(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
                            i, j);
                }
            }
            scanner.close();

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public void convertRgbToYCbCr(int red, int green, int blue, int i, int j) {
        Y[i][j] = 0.299 * red + 0.587 * green + 0.114 * blue;
        Cb[i][j] = 128 - 0.169 * red - 0.331 * green + 0.499 * blue;
        Cr[i][j] = 128 + 0.499 * red - 0.418 * green - 0.0813 * blue;
    }

    public void writeImage(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);

            writer.write("P3\n");
            writer.write("# CREATOR: GIMP PNM Filter Version 1.1\n");
            writer.write(cols + " " + rows + "\n");
            writer.write(maxValue + "\n");

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int[] rgbResult = convertYCbCrToRgb(i, j);

                    for (int index = 0; index < 3; index++) {
                        writer.write(rgbResult[index] + "\n");
                    }
                }
            }

            writer.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private int[] convertYCbCrToRgb(int i, int j) {

        int[] rgbResult = new int[3];

        rgbResult[0] = (int) (Y[i][j] + (1.402 * (Cr[i][j] - 128)));
        rgbResult[1] = (int) (Y[i][j] - (0.344 * (Cb[i][j] - 128)) - (0.714 * (Cr[i][j] - 128)));
        rgbResult[2] = (int) (Y[i][j] + (1.772 * (Cb[i][j] - 128)));

        for (int index = 0; index < 3; index++) {
            rgbResult[index] = validateRgbColor(rgbResult[index]);
        }

        return rgbResult;
    }

    private int validateRgbColor(int color) {
        if (color < 0) {
            color = 0;
            return color;
        }

        if (color > 255) {
            color = 255;
        }

        return color;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public double[][] getY() {
        return Y;
    }

    public double[][] getCb() {
        return Cb;
    }

    public double[][] getCr() {
        return Cr;
    }

}
