package com.monochromechameleon.apd.cw3;

import java.util.Observable;

/**
 * Observable decorator of the DogBot interface
 */
public class ObservableDogBot extends Observable implements DogBot {

    private final DogBot innerDogBot;
    
    /**
     * Factory method for creating an observable version of a PlainDogBot
     * 
     * @param n The name of the dogBot
     * @param h The hungry value of the dogBot
     * @param t The tired value of the dogBot
     * 
     * @return A new ObservableDogBot, wrapping a PlainDogBot
     */
    public static ObservableDogBot create(String n, int h, int t) {
        DogBot dog = new PlainDogBot(n, h, t);
        return new ObservableDogBot(dog);
    }
    
    /**
     * Factory method for creating an observable version of a RacingDogBot
     * 
     * @param n The name of the dogBot
     * @param h The hungry value of the dogBot
     * @param t The tired value of the dogBot
     * 
     * @return A new ObservableDogBot, wrapping a RacingDogBot
     */
    public static ObservableDogBot createRacing(String n, int h, int t) {
        DogBot dog = new RacingDogBot(n, h, t);
        return new ObservableDogBot(dog);
    }
    
    private ObservableDogBot(DogBot dog) {
        innerDogBot = dog;
    }
    
    private void doNotify(String what) {
        setChanged();
        notifyObservers(what);
        clearChanged();
    }
    
    @Override
    public boolean eat() {
        boolean result = innerDogBot.eat();
        if (result) {
            // Only notify if eating has occurred.
            doNotify("eat");
        }
        return result;
    }

    @Override
    public void rest() {
        innerDogBot.rest();
        doNotify("rest");
    }

    @Override
    public void play() {
        innerDogBot.play();
        doNotify("play");
    }

    @Override
    public String noise() {
        // Specification says A DogWatcher object should react whenever a method
        // causes its state to change. This is a non-changing method, so no 
        // observation required.
        return innerDogBot.noise();
    }
    
    @Override
    public String toString() {
        return innerDogBot.toString();
    }
}