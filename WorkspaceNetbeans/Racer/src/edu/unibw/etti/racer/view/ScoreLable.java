/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unibw.etti.racer.view;

import edu.unibw.etti.racer.controller.Configuration;
import edu.unibw.etti.racer.model.Score;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author rgn
 */
public class ScoreLable extends Label implements Observer {
    private Score score = null;
    public ScoreLable(Score score) {
        this.score = score;
        this.score.addObserver(this);
        update(score, null);
    }
    @Override
    public void update(Observable o, Object arg) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               String output = "Levels: " + score.getLevel()
                             + " Points: " + score.getPoints() 
                             + " Lives: " + score.getLives();
                setText(output);
                
                if(score.getLives() == 0) {
                    Alert alter = new Alert(Alert.AlertType.INFORMATION,
                            "Glückwunsch!\n Sie haben Level: " 
                                    + score.getLevel()
                                    + " mit " 
                                    + score.getPoints() 
                                    + " Punkten erreicht" );
                    alter.setHeaderText("Gameover");
                    alter.setGraphic(new ImageView(new Image(Configuration.POINT_FILE)));
                    alter.showAndWait();
                    output = "";
                }
                
            }
        });
        
    }

   
}
