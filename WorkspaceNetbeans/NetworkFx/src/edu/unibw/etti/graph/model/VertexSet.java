package edu.unibw.etti.graph.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * Die Klasse VertexSet speichert Knoten als Multimenge.
 *
 * @author Andrea Baumann
 * @see Vertex
 */
public class VertexSet extends Observable {

    private final List<Vertex> vertices = Collections.synchronizedList(new ArrayList<>());
    private final List<Vertex> verticesUnmodifiable = Collections.unmodifiableList(vertices);

    /**
     * Ergaenzt einen neuen Knoten in der Knotenmenge.
     *
     * @param xPos X-Koordinate des Knotens
     * @param yPos Y-Koordinate des Knotens
     * @param zPos Z-Koordinate des Knotens
     * @param color Farbe des Knotens. Die Farbe darf nicht gleich "null" sein!
     * @return Der neue Knoten wird zurueckgegeben.
     * @see Vertex
     */
    public Vertex addVertex(int xPos, int yPos, int zPos, String color) {
        Vertex vertex = new Vertex(xPos, yPos, zPos, color);
        vertices.add(vertex);

        setChanged();
        notifyObservers();
        return vertex;
    }

    /**
     * Gibt eine Sammlung der Knoten der Knotenmenge zurueck.
     *
     * @return Sammlung der Knoten.
     * @see Vertex
     */
    public Collection<Vertex> getVertices() {
        return verticesUnmodifiable;
    }

    /**
     * Loescht alle Knoten und Kanten.
     */
    public void clear() {
        vertices.clear();

        setChanged();
        notifyObservers();
    }
}
