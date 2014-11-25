package com.monochromechameleon.apd.cw4;

public class LongerThan implements Checker<String> {

    private final int than;
    
    public LongerThan(int longer) {
        this.than = longer;
    }
    
    @Override
    public boolean check(String val) {
        return val.length() > than;
    }    
}
