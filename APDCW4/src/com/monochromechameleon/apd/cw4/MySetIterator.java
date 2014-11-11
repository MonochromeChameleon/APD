package com.monochromechameleon.apd.cw4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MySetIterator<E> implements Iterator<E> {
    
    protected int index;
    protected final List<E> items;
    
    public MySetIterator(MySet<E> theSet) {
        this.items = new ArrayList(theSet);
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < items.size();
    }

    @Override
    public E next() {
        if (hasNext()) {
            E ret = items.get(index);
            index += 1;
            return ret;
        }
        throw new NoSuchElementException();
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
