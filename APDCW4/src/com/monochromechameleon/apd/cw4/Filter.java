package com.monochromechameleon.apd.cw4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Filter<E> implements Iterator<E> {

    private final Checker<E> checker;
    private final Iterator<E> it;
    private E next;
    
    public Filter(Iterator<E> iterator, Checker<E> theChecker) {
        checker = theChecker;
        it = iterator;
    }
    
    @Override
    public boolean hasNext() {
        if (!it.hasNext()) {
            return false;
        }
        next = it.next();
        return checker.check(next) ? true : hasNext();
    }

    @Override
    public E next() {
        if (hasNext()) {
            return next;
        }
        throw new NoSuchElementException();
    }
}
