public class Decoder {

    private static final int SIZE = 8;

    private final PPMImage image;
    private final Block[] blockY;
    private final Block[] blockCb;
    private final Block[] blockCr;
    private final int nOfBlocks;
    private final int[][] Q;
    private final double[] array;

    public Decoder(int rows, int cols, int maxValue, Block[] blockY, Block[] blockCb, Block[] blockCr, int nOfBlocks, int[][] Q, double[] array) {
        this.image = new PPMImage(rows, cols, maxValue);
        this.nOfBlocks = nOfBlocks;
        this.blockY = blockY;
        this.blockCb = blockCb;
        this.blockCr = blockCr;
        this.Q = Q;
        this.array = array;
    }

    public void decode() {
        applyEntropyDecoding();

        applyDeQuantizationForAllBlocks();

        applyInverseDCTForAllBlocks();

        addToAllBlocks();

        for (int i = 0; i < nOfBlocks; i++) {

            writeBlockInImage(blockY[i].getI(), blockY[i].getJ(), blockY[i].getValues(), blockY[i].getType());
            writeBlockInImage(blockCb[i].getI(), blockCb[i].getJ(), blockCb[i].getValues(), blockCb[i].getType());
            writeBlockInImage(blockCr[i].getI(), blockCr[i].getJ(), blockCr[i].getValues(), blockCr[i].getType());
        }

    }

    private void writeBlockInImage(int i, int j, double[][] values, String type) {
        double[][] colorMatrix;

        switch (type) {
            case "Y" -> colorMatrix = image.getY();
            case "Cb" -> colorMatrix = image.getCb();
            case "Cr" -> colorMatrix = image.getCr();
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }

//        if (type.equals("Y")) {

        for (int x = i, valuesX = 0; x < SIZE + i; x++, valuesX++) {
            for (int y = j, valuesY = 0; y < SIZE + j; y++, valuesY++) {
                colorMatrix[x][y] = values[valuesX][valuesY];
            }
        }
//        } else {
//
//            for (int valuesX = 0, x = i; valuesX < SIZE / 2; valuesX++, x += 2) {
//                for (int valuesY = 0, y = j; valuesY < SIZE / 2; valuesY++, y += 2) {
//
//                    colorMatrix[x][y] = values[valuesX][valuesY];
//                    colorMatrix[x][y + 1] = values[valuesX][valuesY];
//                    colorMatrix[x + 1][y] = values[valuesX][valuesY];
//                    colorMatrix[x + 1][y + 1] = values[valuesX][valuesY];
//                }
//            }
//        }
    }

    private void applyDeQuantizationForAllBlocks() {

        for (int i = 0; i < nOfBlocks; i++) {
            applyDeQuantization(blockY[i].getValues());
            applyDeQuantization(blockCb[i].getValues());
            applyDeQuantization(blockCr[i].getValues());
        }
    }

    private void applyDeQuantization(double[][] values) {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                values[i][j] *= Q[i][j];
            }
        }
    }

    private void applyInverseDCTForAllBlocks() {

        for (int i = 0; i < nOfBlocks; i++) {
            blockY[i].setValues(applyInverseDCT(blockY[i].getValues()));
            blockCb[i].setValues(applyInverseDCT(blockCb[i].getValues()));
            blockCr[i].setValues(applyInverseDCT(blockCr[i].getValues()));
        }
    }

    private double[][] applyInverseDCT(double[][] values) {
        double[][] f = new double[SIZE][SIZE];

        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                double sum = 0.0;

                for (int u = 0; u < SIZE; u++) {
                    for (int v = 0; v < SIZE; v++) {

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

                        sum += Math.cos(((2 * x + 1) * u * Math.PI) / 16) *
                                Math.cos(((2 * y + 1) * v * Math.PI) / 16) *
                                values[u][v] * cu * cv;
                    }
                }

                sum /= 4.0;
                f[x][y] = sum;
            }
        }

        return f;
    }

    private void addToAllBlocks() {
        for (int i = 0; i < nOfBlocks; i++) {
            add(blockY[i].getValues());
            add(blockCb[i].getValues());
            add(blockCr[i].getValues());
        }
    }

    private void add(double[][] values) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                values[i][j] += 128;
            }
        }
    }

    private void applyEntropyDecoding() {
        int[] start = new int[1];

        for (int i = 0; i < nOfBlocks; i++) {
            blockY[i].setValues(
                    zigZagParse(
                            runLengthDecoding(start)
                    )
            );

            blockCb[i].setValues(
                    zigZagParse(
                            runLengthDecoding(start)
                    )
            );

            blockCr[i].setValues(
                    zigZagParse(
                            runLengthDecoding(start)
                    )
            );
        }
    }

    private double[] runLengthDecoding(int[] start) {
        double[] result = new double[64];
        int pos = 0;

        result[pos++] = array[start[0] + 1];
        start[0] += 2;

        boolean zeroes = false;
        int endPosition = 0;

        for (int i = start[0], x = 0; x < 63; i += 3, x++) {

            if (array[i] == 0 && array[i + 1] == 0) {
                zeroes = true;
                endPosition = i + 2;
                break;
            }

            if (array[i] != 0) {
                for (int j = 0; j < array[i]; j++) {

                    result[pos++] = 0;
                }
            }

            result[pos++] = array[i + 2];
        }

        if (zeroes) {
            for (int i = pos; i < 64; i++) {
                result[pos] = 0;
            }
        }

        if (endPosition != 0) {
            start[0] = endPosition;
        } else {
            start[0] = start[0] + 63 * 3 + 1;
        }

        return result;
    }

    private double[][] zigZagParse(double[] values) {
        double[][] result = new double[8][8];
        int pos = 0;
        int row = 0, col = 0;

        boolean flag = false;

        for (int len = 1; len <= SIZE; len++) {
            for (int i = 0; i < len; i++) {

                result[row][col] = values[pos++];

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

                result[row][col] = values[pos++];

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

    public PPMImage getImage() {
        return image;
    }
}
