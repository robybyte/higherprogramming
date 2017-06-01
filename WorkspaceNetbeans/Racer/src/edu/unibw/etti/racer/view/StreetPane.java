package edu.unibw.etti.racer.view;

import edu.unibw.etti.racer.controller.ObstacleArrayList;
import edu.unibw.etti.racer.model.Score;
import edu.unibw.etti.racer.model.Car;
import edu.unibw.etti.racer.view.ImageViewCache;
import edu.unibw.etti.racer.view.ImageViewOfFigure;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class StreetPane extends AnchorPane {

    private static final Color STREET_COLOR = Color.color(0.28, 0.28, 0.28);

    public StreetPane(Score game, ObstacleArrayList obstacleList, Car car) {
        setBackground(new Background(new BackgroundFill(STREET_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        GroupOfMarkers marker = new GroupOfMarkers(game);
        marker.bindToFigure(car);
        getChildren().add(marker);

        ImageViewOfFigure imageViewCar = ImageViewCache.getImageView(car);
        imageViewCar.bindToFigure(car);
        getChildren().add(imageViewCar);

        GroupOfObstacles og = new GroupOfObstacles(obstacleList);
        obstacleList.addObserver(og);
        getChildren().add(og);
    }
}
