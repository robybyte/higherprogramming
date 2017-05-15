package edu.unibw.etti.graph.view;

import java.io.File;

import edu.unibw.etti.graph.control.FileControl;
import edu.unibw.etti.graph.control.FileSaveException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

class SaveFileHandler implements EventHandler<ActionEvent> {

    final static String SAVE_FILE = "Save File";
    private final FileControl control;

    private final Stage fileStage = new Stage();
    private final FileChooser fileChooser = new FileChooser();
    private File initialDirectory = null;

    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);

    SaveFileHandler(FileControl control) {
        this.control = control;
        fileChooser.setTitle(SAVE_FILE);
        alert.initStyle(StageStyle.UNDECORATED);
    }

    @Override
    public void handle(ActionEvent event) {
        if (initialDirectory != null) {
            fileChooser.setInitialDirectory(null);
        }

        File file = fileChooser.showSaveDialog(fileStage);

        if (file != null) {
            initialDirectory = file.getParentFile();
            try {
                control.saveFile(file);
            } catch (FileSaveException e) {
                e.printStackTrace(System.err);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }
}
