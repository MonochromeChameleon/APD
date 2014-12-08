package com.monochromechameleon.apd.cw5;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaxLengthTest {
    
    /**
     * Test of join method, of class MaxLength.
     */
    @Test
    public void testJoin() {
        List<String> words = Arrays.asList("Pour-over", "typewriter", "fingerstache", "cardigan", "High Life", "dreamcatcher", "gentrify", "paleo", "readymade");

        Integer result = Fold.fold(words, 0, new MaxLength());
        Integer expected = 12;
        
        assertEquals(expected, result);
    }
}
