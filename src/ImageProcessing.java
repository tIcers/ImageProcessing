import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;

public class ImageProcessing {
    public static void main(String[] args) {
        int[][] imageData = imgToTwoD("./apple.jpg");

        viewImageData(imageData);

        int[][] trimmed = trimBorders(imageData, 60);

        twoDToImage(trimmed, "./trimmed_apple.jpg");


        int[][] negative = negativeColor(imageData);

        twoDToImage(negative, "./negative_apple.jpg");


        int[][] stretchedHImg = stretchHorizontally(imageData);

        twoDToImage(stretchedHImg, "./stretched_apple.jpg");


        int[][] shrankVImg = shrinkVertically(imageData);

        twoDToImage(shrankVImg, "./shrank_apple.jpg");


        int[][] invertedImg = invertImage(imageData);

        twoDToImage(invertedImg, "./inverted_apple.jpg");


        int[][] coloredImg = colorFilter(imageData, -75, 30, -30);

        twoDToImage(coloredImg, "./colored_apple.jpg");

        int[][] allFilters = stretchHorizontally(shrinkVertically(colorFilter(negativeColor(trimBorders(invertImage(imageData), 50)), 200, 20, 40)));


        // Painting with pixels

        int[][] blankImg = new int[500][500];

        int[][] randomImg = paintRandomImage(blankImg);

        twoDToImage(randomImg, "./random_img.jpg");

        int[] rgba = {255, 255, 0, 255};

        int[][] rectangleImg = paintRectangle(randomImg, 200, 200, 100, 100, getColorIntValFromRGBA(rgba));

        twoDToImage(rectangleImg, "./rectangle.jpg");

        int[][] generatedRectangles = generateRectangles(randomImg, 1000);

        twoDToImage(generatedRectangles, "./generated_rect.jpg");
    }
    // Image Processing Methods

    public static int[][] trimBorders(int[][] imageTwoD, int pixelCount) {
        if (imageTwoD.length > pixelCount * 2 && imageTwoD[0].length > pixelCount * 2) {
            int[][] trimmedImg = new int[imageTwoD.length - pixelCount * 2][imageTwoD[0].length - pixelCount * 2];
            for (int i = 0; i < trimmedImg.length; i++) {
                for (int j = 0; j < trimmedImg[i].length; j++) {
                    trimmedImg[i][j] = imageTwoD[i + pixelCount][j + pixelCount];
                }
            }
            return trimmedImg;
        } else {
            System.out.println("Cannot trim that many pixels from the given image.");
            return imageTwoD;
        }
    }

    public static int[][] negativeColor(int[][] imageTwoD) {
        int[][] manipulatedImg = new int[imageTwoD.length][imageTwoD[0].length];
        for (int i = 0; i < imageTwoD.length; i++) {
            for (int j = 0; j < imageTwoD[i].length; j++) {
                int[] rgba = getRGBAFromPixel(imageTwoD[i][j]);
                rgba[0] = 255 - rgba[0];
                rgba[1] = 255 - rgba[1];
                rgba[2] = 255 - rgba[2];
                manipulatedImg[i][j] = getColorIntValFromRGBA(rgba);
            }
        }
        return manipulatedImg;
        }

    public static int[][] stretchHorizontally(int[][] imageTwoD) {
        int[][] manipulatedImg = new int[imageTwoD.length][imageTwoD[0].length * 2];
        int it = 0;
        for (int i = 0; i < imageTwoD.length; i++) {
            for (int j = 0; j < imageTwoD[i].length; j++) {
                it = j * 2;
                manipulatedImg[i][it] = imageTwoD[i][j];
                manipulatedImg[i][it + 1] = imageTwoD[i][j];
            }
        }
        return manipulatedImg;
    }

