package edu.unibw.etti.graph.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In der Klasse Graph koennen Graphen gespeichert werden.
 *
 * @author Andrea Baumann
 * @see Edge
 * @see Vertex
 */
public class Graph extends Observable {

    private final ArrayList<Vertex> vertices = new ArrayList<>();
    private final List<Vertex> verticesUnmodifiable = Collections.unmodifiableList(vertices);
    private final ArrayList<Edge> edges = new ArrayList<>();
    private final List<Edge> edgesUnmodifiable = Collections.unmodifiableList(edges);
    private final Map<Vertex, ArrayList<Edge>> vertexToEdge = new ConcurrentHashMap<>();
    private final Map<Vertex, ArrayList<Vertex>> vertexToVertex = new ConcurrentHashMap<>();

    /**
     * Fuegt einen neuen Knoten in den Graphen ein.
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

        vertexToEdge.put(vertex, new ArrayList<>());
        vertexToVertex.put(vertex, new ArrayList<>());

        setChanged();
        return vertex;
    }

    /**
     * Gibt die Kante zurueck, die die beiden Knoten verbindet - oder null.
     *
     * @param from Der eine Knoten.
     * @param to Der andere Knoten.
     * @return Gibt die Kante zurueck, die die beiden Knoten verbindet - oder
     * null.
     * @see Edge
     * @see Vertex
     */
    public Edge getEdge(Vertex from, Vertex to) {
        try {
            for (Edge edge : vertexToEdge.get(from)) {
                if (edge.fromVertex == to || edge.toVertex == to) {
                    return edge;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Fuegt eine neue Kante in den Graphen ein.
     *
     * @param fromVertex Der erste Knoten der Kante. Der Knoten darf nicht
     * "null" sein und muss schon zuvor mit der Methode
     * {@link #addVertex(int, int, int, String) addVertex} ergaenzt worden sein.
     * @param toVertex Der zweite Knoten der Kante. Der Knoten darf nicht "null"
     * sein und muss schon zuvor mit der Methode ergaenzt worden sein.
     * @param color Farbe der Kante. Die Farbe darf nicht gleich "null" sein!
     * @return Die neue Kante wird zurueckgegeben.
     * @see Edge
     * @see Vertex
     */
    public Edge addEdge(Vertex fromVertex, Vertex toVertex, String color) {
        Edge edge = new Edge(fromVertex, toVertex, color);
        edges.add(edge);

        vertexToEdge.get(fromVertex).add(edge);
        vertexToEdge.get(toVertex).add(edge);
        vertexToVertex.get(fromVertex).add(toVertex);
        vertexToVertex.get(toVertex).add(fromVertex);

        setChanged();
        return edge;
    }

    /**
     * Entfernt eine Kante aus dem Graphen.
     *
     * @param edge Die Kante die geloescht werden soll. Die Kante darf nicht
     * "null" sein.
     * @see Edge
     */
    public void deleteEdge(Edge edge) {
        edges.remove(edge);

        vertexToEdge.get(edge.fromVertex).remove(edge);
        vertexToEdge.get(edge.toVertex).remove(edge);
        vertexToVertex.get(edge.fromVertex).remove(edge.toVertex);
        vertexToVertex.get(edge.toVertex).remove(edge.fromVertex);

        setChanged();
    }

    /**
     * Gibt eine Sammlung aller Knoten des Graphen zurueck.
     *
     * @return Sammlung aller Knoten des Graphen.
     * @see Vertex
     */
    public Collection<Vertex> getVertices() {
        return verticesUnmodifiable;
    }

    /**
     * Gibt eine Sammlung aller Kanten des Graphen zurueck.
     *
     * @return Sammlung aller Kanten des Graphen.
     * @see Edge
     */
    public Collection<Edge> getEdges() {
        return edgesUnmodifiable;
    }

    /**
     * Gibt einen zufaeligen Nachbarknoten zurueck - oder null.
     *
     * @param vertex Der Knoten fuer den ein zufaelliger Nachbar gesucht wird.
     * @return Ein zufaelliger Nachbarknoten - oder null, falls kein Nachbar
     * existiert.
     */
    public Vertex getRandomNeighbour(Vertex vertex) {
        try {
            ArrayList<Vertex> neighbours = vertexToVertex.get(vertex);
            if (neighbours.isEmpty()) {
                return null;
            } else {
                return neighbours.get((int) (Math.random() * neighbours.size()));
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Gibt einen zufaeligen Knoten zurueck - oder null.
     *
     * @return Eine zufaelliger Knoten - oder null, falls kein Knoten existiert.
     */
    public Vertex getRandomVertex() {
        try {
            if (vertices.isEmpty()) {
                return null;
            } else {
                return vertices.get((int) (Math.random() * vertices.size()));
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Gibt allen Beobachtern Bescheid, dass sich etwas geaendert hat.
     */
    public void update() {
        notifyObservers();
    }

    /**
     * Loescht alle Knoten und Kanten des Graphen.
     */
    public void clear() {
        vertices.clear();
        edges.clear();
        vertexToEdge.clear();
        vertexToVertex.clear();

        setChanged();
        notifyObservers();
    }
    
}
