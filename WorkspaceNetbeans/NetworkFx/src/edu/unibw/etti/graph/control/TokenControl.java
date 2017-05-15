package edu.unibw.etti.graph.control;

import edu.unibw.etti.graph.model.Graph;
import edu.unibw.etti.graph.model.VertexSet;
import edu.unibw.etti.graph.model.Vertex;
import edu.unibw.etti.graph.model.WebColor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Kuemmert sich um das Erzeugen und Speichern neuer Tokens und die Verwaltung
 * der zugehöerigen TokenThreads.
 *
 * @author Andrea Baumann
 * @see VertexSet
 * @see TokenThread
 */
public class TokenControl {

    private final List<TokenThread> tokenThreads
            = Collections.synchronizedList(new ArrayList<>());
    private final VertexSet tokens;
    private final Graph graph;

    /**
     * Der Konstruktor erwartet einen Graphen und ein TokenSet.
     * 
     * @param graph Der Graph auf dem die Tokens entlangwandern sollen.
     * @param tokens Das TokenSet, in dem die Tokens gespeichert werden.
     */
    public TokenControl(Graph graph, VertexSet tokens) {
        this.tokens = tokens;
        this.graph = graph;
    }

    /**
     * Erzeugt einen neuen Knoten und den dazugehoerigen Thread.
     */
    public void startTokenThread() {
        if (!graph.getVertices().isEmpty()) {
            Vertex token = tokens.addVertex(0, 0, 0, WebColor.getRandomViolet());

            TokenThread tokenThread = new TokenThread(graph, token);
            tokenThreads.add(tokenThread);
            tokenThread.setDaemon(true);
            tokenThread.start();
        }
    }

    /**
     * Stoppt alle Threads und loescht die Knoten aus der Knotenmenge.
     */
    public void stopAllTokenThreads() {
        tokenThreads.stream().forEach((tokenThread) -> {
            tokenThread.interrupt();
        });
        tokenThreads.clear();
        tokens.clear();
    }
}
