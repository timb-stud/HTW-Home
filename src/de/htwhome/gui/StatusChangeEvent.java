package de.htwhome.gui;

import java.util.EventObject;

/**
 *
 * @author Volkan Gökkaya
 * Diese Klasse ist Observable
 * Alle Aktualisierungen der GUI laufen ueber diese Kasse
 */
public class StatusChangeEvent<T> extends EventObject {

    private T status;

    /*
     * Die Aktualisierung des Status ist in dieser Methode implementiert
     */
    public StatusChangeEvent(Object source, T status) {
        super(source);
        this.status = status;
    }
    /*
     * Liefert den aktuellen Status
     */

    public T getStatus() {
        return status;
    }
    /*
     * Der Status wird verändert
     */

    public void setStatus(T status) {
        this.status = status;
    }
}
