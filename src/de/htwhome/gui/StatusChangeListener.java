package de.htwhome.gui;

/**
 *
 * @author Volkan Gökkaya
 * Diese Klasse ist eine Observer Klasse
 * Sie bekommt Bescheid gesagt, wenn Änderungen vorgenommen wurden (im Status)
 */
public interface StatusChangeListener {
    /*
     * Event, das ausgelöst wird, falls ein Status sich ändert
     */

    void changeEventReceived(StatusChangeEvent evt);
}
