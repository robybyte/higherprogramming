package edu.unibw.etti.graph.model;

import java.util.Observable;

/**
 * Die Knoten des Graphen.
 *
 * @author Andrea Baumann
 * @see Graph
 * @see Edge
 */
public class Vertex extends Observable {

    private static int unicNumber = 0;

    /**
     * Eindeutige ID der Knoten.
     */
    public final int id;

    private String color = null;

    private double x = 0.0;
    private double y = 0.0;
    private double z = 0.0;
    private boolean free = true;

    Vertex(double x, double y, double z, String color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;

        id = unicNumber++;
    }

    /**
     * Auslesen der x-Koordinate des Knotens.
     *
     * @return x-Koordinate des Knotens.
     */
    public double getX() {
        return x;
    }

    /**
     * Auslesen der y-Koordinate des Knotens.
     *
     * @return y-Koordinate des Knotens.
     */
    public double getY() {
        return y;
    }

    /**
     * z-Koordinate des Knotens.
     *
     * @return z-Koordinate des Knotens.
     */
    public double getZ() {
        return z;
    }

    /**
     * Abstand zu einem anderen Knoten bestimmen.
     *
     * @param v Knoten zu dem der Abstand berechnet werden soll - darf nicht
     * null sein.
     * @return Abstand zu einem anderen Knoten.
     */
    public double distance(Vertex v) {
        return Math.sqrt(
                Math.pow(x - v.x, 2.0)
                + Math.pow(y - v.y, 2.0)
                + Math.pow(z - v.z, 2.0));
    }

    /**
     * &Auml;nderung des Ortes des Knotens um einen bestimmten Betrag in x-, y-
     * und z-Richtung.
     *
     * @param x &Auml;nderung in x-Richtung.
     * @param y &Auml;nderung in y-Richtung.
     * @param z &Auml;nderung in z-Richtung.
     */
    public void shift(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;

        setChanged();
        notifyObservers();
    }

    /**
     * Setzen des Ortes des Knotens an eine andere Position.
     *
     * @param x x-Koordinate des Knotens.
     * @param y y-Koordinate des Knotens.
     * @param z z-Koordinate des Knotens.
     */
    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

        setChanged();
        notifyObservers();
    }

    /**
     * Setzen der Farbe der Kante in Hex (z.B. &#35;FF8C15).
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

    public synchronized boolean getResource(long maxWaitingTime) throws InterruptedException {
        if (free) {
            free = false;
            return true;
        } else {
            wait(maxWaitingTime);
            return false;
        }
    }
    
    public synchronized void freeResource() {
        notifyAll();
        free = true;
    }
    // TODO Aufgabe c, h, i
}
