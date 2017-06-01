package edu.unibw.etti.racer;

import edu.unibw.etti.racer.view.GamePane;
import edu.unibw.etti.racer.controller.GameControl;
import edu.unibw.etti.racer.controller.ObstacleArrayList;
import edu.unibw.etti.racer.model.Score;
import edu.unibw.etti.racer.model.Car;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RacerFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        Score score = new Score();
        Car car = new Car();
        ObstacleArrayList obstacles = new ObstacleArrayList();

        GameControl gameControl = new GameControl(score, car, obstacles);
        gameControl.startThreads();
        
        GamePane gamePane = new GamePane(gameControl);

        Scene scene = new Scene(gamePane);
      
        primaryStage.setResizable(false);
        primaryStage.setTitle("Racer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
