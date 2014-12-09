package com.monochromechameleon.apd.cw5;

public class MaxLength implements Join<String, Integer> {

    @Override
    public Integer join(String f, Integer b) {
        return f.length() > b ? f.length() : b;
    }
}
