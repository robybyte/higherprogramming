package edu.unibw.etti.graph.model;

import javafx.scene.paint.Color;

/**
 * Klasse um zufaellige Hex-Farberte zu erzeugen.
 * @author Andrea Baumann
 */
public class WebColor {

    private static final char[] COLOR_CHARS = {
        '0', '1', '2', '3', '4',
        '5', '6', '7', '8', '9',
        'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] COLOR_WEB = {
        '#', '0', '0', '0', '0', '0', '0'};
    private static final int COLOR_CHARS_LENGTH = COLOR_CHARS.length;

    /**
     * Zufaelligen Hex-Farberte erzeugen.
     * @return Zufaelliger Hex-Farbert (z.B. #00FF00).
     */
    public synchronized static String getRandom() {
        for (int i = 1; i < COLOR_WEB.length; i++) {
            COLOR_WEB[i] = COLOR_CHARS[(int) (Math.random() * COLOR_CHARS_LENGTH)];
        }
        return new String(COLOR_WEB);
    }

    /**
     * Zufaelligen violetten Hex-Farberte erzeugen.
     * @return Zufaelliger violetter Hex-Farbert (z.B. #A100A1).
     */
    public synchronized static String getRandomViolet() {
        char c0 = COLOR_CHARS[(int) (Math.random() * COLOR_CHARS_LENGTH)];
        char c1 = COLOR_CHARS[(int) (Math.random() * COLOR_CHARS_LENGTH)];
        COLOR_WEB[1] = c0;
        COLOR_WEB[2] = c1;
        COLOR_WEB[3] = '0'; 
        COLOR_WEB[4] = '0'; 
        COLOR_WEB[5] = c0;
        COLOR_WEB[6] = c1;
        return new String(COLOR_WEB);
    }

    /**
     * Zufaelligen graen-blauen Hex-Farberte erzeugen.
     * @return Zufaelliger graen-blauer Hex-Farbert (z.B. #00A11A).
     */
    public synchronized static String getRandomCyan() {
        int i0 = (int) (Math.random() * COLOR_CHARS_LENGTH);
        int i1 = (int) (Math.random() * COLOR_CHARS_LENGTH);
        COLOR_WEB[1] = '0'; 
        COLOR_WEB[2] = '0'; 
        COLOR_WEB[3] = COLOR_CHARS[COLOR_CHARS_LENGTH - 1 - i0];
        COLOR_WEB[4] = COLOR_CHARS[COLOR_CHARS_LENGTH - 1 - i1];
        COLOR_WEB[5] = COLOR_CHARS[COLOR_CHARS_LENGTH - 1 - i1];
        COLOR_WEB[6] = COLOR_CHARS[COLOR_CHARS_LENGTH - 1 - i0];
        return new String(COLOR_WEB);
    }

    /**
     * Zufaelligen grauen Hex-Farberte erzeugen.
     * @return Zufaelliger grauen Hex-Farbert (z.B. #121212).
     */
    public synchronized static String getRandomGrey() {
        char c0 = COLOR_CHARS[(int) (Math.random() * COLOR_CHARS_LENGTH)];
        char c1 = COLOR_CHARS[(int) (Math.random() * COLOR_CHARS_LENGTH)];
        COLOR_WEB[1] = c0;
        COLOR_WEB[2] = c1;
        COLOR_WEB[3] = c0; 
        COLOR_WEB[4] = c1; 
        COLOR_WEB[5] = c0;
        COLOR_WEB[6] = c1;
        return new String(COLOR_WEB);
    }

    /**
     * Umwandeln einer Farbe in einen Hex-Farbert.
     * @param value Die Farbe, die umgewandelt werden soll.
     * @return Raeckgabe der Farbe als Hex-Farbwert.
     */
    public static String getColor(Color value) {
        return "#"
                + Integer.toHexString((int) (value.getRed() * 256.0))
                + Integer.toHexString((int) (value.getGreen() * 256.0))
                + Integer.toHexString((int) (value.getBlue() * 256.0));
    }
}
