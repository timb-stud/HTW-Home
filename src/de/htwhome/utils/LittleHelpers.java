/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.utils;

/**
 *
 * @author christian
 */
public class LittleHelpers {

    public static Double randomMeasurement() {
        Double measure = Math.random() * 10;
        measure = Math.round( measure * 100. ) / 100.;
        return measure;
    }

}
