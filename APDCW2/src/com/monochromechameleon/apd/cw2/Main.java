package com.monochromechameleon.apd.cw2;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        PolyOperation po = new PolyOperation(args);
        System.out.println(po.toString());
    }
}
