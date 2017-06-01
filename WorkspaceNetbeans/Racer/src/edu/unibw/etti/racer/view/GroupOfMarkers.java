package edu.unibw.etti.racer.view;

import edu.unibw.etti.racer.controller.Configuration;
import edu.unibw.etti.racer.model.Figure;
import edu.unibw.etti.racer.model.Score;
import edu.unibw.etti.racer.view.ImageViewCache;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class GroupOfMarkers extends AnchorPane implements Observer {

    private static final Image MARKER = ImageViewCache.makeTransparent(new Image(Configuration.MARKER_FILE));
    
    private Figure figure = null;
    private final Score game;
    
    public GroupOfMarkers(Score game) {
        setCache(true);
        setPrefSize(Configuration.STREET_LENGTH, Configuration.STREET_WIDTH);
        double offsetX = 0.0;
        while (offsetX < Configuration.STREET_LENGTH + Configuration.NEXT_MARKER_FACTOR * MARKER.getWidth()) {
            for (int y = 0; y <= Configuration.NUMBER_OF_LANES; y++) {
                double offsetY = y  * (Configuration.LANE_WIDTH + Configuration.MARKER_WIDTH); 
                Node n = new ImageView(MARKER);
                n.setTranslateX(offsetX);
                n.setTranslateY(offsetY);
                getChildren().add(n);
            }
            offsetX += Configuration.NEXT_MARKER_FACTOR * MARKER.getWidth();
        }
        this.game = game;
    }
    
    public void bindToFigure(Figure figure) {
        if (this.figure != null)
            this.figure.deleteObserver(this);
        this.figure = figure;
        
        if (figure != null)
            figure.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> setTranslateX((getTranslateX() - (1 + game.getLevel()) / 2.0) % (Configuration.NEXT_MARKER_FACTOR * MARKER.getWidth())));
    }

}
