package edu.unibw.etti.graph.control;

import edu.unibw.etti.graph.model.Edge;
import edu.unibw.etti.graph.model.Graph;
import edu.unibw.etti.graph.model.Vertex;
import edu.unibw.etti.graph.model.VertexSet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Lesen eines Graphen aus einer Datei und schreiben eines Graphen in eine
 * Datei.
 *
 * @author Sie
 */
public class FileControl {

    private final Graph graph;
    private static final Pattern patternKnoten = Pattern.compile("vertex\\{id:\"([0-9]+)\","
            + "xPos:\"([-]?[0-9]+)\","
            + "yPos:\"([-]?[0-9]+)\","
            + "zPos:\"([-]?[0-9]+)\","
            + "color:\"(#[0-9A-Fa-f]{6}?)\"\\}");

    private static final Pattern patternKante = Pattern.compile("edge\\{from:\"([0-9]+)\","
            + "to:\"([0-9]+)\","
            + "color:\"(#[0-9A-Fa-f]{6}?)\"\\}");

    public FileControl(Graph graph) {
        this.graph = graph;
    }

    public void saveFile(File file) throws FileSaveException {
        System.out.println(file.getPath());
        String out = "";
        try (FileWriter fw = new FileWriter(file)) {
            //start with the vertex
            for (Vertex v : graph.getVertices()) {
                // vertex{id: "0000", xPos: "10", yPos: "5", zPos: "15", color: "#6611ff"} 
                out = "vertex{id:\"" + v.id + "\","
                        + "xPos:\"" + (int) v.getX() + "\","
                        + "yPos:\"" + (int) v.getY() + "\","
                        + "zPos:\"" + (int) v.getZ() + "\","
                        + "color:\"" + v.getColor() + "\"}";
                fw.write(out + "\n");
            }
            for (Edge e : graph.getEdges()) {
                //edge{from: "2002", to: "1001", color: "#114455"}
                out = "edge{from:\"" + e.fromVertex.id + "\",to:\"" + e.toVertex.id
                        + "\",color:\"" + e.getColor() + "\"}";
                fw.write(out + "\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(FileControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void openFile(File file) throws FileOpenException {
        System.out.println(file.getPath());
        HashMap<Integer, Vertex> hMap = new HashMap<>();

        Matcher m1 = null;
        Matcher m2 = null;
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready()) {

                line = br.readLine();
                System.out.println("Line: " + line);
                m1 = patternKnoten.matcher(line);

                if (m1.matches()) {
                    hMap.put(Integer.parseInt(m1.group(1)),
                            graph.addVertex(Integer.parseInt(m1.group(2)),
                                    Integer.parseInt(m1.group(3)),
                                    Integer.parseInt(m1.group(4)),
                                    m1.group(5)));
                } else {
                    m2 = patternKante.matcher(line);
                    if (m2.matches()) {
                        graph.addEdge(hMap.get(Integer.parseInt(m2.group(1))),
                                hMap.get(Integer.parseInt(m2.group(2))),
                                m2.group(3));
                    }
                }
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(FileControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO Aufgabe f

        graph.update();
    }

}
