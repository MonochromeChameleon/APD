package com.monochromechameleon.apd.cw5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderedLists implements Joiner<Integer, List<Integer>> {

    @Override
    public List<Integer> join(Integer f, List<Integer> b) {
        List<Integer> out = new ArrayList<>();
        Iterator<Integer> it = b.iterator();
        boolean fAdded = false;
        while (it.hasNext()) {
            int next = it.next();
            if (next < f || fAdded) {
                out.add(next);
            }
            if (next >= f && !fAdded) {
                out.add(f);
                fAdded = true;
                out.add(next);
            }
        }
        
        if (!fAdded) {
            out.add(f);
        }
        
        return out;
    }
}
