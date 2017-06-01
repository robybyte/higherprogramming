package edu.unibw.etti.racer.view;

import edu.unibw.etti.racer.model.Figure;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViewOfFigure extends ImageView implements Observer {

    private Figure figure;

    public ImageViewOfFigure(Image image) {
        super(image);
    }

    public void bindToFigure(Figure figure) {
        if (this.figure != null) {
            this.figure.deleteObserver(this);
        }
        if (figure != null) {
            this.figure = figure;
            setTranslateX(figure.getX());
            setTranslateY(figure.getY() - figure.height_half);
            this.figure.addObserver(this);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> {
            setTranslateX(figure.getX());
            setTranslateY(figure.getY() - figure.height_half);
        });
    }
}
