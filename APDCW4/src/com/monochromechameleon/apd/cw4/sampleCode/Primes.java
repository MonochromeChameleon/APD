package com.monochromechameleon.apd.cw4.sampleCode;

import java.util.Iterator;
import java.util.LinkedList;

class Primes implements Iterator<Integer> {

    private final LinkedList<Integer> primes;
    private final Counter counter;

    public Primes() {
        primes = new LinkedList<>();
        counter = new Counter();
        counter.next();
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        int trial = counter.next();
        int i = 1;
        while (i < primes.size()) {
            i = 1;
            for (Integer p : primes) {
                if (trial % p == 0) {
                    trial = counter.next();
                    break;
                } else {
                    i++;
                }
            }
        }
        primes.add(trial);
        return trial;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
