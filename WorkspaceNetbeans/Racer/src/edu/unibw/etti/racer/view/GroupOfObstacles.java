package edu.unibw.etti.racer.view;

import edu.unibw.etti.racer.model.Obstacle;
import edu.unibw.etti.racer.controller.ObstacleArrayList;
import edu.unibw.etti.racer.view.ImageViewCache;
import edu.unibw.etti.racer.view.ImageViewOfFigure;
import java.util.Observable;
import java.util.Observer;

import java.util.concurrent.ConcurrentHashMap;
import javafx.application.Platform;
import javafx.scene.Group;

public class GroupOfObstacles extends Group implements Observer {

    private final ConcurrentHashMap<Obstacle, ImageViewOfFigure> mapImageViewToName = new ConcurrentHashMap<>();

    private final ObstacleArrayList obstacles;

    public GroupOfObstacles(ObstacleArrayList obstacles) {
        this.obstacles = obstacles;

        validate();
    }

    public final synchronized void validate() {
        mapImageViewToName
                .keySet()
                .stream()
                .filter(k -> !obstacles.contains(k))
                .forEach(k -> {
                    ImageViewOfFigure v = mapImageViewToName.get(k);
                    getChildren().remove(v);
                    v.bindToFigure(null);
                    mapImageViewToName.remove(k);
                    ImageViewCache.releaseImageView(k, v);
                });
        int i = 0;
        Obstacle obstacle = obstacles.get(i++);
        while (obstacle != null) {

            if (!mapImageViewToName.containsKey(obstacle)) {
                ImageViewOfFigure imageViewFigure = ImageViewCache.getImageView(obstacle);
                imageViewFigure.bindToFigure(obstacle);
                mapImageViewToName.put(obstacle, imageViewFigure);
                getChildren().add(imageViewFigure);
            }

            obstacle = obstacles.get(i++);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> validate());
    }

}
