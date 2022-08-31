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