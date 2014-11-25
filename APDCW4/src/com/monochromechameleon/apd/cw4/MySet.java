package com.monochromechameleon.apd.cw4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MySet<E> {

    private static class MySetIterator<E> implements Iterator<E> {

        protected int index;
        protected final List<E> items;

        public MySetIterator(List<E> theItems) {
            this.items = theItems;
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

    private final ArrayList<E> els;

    public MySet() {
        els = new ArrayList<>();
    }

    public Iterator<E> iterator() {
        return new MySetIterator(els);
    }

    public void insert(E el) {
        if (getIndex(el) < 0) {
            els.add(el);
        }
    }

    public void remove(E el) {
        int i = getIndex(el);
        if (i < 0) {
            return;
        }
        els.set(i, els.get(els.size() - 1));
        els.remove(els.size() - 1);
    }

    public boolean isIn(E el) {
        return getIndex(el) >= 0;
    }

    private int getIndex(E el) {
        for (int i = 0; i < els.size(); i++) {
            if (el.equals(els.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return els.size();
    }

    @Override
    public String toString() {
        if (els.isEmpty()) {
            return "{}";
        } else {
            String str = "{" + els.get(0);
            for (int i = 1; i < els.size(); i++) {
                str += "," + els.get(i);
            }
            return str + "}";
        }
    }
}
