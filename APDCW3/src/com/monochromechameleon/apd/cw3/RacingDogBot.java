package com.monochromechameleon.apd.cw3;

/**
 *
 */
public class RacingDogBot extends PlainDogBot {

    public RacingDogBot(String n, int h, int t) {
        super(n, h, t);
    }

    public boolean race() {
        if (tired < 5) {
            hungry += 2;
            tired += 5;
            return true;
        }
        return false;
    }

    @Override
    public void rest() {
        hungry++;
        tired -= 3;
    }
}
