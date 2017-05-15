package edu.unibw.etti.graph.view;

import edu.unibw.etti.graph.control.FileControl;
import edu.unibw.etti.graph.control.TokenControl;
import edu.unibw.etti.graph.model.Graph;
import edu.unibw.etti.graph.model.VertexSet;
import edu.unibw.etti.graph.model.WebColor;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;

/**
 * Der Haupt-Pane der Anwendung. Die gesamte Oberflaeche der Anwendung wird
 * initialisiert und zusammengebaut.
 *
 * @author Andrea Baumann
 */
public class RootPane extends BorderPane {

    private static final String CLEAR_PANEL = "_Clear Panel";

    private static final String ZOOM_IN = "Zoom _In";
    private static final String ZOOM_OUT = "Zoom _Out";
    private static final double ZOOM_OUT_AMOUNT = 1.1;
    private static final double ZOOM_IN_AMOUNT = 0.9;

    private static final String ADD_VERTEX = "Add _Vertex";
    private static final String ADD_TOKEN = "Add _Token";

    public RootPane() {
        // Graph and Token Models
        Graph graph = new Graph();
        VertexSet tokens = new VertexSet();

        // Graph and Token Scene
        GraphScene graphScene = new GraphScene(graph, tokens);
        graphScene.buildGraphAndTokens();
        graph.addObserver(graphScene);
        tokens.addObserver(graphScene);

        // File Buttons 
        FileControl fileControl = new FileControl(graph);
        Button openFileButton = new Button(OpenFileHandler.OPEN_FILE);
        openFileButton.addEventHandler(ActionEvent.ACTION, new OpenFileHandler(fileControl));
        Button saveFileButton = new Button(SaveFileHandler.SAVE_FILE);
        saveFileButton.addEventHandler(ActionEvent.ACTION, new SaveFileHandler(fileControl));

        // Vertex Button
        Button addVertexButton = new Button(ADD_VERTEX);
        addVertexButton.setMnemonicParsing(true);
        addVertexButton.addEventHandler(ActionEvent.ACTION, e -> {
            graph.addVertex(0, 0, 0, WebColor.getRandomCyan());
            graph.update();
        });

        // Token Button
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "The network must have at least one button!");
        alert.initStyle(StageStyle.UNDECORATED);
        TokenControl tokenControl = new TokenControl(graph, tokens);
        Button addTokenButton = new Button(ADD_TOKEN);
        addTokenButton.addEventHandler(ActionEvent.ACTION, e -> {
            if (graph.getVertices().isEmpty()) {
                alert.showAndWait();
            } else {
                tokenControl.startTokenThread();
            }
        });

        // Clear Button 
        Button clearPanelButton = new Button(CLEAR_PANEL);
        clearPanelButton.addEventHandler(ActionEvent.ACTION, e -> {
            graph.clear();
            tokenControl.stopAllTokenThreads();
        });

        // The Zoom Button Panel
        // Zoom Buttons
        Button zoomInButton = new Button(ZOOM_IN);
        zoomInButton.addEventHandler(ActionEvent.ACTION, e -> graphScene.zoom(ZOOM_IN_AMOUNT));
        Button zoomOutButton = new Button(ZOOM_OUT);
        zoomOutButton.addEventHandler(ActionEvent.ACTION, e -> graphScene.zoom(ZOOM_OUT_AMOUNT));

        // Put all together
        BorderPane buttonPane = new BorderPane();
        buttonPane.setLeft(new HBox(openFileButton, saveFileButton,
                addVertexButton, addTokenButton, clearPanelButton));
        buttonPane.setCenter(new Group());
        buttonPane.setRight(new HBox(zoomInButton, zoomOutButton));

        AnchorPane anchorGraphScene = new AnchorPane(graphScene);
        anchorGraphScene.widthProperty().addListener((a, b, c)
                -> graphScene.setWidth((Double) c));
        anchorGraphScene.heightProperty().addListener((a, b, c)
                -> graphScene.setHeight((Double) c));
        anchorGraphScene.addEventHandler(MouseEvent.ANY, graphScene);

        setTop(buttonPane);
        setCenter(anchorGraphScene);

        addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() != null) {
                switch (e.getCode()) {
                    case V:
                        graph.addVertex(0, 0, 0, WebColor.getRandomCyan());
                        graph.update();
                        break;
                    case T:
                        if (graph.getVertices().isEmpty()) {
                            alert.showAndWait();
                        } else {
                            tokenControl.startTokenThread();
                        }
                        break;
                    case C:
                        graph.clear();
                        tokenControl.stopAllTokenThreads();
                        break;
                    case I:
                        graphScene.zoom(ZOOM_IN_AMOUNT);
                        break;
                    case O:
                        graphScene.zoom(ZOOM_OUT_AMOUNT);
                        break;
                    default:
                        break;
                }
            }
        });

    }
}
