// Aufgabe C
package edu.unibw.etti.graph.control;

import edu.unibw.etti.graph.model.Edge;
import edu.unibw.etti.graph.model.Graph;
import edu.unibw.etti.graph.model.Vertex;

/**
 * Thread zum Bewegen eines Knotens auf dem Graphen.
 *
 * @author Sie
 */
public class TokenThread extends Thread {

    private final Graph graph;
    private final Vertex token;

    // TODO Aufgabe g, h, i 
    public TokenThread(Graph graph, Vertex token) {
        this.graph = graph;
        this.token = token;
    }

    @Override
    public void run() {
        // TODO Aufgabe g, h, i
        Vertex v = graph.getRandomVertex(); //start
        Vertex z = graph.getRandomNeighbour(v); // Ziel
        token.set(v.getX(), v.getY(), v.getZ());
        boolean gotResourceZ = false;
        boolean gotResourceV = false;
        Edge e = graph.getEdge(v, z);
        String color1 = v.getColor(); // Just once needed!
        String edgeColor = "";
        try {
            while (Thread.currentThread().isAlive()) {

                if (!gotResourceV) {
                    gotResourceV = v.getResource(Long.MAX_VALUE);
                    color1 = v.getColor();
                    v.setColor(token.getColor());
                }
                if (!gotResourceZ) {
                    while (!z.getResource(5000)) {
                        z.freeResource();
                        z = graph.getRandomNeighbour(v);
                    }
                    e = graph.getEdge(v, z);
                    edgeColor = e.getColor();
                    e.setColor(token.getColor());
                    gotResourceZ = true;
                    z.setColor(token.getColor());
                }
                if (gotResourceV && gotResourceZ) {
                    token.shift((z.getX() - token.getX()) / token.distance(z),
                            (z.getY() - token.getY()) / token.distance(z),
                            (z.getZ() - token.getZ()) / token.distance(z));
                } else {
                    z = graph.getRandomNeighbour(v);
                }
                if (token.distance(z) <= 1.0) {
                    e.setColor(edgeColor);
                    v.freeResource();
                    v.setColor(color1);
                    v = z;
                    z = graph.getRandomNeighbour(v);
                    gotResourceZ = false;
                }
                sleep(10);
            }
        } catch (InterruptedException ex) {
            System.err.println(ex.toString());
        }
        //v.shift(MAX_PRIORITY, MIN_PRIORITY, MIN_PRIORITY);
        //   v.setColor("#ffffff");
    }
}
