package edu.unibw.etti.graph.view;

import edu.unibw.etti.graph.model.Vertex;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;

class VertexExtended extends Sphere implements Observer {

    final Vertex vertex;
    
    private final PhongMaterial material = new PhongMaterial();
    static final double RADIUS = 25.0;


    VertexExtended(Vertex vertex) {
        super(RADIUS);
        
        this.vertex = vertex;
        setMaterial(material);

        syncData();
    }

    final synchronized void syncData() {
        setTranslateX(vertex.getX());
        setTranslateY(vertex.getY());
        setTranslateZ(vertex.getZ());
        
        material.setDiffuseColor(Color.web(vertex.getColor(), 0.7));
        material.setSpecularColor(Color.WHITE.interpolate(Color.web(vertex.getColor()), 0.5));
        material.setSpecularPower(25);
        
        setDrawMode(DrawMode.LINE);
    }
    
    void addToObserver() {
        vertex.addObserver(this);
    }

    void deleteFromObserver() {
        vertex.deleteObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> syncData());
    }
}
