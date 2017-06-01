package edu.unibw.etti.racer.view;

import edu.unibw.etti.racer.model.Figure;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImageViewCache {

    private static final HashMap<String, ArrayList<ImageViewOfFigure>> CACHE_IMAGE_VIEWS_BY_NAME = new HashMap<>();
    private static final HashMap<String, Image> CAHCE_IMAGES_BY_NAME = new HashMap<>();

    public synchronized static ImageViewOfFigure getImageView(Figure figure) {
        if (!CAHCE_IMAGES_BY_NAME.containsKey(figure.file)) {
            Image image = makeTransparent(new Image(figure.file));
            CAHCE_IMAGES_BY_NAME.put(figure.file, image);
            CACHE_IMAGE_VIEWS_BY_NAME.put(figure.file, new ArrayList<>());
        }

        if (CACHE_IMAGE_VIEWS_BY_NAME.get(figure.file).isEmpty()) {
            ImageViewOfFigure imageViewFigure = new ImageViewOfFigure(CAHCE_IMAGES_BY_NAME.get(figure.file));
            imageViewFigure.bindToFigure(figure);
            imageViewFigure.setCache(true);
            return imageViewFigure;
        } else {
            return CACHE_IMAGE_VIEWS_BY_NAME.get(figure.file).remove(0);
        }
    }

    public synchronized static void releaseImageView(Figure figure, ImageViewOfFigure imageView) {
        CACHE_IMAGE_VIEWS_BY_NAME.get(figure.file).add(imageView);
    }

    public static Image makeTransparent(Image image) {
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        PixelReader pixelReader = image.getPixelReader();

        Color trans = pixelReader.getColor(0, 0);
        double transRed = trans.getRed();
        double transGreen = trans.getGreen();
        double transBlue = trans.getBlue();
        double delta = 0.05;

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color pixel = pixelReader.getColor(x, y);
                double pixelRed = pixel.getRed();
                double pixelGreen = pixel.getGreen();
                double pixelBlue = pixel.getBlue();
                if (Math.abs(transRed - pixelRed) > delta
                        || Math.abs(transGreen - pixelGreen) > delta
                        || Math.abs(transBlue - pixelBlue) > delta) {
                    pixelWriter.setColor(x, y, pixelReader.getColor(x, y));
                } else {
                    pixelWriter.setColor(x, y, Color.TRANSPARENT);
                }
            }
        }
        return writableImage;
    }

}
