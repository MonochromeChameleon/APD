package com.monochromechameleon.apd.cw5;

public class AddIntegers implements Joiner<Integer, Integer> {

    @Override
    public Integer join(Integer f, Integer b) {
        return f + b;
    }
}
