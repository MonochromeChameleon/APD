package com.monochromechameleon.apd.cw3;

import java.util.Observable;
import java.util.Observer;

/**
 * Observer for watching our DogBots
 */
public class DogWatcher implements Observer {
    
    private final String name;
    
    public DogWatcher(String n) {
        name = n;
    }

    /**
     * Assign this as an observer of the given dogBot
     * 
     * @param dog the DogBot to observe
     */
    public void watch(Observable dog) {
        dog.addObserver(this);
    }
    
    /**
     * Remove this as an observer of the given dogBot
     * 
     * @param dog the DogBot to stop observing
     */
    public void stopWatching(Observable dog) {
        dog.deleteObserver(this);
    }
    
    /**
     * Called when any dogBot currently under observation updates
     * 
     * @param o the Observable triggering the update
     * @param arg any parameters sent along with the update
     */
    @Override
    public void update(Observable o, Object arg) {
        String output = String.format("%s observes %s %s", name, o, arg);
        System.out.println(output);
    }
}
