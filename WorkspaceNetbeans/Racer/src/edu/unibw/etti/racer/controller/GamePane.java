package edu.unibw.etti.racer.controller;

import edu.unibw.etti.racer.controller.GameControl;
import edu.unibw.etti.racer.controller.StartPauseButton;
import edu.unibw.etti.racer.view.StreetPane;
import edu.unibw.etti.racer.handler.MoveCarHandler;
import edu.unibw.etti.racer.view.ScoreLable;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class GamePane extends BorderPane {

    public GamePane(GameControl gameControl) {
        // TODO: Klasse MoveCarHandler schreiben!
        // TODO: Einbinden des MoveCarHandler!
        MoveCarHandler mc = new MoveCarHandler(gameControl);
        StreetPane streetPanel = new StreetPane(gameControl.score, gameControl.obstacles, gameControl.car);
        ScoreLable scoreLable = new ScoreLable(gameControl.score);
//this.addEventHandler(KeyEvent.ANY, mc);
        super.setEventHandler(KeyEvent.ANY, mc);
        
        HBox bottom = new HBox(5);
        bottom.setAlignment(Pos.CENTER_LEFT);
        bottom.setPadding(new Insets(5));
        StartPauseButton startPauseBottom = new StartPauseButton();
        startPauseBottom.setPrefWidth(100);
        startPauseBottom.bindToGameControl(gameControl);
        startPauseBottom.addEventHandler(ActionEvent.ACTION, startPauseBottom);
        bottom.getChildren().add(startPauseBottom);
        bottom.getChildren().add(scoreLable);
        // TODO: Klasse ScoreLabel schreiben!
        // TODO: Einbinden des ScoreLabel!

        setCenter(streetPanel);
        setBottom(bottom);
    }
}
