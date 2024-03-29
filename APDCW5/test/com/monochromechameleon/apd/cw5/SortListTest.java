package com.monochromechameleon.apd.cw5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SortListTest {

    @Test
    public void testOrderedLists() {
        List<Integer> ints = Arrays.asList(5, 3, 4, 2, 4, 1);
        
        List<Integer> result = Fold.fold(ints, new ArrayList<Integer>(), new SortList());
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 4, 5);
        
        assertEquals(expected, result);
    }
}
