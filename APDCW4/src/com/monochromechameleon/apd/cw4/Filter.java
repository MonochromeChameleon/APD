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
        if (next != null) {
            // If we already know the next value, return true immediately.
            return true;
        }
        try {
            // Try to find the next valid value and recurse.
            getNext();
            return hasNext();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    
    private E getNext() {
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        next = it.next();
        return checker.check(next) ? next : getNext();
    }

    @Override
    public E next() {
        if (hasNext()) {
            E ret = next;
            next = null;
            return ret;
        }
        throw new NoSuchElementException();
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
