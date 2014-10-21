package com.monochromechameleon.apd.cw2;

public class Poly {
    
    private static class Pair {
        private final int coefficient;
        private final int exponent;
        
        public Pair(int coeff, int deg) {
            this.coefficient = coeff;
            this.exponent = deg;
        }
        
        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Pair)) return false;
            Pair that = (Pair)other;
            return this.coefficient == that.coefficient && this.exponent == that.exponent;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 97 * hash + this.coefficient;
            hash = 97 * hash + this.exponent;
            return hash;
        }
        
        @Override
        public String toString() {
            if (coefficient == 0) {
                return "";
            }
            if (exponent == 1) {
                return String.format("%dx", coefficient);
            }
            if (exponent == 0) {
                return String.valueOf(coefficient);
            }
            return String.format("%dx^%d", coefficient, exponent);
        }
    }
    
    private final Pair[] pairs;
    
    public Poly() {
        pairs = new Pair[] {};
    }
    
    public Poly(int m, int n) {
        Pair p = new Pair(m, n);
        pairs = new Pair[] { p };
    }
    
    private Poly(Pair[] ps) {
        // Utility constructor
        pairs = ps;
    }
    
    public int degree() {
        if (pairs.length == 0) {
            return 0;
        }
        Integer maxDegree = pairs[0].exponent;
        for (Pair p : pairs) {
            maxDegree = Math.max(maxDegree, p.exponent);
        }
        return maxDegree;
    }
    
    public int coeff(int d) {
        // Because this is a sparse array, we don't know at what index the right pair
        // will appear, so we have to iterate.
        for (Pair p : pairs) {
            if (p.exponent == d) {
                return p.coefficient;
            }
        }
        
        return 0;
    }
    
    private static Pair[] append(Pair[] pairs, Pair newPair) {
        Pair[] ret = new Pair[pairs.length + 1];
        System.arraycopy(pairs, 0, ret, 0, pairs.length);
        ret[pairs.length] = newPair;
        return ret;
    }
        
    public Poly add(Poly poly) {
        
        Pair[] addPairs = {};

        for (Pair p : pairs) {
            int sumCoeff = this.coeff(p.exponent) + poly.coeff(p.exponent);
            if (sumCoeff != 0) {
                addPairs = append(addPairs, new Pair(sumCoeff, p.exponent));
            }
        }
        for (Pair p : poly.pairs) {
            if (this.coeff(p.exponent) == 0) {
                addPairs = append(addPairs, new Pair(p.coefficient, p.exponent));
            }
        }
        
        return new Poly(addPairs);
    }
    
    public Poly sub(Poly poly) {
        return this.add(poly.minus());
    }
    
    public Poly mult(Poly poly) {
        
        Poly result = new Poly();
        
        for (Pair p1 : pairs) {
            for (Pair p2 : poly.pairs) {
                int newCoefficient = p1.coefficient * p2.coefficient;
                int newDegree = p1.exponent + p2.exponent;
                
                Poly component = new Poly(newCoefficient, newDegree);

                result = result.add(component);
            }
        }
        
        return result;
    }
    
    public Poly minus() {
        Poly ret = new Poly();
        for (Pair p : pairs) {
            ret = ret.add(new Poly(-p.coefficient, p.exponent));
        }
        return ret;
    }
    
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Poly)) {
            return false;
        }

        Poly that = (Poly)other;
        int thisDegree = this.degree();
        int thatDegree = that.degree();

        if (thisDegree != thatDegree) {
            return false;
        }
        
        // We need to compare all pairs in this, as well as all pairs in that, to ensure
        // that one set of pairs is not a subset of the other.
        for (Pair p : this.pairs) {
            if (that.coeff(p.exponent) != p.coefficient) return false;
        }
        
        for (Pair p : that.pairs) {
            if (this.coeff(p.exponent) != p.coefficient) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;        
        for (Pair p : pairs) {
            int pHash = p.hashCode();
            hash = 43 * hash + pHash;
        }
        return hash;
    }
    
    private String innerToString(boolean isFirstTerm) {
        if (pairs.length == 0) return "";
        if (pairs.length == 1 && isFirstTerm) return pairs[0].toString();
        
        int deg = this.degree();
        int coeff = this.coeff(deg);

        if (pairs.length == 1) return (new Pair(Math.abs(coeff), deg).toString());
        
        Poly firstTerm = new Poly(this.coeff(deg), deg);
        Poly rest = this.add(firstTerm.minus());
        if (rest.equals(new Poly())) {
            return firstTerm.innerToString(false);
        }
        boolean restIsPositive = rest.coeff(rest.degree()) > 0;
        return String.format("%s %s %s", firstTerm.innerToString(isFirstTerm), restIsPositive ? "+" : "-", rest.innerToString(false));
    }
    
    @Override
    public String toString() {
        return this.innerToString(true);
    }
}
