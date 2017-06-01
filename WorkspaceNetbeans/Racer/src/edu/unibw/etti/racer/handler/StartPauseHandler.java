package edu.unibw.etti.racer.handler;

import edu.unibw.etti.racer.controller.GameControl;
import edu.unibw.etti.racer.controller.StartPauseButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class StartPauseHandler implements EventHandler<ActionEvent> {

    private final GameControl control;

    public StartPauseHandler(GameControl gameControl) {
        this.control = gameControl;
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();

            if (button.getText().equals(StartPauseButton.BUTTON_PAUSE)) {
                control.setPaused(true);
            } else {
                control.setPaused(false);
            }
        }
    }
}
