package com.monochromechameleon.apd.cw3;

/**
 * Interface defining the common methods on RacingDogBot and PlainDogBot
 */
public interface DogBot {
    boolean eat();
    void rest();
    void play();
    String noise();
}
