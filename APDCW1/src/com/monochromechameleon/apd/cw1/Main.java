package com.monochromechameleon.apd.cw1;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System sys = new System(0);
        sys.run(100, "constantOutsideTemp.csv");
        
        System sys2 = new System(-0.1);
        sys2.run(100, "variableOutsideTemp.csv");
    }
}
