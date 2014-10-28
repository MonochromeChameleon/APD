package com.monochromechameleon.apd.cw3;

/**
 *
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ObservableDogBot patch = ObservableDogBot.create("Patch", 7, 5);
        ObservableDogBot rover = ObservableDogBot.createRacing("Rover", 4, 2);
        
        DogWatcher henry = new DogWatcher("Henry");
        DogWatcher jim = new DogWatcher("Jim");
        
        henry.watch(patch);
        jim.watch(rover);
        
        patch.play();
        rover.eat();
    }
}
