package edu.unibw.etti.graph.view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import edu.unibw.etti.graph.model.Edge;
import edu.unibw.etti.graph.model.Graph;
import edu.unibw.etti.graph.model.VertexSet;
import edu.unibw.etti.graph.model.WebColor;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;

import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

class GraphScene extends SubScene implements Observer, EventHandler<MouseEvent> {

    private final Graph graph;
    private final VertexSet tokens;

    private final ArrayList<VertexExtended> extendedVertices = new ArrayList<>();
    private final ArrayList<EdgeExtended> extendedEdges = new ArrayList<>();
    private final ArrayList<TokenExtended> extendedTokens = new ArrayList<>();

    private final HashMap<VertexExtended, HashSet<VertexExtended>> collision = new HashMap<>();
    private final HashMap<Edge, EdgeExtended> edgeMap = new HashMap<>();

    private final Group graphGroup = new Group();
    private final Affine graphGroupAffine = new Affine();
    private final DoubleProperty cameraDistance = new SimpleDoubleProperty(900);

    GraphScene(Graph graph, VertexSet tokens) {
        super(new Group(), 1, 1, true, SceneAntialiasing.BALANCED);

        this.graph = graph;
        this.tokens = tokens;
        ((Group) getRoot()).getChildren().add(graphGroup);

        graphGroup.translateZProperty().bind(cameraDistance);
        graphGroup.getTransforms().add(graphGroupAffine);

        setCamera(buildCamera());

    }

    synchronized void buildGraphAndTokens() {
        extendedVertices.forEach(v -> v.deleteFromObserver());
        extendedVertices.clear();
        extendedTokens.forEach(v -> v.deleteFromObserver());
        extendedTokens.clear();
        extendedEdges.forEach(v -> v.deleteFromObservers());
        extendedEdges.clear();

        graphGroup.getChildren().clear();

        graph.getEdges().stream().forEach((iEdge) -> {
            EdgeExtended edge = new EdgeExtended(iEdge);
            extendedEdges.add(edge);
            edge.addToObservers();
            edgeMap.put(iEdge, edge);
            graphGroup.getChildren().add(edge);
        });
        graph.getVertices().stream().forEach((iVertex) -> {
            VertexExtended vertex = new VertexExtended(iVertex);
            extendedVertices.add(vertex);
            vertex.addToObserver();
            graphGroup.getChildren().add(vertex);
        });
        tokens.getVertices().stream().forEach((iVertex) -> {
            TokenExtended token = new TokenExtended(iVertex);
            extendedTokens.add(token);
            token.addToObserver();
            graphGroup.getChildren().add(token);
        });

    }

    synchronized void toggleEdgeIfCollision() {
        extendedVertices.stream()
                .forEach((iVertexExtended) -> {
                    extendedVertices.stream()
                    .filter((jVertexExtended) -> (iVertexExtended.vertex.id < jVertexExtended.vertex.id))
                    .forEach((jVertexExtended) -> {
                        if (!collision.containsKey(iVertexExtended)) {
                            collision.put(iVertexExtended, new HashSet<>());
                        }
                        if (iVertexExtended.getLocalToSceneTransform().transform(
                                iVertexExtended.getTranslateX(),
                                iVertexExtended.getTranslateY(),
                                iVertexExtended.getTranslateZ()).angle(jVertexExtended.getLocalToSceneTransform().transform(
                                        jVertexExtended.getTranslateX(),
                                        jVertexExtended.getTranslateY(),
                                        jVertexExtended.getTranslateZ())) < 5.0) {
                            if (!collision.get(iVertexExtended).contains(jVertexExtended)) {
                                collision.get(iVertexExtended).add(jVertexExtended);
                                Edge edgeExists = graph.getEdge(iVertexExtended.vertex, jVertexExtended.vertex);
                                if (edgeExists == null) {
                                    Edge iEdge = graph.addEdge(
                                            iVertexExtended.vertex,
                                            jVertexExtended.vertex,
                                            WebColor.getRandomGrey());
                                    EdgeExtended edge = new EdgeExtended(iEdge);
                                    edgeMap.put(iEdge, edge);
                                    extendedEdges.add(edge);
                                    edge.addToObservers();
                                    graphGroup.getChildren().add(edge);
                                } else {
                                    EdgeExtended ee = edgeMap.get(edgeExists);
                                    edgeExists.deleteObserver(ee);
                                    extendedEdges.remove(ee);
                                    graphGroup.getChildren().remove(ee);
                                    graph.deleteEdge(edgeExists);
                                }
                            }
                        } else {
                            collision.get(iVertexExtended).remove(jVertexExtended);
                        }
                    });
                });

    }

    void zoom(double amount) {
        cameraDistance.setValue(cameraDistance.getValue() * amount);
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> buildGraphAndTokens());
        // buildGraphAndTokens();
    }

    private PerspectiveCamera buildCamera() {
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        ((Group) getRoot()).getChildren().add(camera);
        return camera;
    }

    private double moveGraphPosX;
    private double moveGraphPosY;
    private double moveGraphOldX;
    private double moveGraphOldY;

    private double moveSpherePosX;
    private double moveSpherePosY;
    private double moveSphereOldX;
    private double moveSphereOldY;

    private VertexExtended pickedSphere = null;

    @Override
    public void handle(MouseEvent me) {
        if (me.getTarget() instanceof VertexExtended
                || me.getTarget() instanceof EdgeExtended) {
            if (me.getEventType() == MouseEvent.MOUSE_PRESSED) {
                moveSpherePosX = me.getSceneX();
                moveSpherePosY = me.getSceneY();
                moveSphereOldX = moveSpherePosX;
                moveSphereOldY = moveSpherePosY;
                PickResult pr = me.getPickResult();
                if (pr != null
                        && pr.getIntersectedNode() != null
                        && pr.getIntersectedNode() instanceof VertexExtended) {

                    pickedSphere = (VertexExtended) pr.getIntersectedNode();
                }
                if (me.getClickCount() > 1
                        && pr != null
                        && pr.getIntersectedNode() != null) {
                    pickColor(pr.getIntersectedNode());
                }
            } else if (me.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                moveSpherePosX = me.getSceneX();
                moveSpherePosY = me.getSceneY();
                if (pickedSphere != null) {
                    Point3D p3d = graphGroup.localToParent(
                            pickedSphere.getTranslateX(),
                            pickedSphere.getTranslateY(),
                            pickedSphere.getTranslateZ());
                    p3d = p3d.add(moveSpherePosX - moveSphereOldX,
                            moveSpherePosY - moveSphereOldY, 0);
                    p3d = graphGroup.parentToLocal(p3d);
                    if (pickedSphere instanceof VertexExtended) {
                        VertexExtended ve = (VertexExtended) pickedSphere;
                        ve.vertex.set(p3d.getX(), p3d.getY(), p3d.getZ());
                        toggleEdgeIfCollision();
                    }

                    moveSphereOldX = moveSpherePosX;
                    moveSphereOldY = moveSpherePosY;
                }
            } else if (me.getEventType() == MouseEvent.MOUSE_PRESSED) {
                pickedSphere = null;
            }
            me.consume();
        } else {
            if (me.getEventType() == MouseEvent.MOUSE_PRESSED) {
                moveGraphPosX = me.getSceneX();
                moveGraphPosY = me.getSceneY();
                moveGraphOldX = me.getSceneX();
                moveGraphOldY = me.getSceneY();
            } else if (me.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                moveGraphOldX = moveGraphPosX;
                moveGraphOldY = moveGraphPosY;
                moveGraphPosX = me.getSceneX();
                moveGraphPosY = me.getSceneY();
                double moveGraphDeltaX = (moveGraphPosX - moveGraphOldX);
                double moveGraphDeltaY = (moveGraphPosY - moveGraphOldY);

                double factor = (me.isControlDown())
                        ? 0.01
                        : (me.isShiftDown())
                                ? 1.0
                                : 0.1;
                if (me.isPrimaryButtonDown()) {
                    graphGroupAffine.prepend(new Rotate(moveGraphDeltaY * factor, Rotate.X_AXIS));
                    graphGroupAffine.prepend(new Rotate(-moveGraphDeltaX * factor, Rotate.Y_AXIS));
                }
            }
        }
    }

    void pickColor(final Node n) {
        if (n instanceof EdgeExtended || n instanceof VertexExtended) {
            String current;
            if (n instanceof EdgeExtended) {
                current = ((EdgeExtended) n).edge.getColor();
            } else {
                current = ((VertexExtended) n).vertex.getColor();
            }

            final ColorPicker colorPicker = new ColorPicker();
            colorPicker.setValue(Color.web(current));

            Scene colorScene = new Scene(colorPicker);
            Stage colorStage = new Stage();
            colorStage.setScene(colorScene);
            colorStage.initStyle(StageStyle.DECORATED);
            colorStage.initModality(Modality.APPLICATION_MODAL);
            colorStage.show();

            colorPicker.setOnAction(t -> {
                if (n instanceof EdgeExtended) {
                    ((EdgeExtended) n).edge.setColor(WebColor.getColor(colorPicker.getValue()));
                } else {
                    ((VertexExtended) n).vertex.setColor(WebColor.getColor(colorPicker.getValue()));
                }
            });
        }
    }
}
