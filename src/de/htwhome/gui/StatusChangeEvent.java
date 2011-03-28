package de.htwhome.gui;

import java.util.EventObject;

/**
 *
 * @author Volkan Gökkaya
 */
public class StatusChangeEvent<T> extends EventObject {

    private T status;

    public StatusChangeEvent(Object source, T status) {
        super(source);
        this.status = status;
    }

    public T getStatus() {
        return status;
    }

    public void setStatus(T status) {
        this.status = status;
    }
}
