package edu.unibw.etti.graph.view;

import edu.unibw.etti.graph.model.Edge;
import edu.unibw.etti.graph.model.Vertex;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Platform;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

class EdgeExtended extends Cylinder implements Observer {

    final Vertex fromVertex;
    final Vertex toVeretx;
    final Edge edge;

    private final PhongMaterial material = new PhongMaterial();
    static final double RADIUS = 3.0;
    public static final int DIVISIONS = 6;

    private static final Point3D Y = new Point3D(0, 1, 0);

    EdgeExtended(Edge edge) {
        super(RADIUS, 1.0);

        this.fromVertex = edge.fromVertex;
        this.toVeretx = edge.toVertex;
        this.edge = edge;
        setMaterial(material);

        syncData();
    }

    final synchronized void syncData() {
        setHeight(edge.getLength() - VertexExtended.RADIUS * 2);

        Point3D b = new Point3D(edge.toVertex.getX() - edge.fromVertex.getX(),
                edge.toVertex.getY() - edge.fromVertex.getY(),
                edge.toVertex.getZ() - edge.fromVertex.getZ());
        Point3D n = Y.crossProduct(b);
        double alpha = Y.angle(b);

        Affine t = new Affine();
        t.append(new Rotate(alpha, n));
        t.prepend(new Translate(edge.fromVertex.getX() + (edge.toVertex.getX() - edge.fromVertex.getX()) / 2.0,
                edge.fromVertex.getY() + (edge.toVertex.getY() - edge.fromVertex.getY()) / 2.0,
                edge.fromVertex.getZ() + (edge.toVertex.getZ() - edge.fromVertex.getZ()) / 2.0));
        getTransforms().clear();
        getTransforms().add(t);

        material.setDiffuseColor(Color.web(edge.getColor()));
        material.setSpecularColor(Color.WHITE.interpolate(Color.web(edge.getColor()), 0.5));
        material.setSpecularPower(50);
    }

    void addToObservers() {
        fromVertex.addObserver(this);
        toVeretx.addObserver(this);
        edge.addObserver(this);
    }

    void deleteFromObservers() {
        fromVertex.deleteObserver(this);
        toVeretx.deleteObserver(this);
        edge.deleteObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> syncData());
    }
}
