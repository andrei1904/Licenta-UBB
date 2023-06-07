public class Main {

    public static void main(String[] args) {

        PPMImage image = new PPMImage();
        image.readImage("C:\\Fac\\Sem5\\AVDP\\Lab01\\nt-P3.ppm");

        Encoder encoder = new Encoder(image);
        encoder.encode();

        Decoder decoder = new Decoder(encoder.getRows(), encoder.getCols(),
                encoder.getMaxValue(), encoder.getBlockY(), encoder.getBlockCb(),
                encoder.getBlockCr(), encoder.getnOfBlocks(), encoder.getQ(),
                encoder.getOutputArray());
        decoder.decode();

        PPMImage newImage = decoder.getImage();
        newImage.writeImage("C:\\Fac\\Sem5\\AVDP\\Lab01\\imageResult.ppm");
    }
}
