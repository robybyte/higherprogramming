package edu.unibw.etti.racer.view;

import edu.unibw.etti.racer.controller.GameControl;
import edu.unibw.etti.racer.controller.GameControl;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class StartPauseButton extends Button implements Observer, EventHandler<ActionEvent> {

    private final StringProperty textOnButton;
    private GameControl gameControl = null;
    public final static String BUTTON_PAUSE = "Pause";
    public final static String BUTTON_START = "Start";

    public StartPauseButton() {
        textOnButton = new SimpleStringProperty();
        textProperty().bind(textOnButton);
    }

    void bindToGameControl(GameControl gameControl) {
        this.gameControl = gameControl;
        this.gameControl.addObserver(this);
        update(null, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> {
            if (gameControl.isPaused()) {
                textOnButton.set(BUTTON_START);
            } else {
                textOnButton.set(BUTTON_PAUSE);
            }
        });
    }

    @Override
    public void handle(ActionEvent event) {
        if (gameControl.isPaused()) {
            gameControl.setPaused(false);
        } else {
            gameControl.setPaused(true);
        }
    }

}
