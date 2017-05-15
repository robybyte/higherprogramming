package edu.unibw.etti.graph.view;

import java.io.File;

import edu.unibw.etti.graph.control.FileControl;
import edu.unibw.etti.graph.control.FileOpenException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

class OpenFileHandler implements EventHandler<ActionEvent> {

    final static String OPEN_FILE = "Open File";
    private final FileControl control;

    private final Stage fileStage = new Stage();
    private final FileChooser fileChooser = new FileChooser();
    private File initialDirectory = null;

    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);

    OpenFileHandler(FileControl control) {
        this.control = control;
        fileChooser.setTitle(OPEN_FILE);
        alert.initStyle(StageStyle.UNDECORATED);
    }

    @Override
    public void handle(ActionEvent event) {
        if (initialDirectory != null) {
            fileChooser.setInitialDirectory(null);
        }

        File file = fileChooser.showOpenDialog(fileStage);

        if (file != null) {
            initialDirectory = file.getParentFile();
            try {
                control.openFile(file);
            } catch (FileOpenException e) {
                e.printStackTrace(System.err);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }
}
