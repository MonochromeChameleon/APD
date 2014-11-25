package com.monochromechameleon.apd.cw4;

public class Odd implements Checker<Integer> {

    @Override
    public boolean check(Integer val) {
        return (val % 2) == 1;
    }    
}
