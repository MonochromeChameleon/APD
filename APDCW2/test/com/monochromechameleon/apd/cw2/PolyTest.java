package com.monochromechameleon.apd.cw2;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

public class PolyTest {

    @Test
    public void testEmptyConstructor() {
        Poly p = new Poly();
        assertEquals(0, p.degree());
    }

    @Test
    public void testDegree() {
        Poly p = new Poly(2, 4);
        assertEquals(4, p.degree());
    }
    
    @Test
    public void testComplexDegree() {
        Poly p = (new Poly(2, 2)).add(new Poly(3, 1)).add(new Poly(6, 0));
        assertEquals(2, p.degree());
    }
    
    @Test
    public void testNegativeDegree() {
        Poly p = new Poly(3, -4);
        assertEquals(4, p.degree());
    }

    @Test
    public void testCoeff() {
        Poly p = new Poly(2, 4);
        assertEquals(2, p.coeff(4));
    }
    
    @Test
    public void testZeroCoeff() {
        Poly p = new Poly(2, 4);
        assertEquals(0, p.coeff(3));
    }

    @Test
    public void testAddSameDegree() {
        Poly p1 = new Poly(2, 4);
        Poly p2 = new Poly(3, 4);

        Poly pAdd = p1.add(p2);

        assertNotSame(p1, pAdd);
        assertNotSame(p2, pAdd);
        assertEquals(5, pAdd.coeff(4));
    }

    @Test
    public void testAddDifferentDegree() {
        Poly p1 = new Poly(2, 4);
        Poly p2 = new Poly(3, 3);

        Poly pAdd = p1.add(p2);

        assertNotSame(p1, pAdd);
        assertNotSame(p2, pAdd);
        assertEquals(2, pAdd.coeff(4));
        assertEquals(3, pAdd.coeff(3));
    }
    
    @Test
    public void testSubSameDegree() {
        Poly p1 = new Poly(2, 4);
        Poly p2 = new Poly(3, 4);

        Poly pSub = p1.sub(p2);

        assertNotSame(p1, pSub);
        assertNotSame(p2, pSub);
        assertEquals(-1, pSub.coeff(4));
    }

    @Test
    public void testSubDifferentDegree() {
        Poly p1 = new Poly(2, 4);
        Poly p2 = new Poly(3, 3);

        Poly pSub = p1.sub(p2);

        assertNotSame(p1, pSub);
        assertNotSame(p2, pSub);
        assertEquals(2, pSub.coeff(4));
        assertEquals(-3, pSub.coeff(3));
    }

    @Test
    public void testSimpleMult() {
        Poly p1 = new Poly(2, 4);
        Poly p2 = new Poly(3, 3);

        Poly pMult = p1.mult(p2);

        assertNotSame(p1, pMult);
        assertNotSame(p2, pMult);
        assertEquals(0, pMult.coeff(4));
        assertEquals(0, pMult.coeff(3));
        assertEquals(6, pMult.coeff(7));
    }

    @Test
    public void testComplexMult() {
        Poly p1 = (new Poly(2, 2)).add(new Poly(3, 1)).add(new Poly(6, 0));
        Poly p2 = (new Poly(3, 3)).add(new Poly(2, 1)).add(new Poly(3, 0));

        Poly pMult = p1.mult(p2);
        assertNotSame(p1, pMult);
        assertNotSame(p2, pMult);

        assertEquals(6, pMult.coeff(5));
        assertEquals(9, pMult.coeff(4));
        assertEquals(22, pMult.coeff(3));
        assertEquals(12, pMult.coeff(2));
        assertEquals(21, pMult.coeff(1));
        assertEquals(18, pMult.coeff(0));
    }

    @Test
    public void testMinus() {
        Poly p1 = (new Poly(2, 2)).add(new Poly(3, 1)).add(new Poly(6, 0));

        Poly pMinus = p1.minus();

        assertNotSame(p1, pMinus);
        assertEquals(-2, pMinus.coeff(2));
        assertEquals(-3, pMinus.coeff(1));
        assertEquals(-6, pMinus.coeff(0));
    }

    @Test
    public void testEquals() {
        Poly p1 = (new Poly(2, 2)).add(new Poly(3, 1)).add(new Poly(6, 0));
        Poly p2 = (new Poly(2, 2)).add(new Poly(3, 1)).add(new Poly(6, 0));

        assertTrue(p1.equals(p2));
        assertNotSame(p1, p2);
    }
    
    @Test
    public void testHashCode() {
        Poly p1 = (new Poly(2, 2)).add(new Poly(3, 1)).add(new Poly(6, 0));
        Poly p2 = (new Poly(2, 2)).add(new Poly(3, 1)).add(new Poly(6, 0));

        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotSame(p1, p2);
    }
    
    @Test
    public void testToString() {
        Poly p1 = (new Poly(2, 2)).add(new Poly(3, 1)).add(new Poly(6, 0));
        Poly p2 = (new Poly(2, 1)).add(new Poly(3, 0)).add(new Poly(6, -1));
        Poly p3 = (new Poly(2, 2)).add(new Poly(-3, 1)).add(new Poly(6, 0));
        Poly p4 = (new Poly(0, 5));
        Poly p5 = new Poly(-3, 3);
        
        assertEquals("2x^2 + 3x + 6", p1.toString());
        assertEquals("8x + 3", p2.toString());
        assertEquals("2x^2 - 3x + 6", p3.toString());
        assertEquals("", p4.toString());
        assertEquals("-3x^3", p5.toString());
    }
    
    @Test
    public void testParse() {
        String[] args = { "1", "2", "3", "4", "5", "6", "7" };
        Poly p12 = Poly.parse(args, 0, 2);
        Poly p3 = Poly.parse(args, 2, 3);
        Poly p7 = Poly.parse(args, 0, args.length);
        
        assertEquals(p12, new Poly(1, 2));
        assertEquals(p3, new Poly(3, 0));
        assertEquals(7, p7.coeff(0));
        assertEquals(6, p7.degree());
    }
}
