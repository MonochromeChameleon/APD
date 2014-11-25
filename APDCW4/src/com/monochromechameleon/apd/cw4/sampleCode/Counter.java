package com.monochromechameleon.apd.cw4.sampleCode;

import java.util.Iterator;

class Counter implements Iterator<Integer> {

    int count;

    public Counter() {
        count = 1;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        return count++;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
