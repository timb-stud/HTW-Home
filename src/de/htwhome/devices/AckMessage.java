package de.htwhome.devices;

/**
 *
 * @author Tim Bartsch
 */
public class AckMessage<T> {
    private int id;
    private T status;

    public AckMessage(int id, T status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public T getStatus() {
        return status;
    }

    public void setStatus(T status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AckMessage{" + "id=" + id + "status=" + status + '}';
    }

}
