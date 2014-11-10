package com.monochromechameleon.apd.cw3;

/**
 * Modification of the DogBot lab file
 */
public class PlainDogBot implements DogBot {

    protected int hungry;
    protected int tired;
    protected final String name;

    public PlainDogBot(String n, int h, int t) {
        name = n;
        hungry = h;
        tired = t;
    }

    @Override
    public boolean eat() {
        if (hungry > 6) {
            hungry -= 3;
            return true;
        }
        return false;
    }

    @Override
    public void rest() {
        hungry += 1;
        tired -= 2;
    }

    @Override
    public void play() {
        hungry += 2;
        tired += 3;
    }

    @Override
    public String noise() {
        if (hungry > 8 && tired < 11) {
            return "whine";
        } 
        if (tired > 7 && tired > hungry) {
            return "snore";
        }
        return "woof";
    }
    
    @Override
    public String toString() {
        return name;
    }
}
