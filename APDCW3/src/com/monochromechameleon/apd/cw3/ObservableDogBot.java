package com.monochromechameleon.apd.cw3;

import java.util.Observable;

/**
 *
 */
public class ObservableDogBot extends Observable implements DogBot {

    private final DogBot innerDogBot;
    
    public static ObservableDogBot create(String n, int h, int t) {
        DogBot dog = new PlainDogBot(n, h, t);
        return new ObservableDogBot(dog);
    }
    
    public static ObservableDogBot createRacing(String n, int h, int t) {
        DogBot dog = new RacingDogBot(n, h, t);
        return new ObservableDogBot(dog);
    }
    
    private ObservableDogBot(DogBot dog) {
        innerDogBot = dog;
    }
    
    @Override
    public boolean eat() {
        boolean result = innerDogBot.eat();
        this.setChanged();
        this.notifyObservers("eat");
        this.clearChanged();
        return result;
    }

    @Override
    public void rest() {
        innerDogBot.rest();
        this.setChanged();
        this.notifyObservers("rest");
        this.clearChanged();
    }

    @Override
    public void play() {
        innerDogBot.play();
        this.setChanged();
        this.notifyObservers("play");
        this.clearChanged();
    }

    @Override
    public String noise() {
        return innerDogBot.noise();
    }
    
    @Override
    public String toString() {
        return innerDogBot.toString();
    }
}
