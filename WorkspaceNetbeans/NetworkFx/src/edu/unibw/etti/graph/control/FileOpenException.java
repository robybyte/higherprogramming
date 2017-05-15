package edu.unibw.etti.graph.control;

/**
 * Ausnahmetyp fuer alle Probleme und Ausnahmen, die beim Oeffnen einer Datei
 * auftreten koennen.
 *
 * @author Andrea Baumann
 */
public class FileOpenException extends Exception {

    public FileOpenException(String message) {
        super(message);
    }

    public FileOpenException(String message, Throwable cause) {
        super(message, cause);
    }

}
