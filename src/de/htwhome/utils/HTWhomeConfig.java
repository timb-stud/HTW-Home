/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwhome.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author christian
 */
public class HTWhomeConfig {

    private String filename;

    public HTWhomeConfig(String filename) {
        this.filename = filename;
    }

    public void store(){

    }

    public void load() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
