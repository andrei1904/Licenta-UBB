import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Encoder {

    private static final int SIZE = 8;

    private final PPMImage image;

    private final Block[] blockY;
    private final Block[] blockCb;
    private final Block[] blockCr;
    private final int nOfBlocks;
    private final int[][] Q;
    private final double[] outputArray;

    public Encoder(PPMImage image) {
        this.image = image;

        addPadding();

        nOfBlocks = (image.getRows() * image.getCols()) / (SIZE * SIZE);
        blockY = new Block[nOfBlocks];
        blockCb = new Block[nOfBlocks];
        blockCr = new Block[nOfBlocks];

        Q = new int[SIZE][SIZE];
        readQuantizationMatrix(Q);

        outputArray = new double[9999999];
    }

    public void encode() {

        splitImage();

        reverseSubsamplingCbCr();

        substractFromAllBlocks();

        applyForwardDCT();

        applyQuantizationForAllBlocks();

        applyEntropyEncoding();
    }

    private void splitImage() {
        int nr = 0;
        for (int i = 0; i < image.getRows(); i += SIZE) {
            for (int j = 0; j < image.getCols(); j += SIZE) {

                blockY[nr] = new Block(splitInBlock(i, j, image.getY()), "Y", i, j);
                blockCb[nr] = new Block(splitInBlockSubsampling(i, j, image.getCb()), "Cb", i, j);
                blockCr[nr] = new Block(splitInBlockSubsampling(i, j, image.getCr()), "Cr", i, j);
                nr++;
            }
        }
    }

    private double[][] splitInBlock(int i, int j, double[][] matrix) {
        double[][] values = new double[SIZE][SIZE];

        for (int x = i, posX = 0; x < i + SIZE; x++, posX++) {
            for (int y = j, posY = 0; y < j + SIZE; y++, posY++) {

                values[posX][posY] = matrix[x][y];
            }
        }

        return values;
    }

    private double[][] splitInBlockSubsampling(int i, int j, double[][] matrix) {
        double[][] values = new double[SIZE / 2][SIZE / 2];

        for (int x = i, posX = 0; x < i + SIZE; x += 2, posX++) {
            for (int y = j, posY = 0; y < j + SIZE; y += 2, posY++) {

                values[posX][posY] = (matrix[x][y] + matrix[x][y + 1] + matrix[x + 1][y] + matrix[x + 1][y + 1]) / 4;

            }
        }

        return values;
    }

    private void reverseSubsamplingCbCr() {
        for (int i = 0; i < nOfBlocks; i++) {
            blockCb[i].setValues(reverseSubsampling(blockCb[i].getValues()));
            blockCr[i].setValues(reverseSubsampling(blockCr[i].getValues()));
        }
    }

    private double[][] reverseSubsampling(double[][] matrix) {
        double[][] values = new double[SIZE][SIZE];

        for (int valuesX = 0, x = 0; valuesX < SIZE / 2; valuesX++, x += 2) {
            for (int valuesY = 0, y = 0; valuesY < SIZE / 2; valuesY++, y += 2) {

                values[x][y] = matrix[valuesX][valuesY];
                values[x][y + 1] = matrix[valuesX][valuesY];
                values[x + 1][y] = matrix[valuesX][valuesY];
                values[x + 1][y + 1] = matrix[valuesX][valuesY];
            }
        }

        return values;
    }

    private void applyForwardDCT() {

        for (int i = 0; i < nOfBlocks; i++) {
            blockY[i].setValues(applyDCT(blockY[i].getValues()));
            blockCb[i].setValues(applyDCT(blockCb[i].getValues()));
            blockCr[i].setValues(applyDCT(blockCr[i].getValues()));
        }
    }

    private void applyQuantizationForAllBlocks() {

        for (int i = 0; i < nOfBlocks; i++) {
            applyQuantization(blockY[i].getValues());
            applyQuantization(blockCb[i].getValues());
            applyQuantization(blockCr[i].getValues());
        }
    }

    private void substractFromAllBlocks() {
        for (int i = 0; i < nOfBlocks; i++) {
            substract(blockY[i].getValues());
            substract(blockCb[i].getValues());
            substract(blockCr[i].getValues());
        }
    }

    private void substract(double[][] values) {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                values[i][j] -= 128;
            }
        }
    }

    private double[][] applyDCT(double[][] values) {
        double[][] F = new double[SIZE][SIZE];

        for (int u = 0; u < SIZE; u++) {
            for (int v = 0; v < SIZE; v++) {
                double sum = 0.0;
                double cu, cv;

                if (u == 0) {
                    cu = 1 / Math.sqrt(2);
                } else {
                    cu = 1;
                }

                if (v == 0) {
                    cv = 1 / Math.sqrt(2);
                } else {
                    cv = 1;
                }

                for (int x = 0; x < SIZE; x++) {
                    for (int y = 0; y < SIZE; y++) {
                        sum += Math.cos(((2 * x + 1) * u * Math.PI) / 16) *
                                Math.cos(((2 * y + 1) * v * Math.PI) / 16) *
                                values[x][y];
                    }
                }

                sum = (sum * cu * cv) / 4.0;
                F[u][v] = sum;
            }
        }

        return F;
    }

    private void applyQuantization(double[][] values) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                values[i][j] = (int) (values[i][j] / Q[i][j]);
            }
        }
    }

    private void readQuantizationMatrix(int[][] Q) {
        try {
            File file = new File("C:\\Fac\\Sem5\\AVDP\\Lab01\\src\\quantizationMatrix.txt");
            Scanner scanner = new Scanner(file);

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    Q[i][j] = scanner.nextInt();
                }
            }

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

    }

    private void applyEntropyEncoding() {
        int size = 0;

        for (int i = 0; i < nOfBlocks; i++) {

            size = runLenghtEncoding(
                    outputArray, zigZagParse(blockY[i].getValues()), size
            );

            size = runLenghtEncoding(
                    outputArray, zigZagParse(blockCb[i].getValues()), size
            );

            size = runLenghtEncoding(
                    outputArray, zigZagParse(blockCr[i].getValues()), size
            );
        }
    }

    private double[] zigZagParse(double[][] values) {
        double[] result = new double[64];
        int pos = 0;
        int row = 0, col = 0;

        boolean flag = false;

        for (int len = 1; len <= SIZE; len++) {
            for (int i = 0; i < len; i++) {

                result[pos++] = values[row][col];

                if (i + 1 == len) {
                    break;
                }

                if (flag) {
                    row++;
                    col--;
                } else {
                    row--;
                    col++;
                }

            }

            if (len == SIZE) {
                break;
            }

            if (flag) {
                row++;
                flag = false;
            } else {
                col++;
                flag = true;
            }
        }

        if (row == 0) {
            if (col == SIZE - 1) {
                row++;
            } else {
                col++;
            }
            flag = true;
        } else {
            if (row == SIZE - 1) {
                col++;
            } else {
                row++;
            }
            flag = false;
        }

        for (int len, diag = SIZE - 1; diag > 0; diag--) {
            len = Math.min(diag, SIZE);

            for (int i = 0; i < len; i++) {

                result[pos++] = values[row][col];

                if (i + 1 == len) {
                    break;
                }

                if (flag) {
                    row++;
                    col--;
                } else {
                    col++;
                    row--;
                }
            }

            if (row == 0 || col == SIZE - 1) {
                if (col == SIZE - 1) {
                    row++;
                } else {
                    col++;
                }
                flag = true;
            } else if (col == 0 || row == SIZE - 1) {
                if (row == SIZE - 1) {
                    col++;
                } else {
                    row++;
                }
                flag = false;
            }
        }

        return result;
    }

    private int runLenghtEncoding(double[] outputArray, double[] amplitudes, int size) {
        double[] list = new double[64 * 3 - 1];
        int pos = 0;

        list[pos++] = sizeForAmplitude(amplitudes[0]);
        list[pos++] = amplitudes[0];

        int nrOfZeros = 0;

        for (int i = 1; i < 64; i++) {

            if (amplitudes[i] == 0) {
                nrOfZeros++;
                continue;
            }

            list[pos++] = nrOfZeros;
            list[pos++] = sizeForAmplitude(amplitudes[i]);
            list[pos++] = amplitudes[i];

            nrOfZeros = 0;
        }

        if (nrOfZeros != 0) {
            list[pos++] = 0;
            list[pos++] = 0;
        }

        System.arraycopy(list, 0, outputArray, size, pos);

        size += pos;

        return size;
    }

    private int sizeForAmplitude(double amplitude) {
        if (amplitude == -1 || amplitude == 1) {
            return 1;
        }

        if (amplitude == -3 || amplitude == -2 || amplitude == 2 || amplitude == 3) {
            return 2;
        }

        if ((amplitude >= -7 && amplitude <= -4) || (amplitude >= 4 && amplitude <= 7)) {
            return 3;
        }

        if ((amplitude >= -15 && amplitude <= -8) || (amplitude >= 8 && amplitude <= 15)) {
            return 4;
        }

        if ((amplitude >= -31 && amplitude <= -16) || (amplitude >= 16 && amplitude <= 31)) {
            return 5;
        }

        if ((amplitude >= -63 && amplitude <= -32) || (amplitude >= 32 && amplitude <= 63)) {
            return 6;
        }

        if ((amplitude >= -127 && amplitude <= -64) || (amplitude >= 64 && amplitude <= 127)) {
            return 7;
        }

        if ((amplitude >= -255 && amplitude <= -128) || (amplitude >= 128 && amplitude <= 255)) {
            return 8;
        }

        if ((amplitude >= -511 && amplitude <= -256) || (amplitude >= 256 && amplitude <= 511)) {
            return 9;
        }

        if ((amplitude >= -1023 && amplitude <= -512) || (amplitude >= 512 && amplitude <= 1023)) {
            return 10;
        }

        return 0;
    }

    private void addPadding() {
        if (image.getRows() % SIZE != 0) {
            int newSize = image.getRows();
            while (newSize % SIZE != 0) {
                newSize++;
            }
            addPaddingBottom(newSize);
        }

        if (image.getCols() % SIZE != 0) {
            int newSize = image.getCols();
            while (newSize % SIZE != 0) {
                newSize++;
            }
            addPaddingRight(newSize);
        }
    }

    private void addPaddingRight(int size) {
        for (int i = image.getCols(); i < size; i++) {
            for (int j = 0; j < image.getRows(); j++) {

                image.getY()[j][i] = image.getY()[j][image.getCols() - 1];
                image.getCb()[j][i] = image.getCb()[j][image.getCols() - 1];
                image.getCr()[j][i] = image.getCr()[j][image.getCols() - 1];
            }
        }

        image.setCols(size);
    }

    private void addPaddingBottom(int size) {
        for (int i = image.getRows(); i < size; i++) {
            for (int j = 0; j < image.getCols(); j++) {

                image.getY()[i][j] = image.getY()[image.getRows() - 1][j];
                image.getCb()[i][j] = image.getCb()[image.getRows() - 1][j];
                image.getCr()[i][j] = image.getCr()[image.getRows() - 1][j];
            }
        }

        image.setRows(size);
    }

    public Block[] getBlockY() {
        return blockY;
    }

    public Block[] getBlockCb() {
        return blockCb;
    }

    public Block[] getBlockCr() {
        return blockCr;
    }

    public int getnOfBlocks() {
        return nOfBlocks;
    }

    public int getRows() {
        return image.getRows();
    }

    public int getCols() {
        return image.getCols();
    }

    public int getMaxValue() {
        return image.getMaxValue();
    }

    public int[][] getQ() {
        return Q;
    }

    public double[] getOutputArray() {
        return outputArray;
    }

}
