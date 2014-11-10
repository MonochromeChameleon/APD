package com.monochromechameleon.apd.cw3;

/**
 * Test runner for the Observable dog bots
 */
public class Main {

    public static void main(String[] args) {
        ObservableDogBot patch = ObservableDogBot.create("Patch", 7, 5);
        ObservableDogBot rover = ObservableDogBot.createRacing("Rover", 4, 2);
        
        DogWatcher henry = new DogWatcher("Henry");
        DogWatcher jim = new DogWatcher("Jim");
        
        henry.watch(patch);
        jim.watch(rover);
        
        patch.play();
        rover.eat(); // Rover isn't hungry -> no output
        patch.eat();
        rover.rest();
        patch.rest();
        rover.play();
        rover.eat(); // Now Rover eats.
        
        jim.stopWatching(rover);
        jim.watch(patch);
        
        rover.play(); // Nobody is watching -> no output
        patch.play(); // Both watch
    }
}
