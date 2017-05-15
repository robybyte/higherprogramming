package edu.unibw.etti.graph.model;

import java.util.Observable;

/**
 * Die Kanten des Graphen.
 *
 * @author Andrea Baumann
 * @see Graph
 * @see Vertex
 */
public class Edge extends Observable {

    private static int unicNumber = 0;

    /**
     * Eindeutige ID der Kante.
     */
    public final int id;

    /**
     * Startknoten der Kante.
     */
    public final Vertex fromVertex;
    /**
     * Endknoten der Kante.
     */
    public final Vertex toVertex;

    private String color = null;

    Edge(Vertex from, Vertex to, String color) {
        fromVertex = from;
        toVertex = to;
        this.color = color;

        id = unicNumber++;
    }

    /**
     * Auslesen der Farbe der Kante in Hex (z.B. &#35;FF8C15).
     *
     * @return Farbe der Kante.
     */
    public String getColor() {
        return color;
    }

    /**
     * Setzen der Farbe der Kante in Hex (z.B. &#35;FF8C15).
     *
     * @param color Farbe der Kante - darf nicht null sein.
     */
    public void setColor(String color) {
        this.color = color;

        setChanged();
        notifyObservers();
    }

    /**
     * Laenge der Kante.
     *
     * @return Laenge der Kante.
     */
    public double getLength() {
        return fromVertex.distance(toVertex);
    }

    // TODO Aufgabe c
}
