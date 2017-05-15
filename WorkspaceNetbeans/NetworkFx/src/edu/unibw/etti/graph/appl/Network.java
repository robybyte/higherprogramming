package edu.unibw.etti.graph.appl;

import edu.unibw.etti.graph.view.RootPane;
import java.util.Locale;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Klasse enthaehlt main-Methode und startet die Anwendung.
 *
 * @author Andrea Baumann
 * @see edu.unibw.etti.graph.view.RootPane
 */
public class Network extends Application {

    @Override
    public void start(Stage stage) {
        Locale.setDefault(Locale.ENGLISH);

        RootPane root = new RootPane();
        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("Network Panel");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
