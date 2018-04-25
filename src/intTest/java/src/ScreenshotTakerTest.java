package src;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenshotTakerTest {
    private static final double defaultMaxError = 0.03;

    public static void main(String[] args) throws IOException, AssertionError, IllegalArgumentException {
        if (args.length < 2 || args.length > 3) {
            throw new IllegalArgumentException("Usage: ScreenshotTakerTest.main(String pathToImgA, String pathToImgB, optional double maxError)");
        }

        double maxError = args.length > 2 ? Double.parseDouble(args[2]) : defaultMaxError;

        if (!compareImages(args[0], args[1], maxError)) {
            throw new AssertionError("Screenshot test failed.");
        }

        System.out.println("Test completed successfully");
    }

    private static boolean compareImages(String pathToImgA, String pathToImgB, double maxError) throws IOException {
        File imgFileA = new File(pathToImgA);
        File imgFileB = new File(pathToImgB);

        BufferedImage imgA = ImageIO.read(imgFileA);
        BufferedImage imgB = ImageIO.read(imgFileB);

        return compareImages(imgA, imgB, maxError);
    }

    private static boolean compareImages(BufferedImage imgA, BufferedImage imgB, double maxError) {
        int width = imgA.getWidth();
        int height = imgA.getHeight();

        if (width != imgB.getWidth() || height != imgB.getHeight()) {
            return false;
        }

        int errorPixelsCount = 0;
        int maxErrorPixelsCount = (int) (maxError * width * height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int colorA = imgA.getRGB(x, y);
                int colorB = imgB.getRGB(x, y);

                if (colorA != colorB) {
                    errorPixelsCount++;

                    if (errorPixelsCount > maxErrorPixelsCount) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
