package com.monochromechameleon.apd.cw5;

public class MaxInteger implements Joiner<Integer, Integer> {

    @Override
    public Integer join(Integer f, Integer b) {
        return f > b ? f : b;
    }
}
