package edu.unibw.etti.graph.control;

/**
 * Ausnahmetyp fuer alle Probleme und Ausnahmen, die beim Speichern eines
 * Graphen in einer Datei auftreten koennen.
 *
 * @author Andrea Baumann
 */
public class FileSaveException extends Exception {

    public FileSaveException(String message) {
        super(message);
    }

    public FileSaveException(String message, Throwable cause) {
        super(message, cause);
    }

}
