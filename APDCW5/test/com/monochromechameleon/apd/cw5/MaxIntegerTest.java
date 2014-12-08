package com.monochromechameleon.apd.cw5;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaxIntegerTest {
    
    /**
     * Test of join method, of class MaxInteger.
     */
    @Test
    public void testMaxInteger() {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);

        Integer result = Fold.fold(ints, 0, new MaxInteger());
        Integer expected = 5;
        
        assertEquals(expected, result);
    }
    
}
