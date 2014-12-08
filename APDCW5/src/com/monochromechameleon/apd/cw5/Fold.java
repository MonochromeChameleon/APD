package com.monochromechameleon.apd.cw5;

import java.util.Iterator;

public class Fold {

    private static <Foo, Bar> Bar fold(Iterator<Foo> foos, Bar bar, Joiner<Foo, Bar> joiner) {
        if (foos.hasNext()) {
            return fold(foos, joiner.join(foos.next(), bar), joiner);
        }
        return bar;
    }
    
    public static <Foo, Bar> Bar fold(Iterable<Foo> foos, Bar bar, Joiner<Foo, Bar> joiner) {
        return fold(foos.iterator(), bar, joiner);
    }
}
