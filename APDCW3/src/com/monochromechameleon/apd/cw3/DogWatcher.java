package com.monochromechameleon.apd.cw3;

import java.util.Observable;
import java.util.Observer;

/**
 *
 */
public class DogWatcher implements Observer {
    
    private final String name;
    
    public DogWatcher(String n) {
        name = n;
    }

    public void watch(Observable dog) {
        dog.addObserver(this);
    }
    
    public void stopWatching(Observable dog) {
        dog.deleteObserver(this);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        String output = String.format("%s observes %s %s", name, o, arg);
        System.out.println(output);
    }
    
}
