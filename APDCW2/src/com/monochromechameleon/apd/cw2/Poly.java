package com.monochromechameleon.apd.cw2;

/**
 * Main class to represent a sparsely-populated polynomial.
 */
public class Poly {

    /**
     * Internal class defining a single term in a polynomial.
     */
    private static class Pair {

        private final int coefficient;
        private final int exponent;

        public Pair(int coeff, int exp) {
            this.coefficient = coeff;
            // Polynomials are mathematically defined as having positive integer
            // exponents, so we enforce that upon creation.
            this.exponent = Math.abs(exp);
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Pair)) {
                return false;
            }
            Pair that = (Pair) other;
            return this.coefficient == that.coefficient &&
                    this.exponent == that.exponent;
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

    // Internal structure
    private final Pair[] pairs;

    //<editor-fold desc="Constructors">

    /**
     * Empty constructor - creates a polynomial equal to zero
     */
    public Poly() {
        this.pairs = new Pair[]{};
    }

    /**
     * Simple constructor - creates a one-term polynomial equal to cx^e
     * @param c the coefficient of the polynomial term
     * @param e the exponent of the polynomial term
     */
    public Poly(int c, int e) {
        if (c == 0) {
            // Don't create a pair equal to zero.
            this.pairs = new Pair[]{};
            return;
        }
        Pair p = new Pair(c, e);
        this.pairs = new Pair[]{p};
    }

    /**
     * Private utility constructor to create a polynomial with more than one term.
     *
     * @param pairs the array of Pairs corresponding to the terms in the polynomial
     */
    private Poly(Pair[] pairs) {
        this.pairs = pairs;
    }

    //</editor-fold>

    //<editor-fold desc="Utility functions">

    /**
     * Utility function for creating a Poly from an array of strings.
     *
     * @param args the input array - assumed to all be integers within the target range
     * @param startIndex the index of the first item of interest in the array
     * @param endIndex the index of the first item not of interest in the array
     * @return a polynomial corresponding to the sum of the pairs of input integers
     */
    public static Poly parse(String[] args, int startIndex, int endIndex) {
        Poly p = new Poly();
        for (int ix = startIndex; ix < endIndex; ix += 2) {
            int arg0 = Integer.parseInt(args[ix]);
            int arg1 = ix < endIndex - 1 ? Integer.parseInt(args[ix + 1]) : 0;

            p = p.add(new Poly(arg0, arg1));
        }
        return p;
    }

    /**
     * Utility function for appending a Pair to an array of Pairs.
     *
     * @param pairs the original array
     * @param newPair the additional Pair to append
     * @return the array that is the conjunct of pairs and newPair
     */
    private static Pair[] append(Pair[] pairs, Pair newPair) {
        Pair[] ret = new Pair[pairs.length + 1];
        System.arraycopy(pairs, 0, ret, 0, pairs.length);
        ret[pairs.length] = newPair;
        return ret;
    }

    //</editor-fold>

    //<editor-fold desc="Functional Specification">

    /**
     * @return the highest exponent in the array of Pairs.
     */
    public int degree() {
        if (pairs.length == 0) {
            return 0;
        }
        Integer maxDegree = pairs[0].exponent;
        for (Pair p : pairs) {
            // Defend against zero coefficients
            if (p.coefficient != 0) {
                maxDegree = Math.max(maxDegree, p.exponent);
            }
        }
        return maxDegree;
    }

    /**
     * Finds the coefficient of the term in the polynomial for the given degree.
     *
     * @param d the degree for which we want to find the coefficient
     * @return the coefficient of the term of that degree
     */
    public int coeff(int d) {
        // Because this is a sparse array, we don't know at what index the right
        // pair will appear, so we have to iterate.
        for (Pair p : pairs) {
            if (p.exponent == d) {
                return p.coefficient;
            }
        }

        return 0;
    }

    /**
     * Adds a polynomial to this.
     *
     * @param poly the Poly to be added
     * @return a new Poly that is the sum of this and poly
     */
    public Poly add(Poly poly) {

        Pair[] addPairs = {};

        for (Pair p : pairs) {
            int sumCoeff = this.coeff(p.exponent) + poly.coeff(p.exponent);
            if (sumCoeff != 0) {
                addPairs = append(addPairs, new Pair(sumCoeff, p.exponent));
            }
        }

        // Add on all the pairs in poly which do not have a counterpart of the same
        // exponent in this.
        for (Pair p : poly.pairs) {
            if (this.coeff(p.exponent) == 0) {
                addPairs = append(addPairs, new Pair(p.coefficient, p.exponent));
            }
        }

        return new Poly(addPairs);
    }

    /**
     * Subtracts a polynomial from this.
     *
     * @param poly the Poly to be subtracted
     * @return a new Poly that is this minus poly
     */
    public Poly sub(Poly poly) {
        return this.add(poly.minus());
    }

    /**
     * Multiplies this by another polynomial.
     *
     * @param poly the Poly by which this should be multiplied
     * @return a new Poly that is the product of this and poly
     */
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

    /**
     * @return a new polynomial that is this multiplied by -1.
     */
    public Poly minus() {
        return this.mult(new Poly(-1, 0));
    }

    //</editor-fold>

    //<editor-fold desc="Object overrides">

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Poly)) {
            return false;
        }

        Poly that = (Poly) other;

        // We need to compare all pairs in this, as well as all pairs in that, to
        // ensure that one set of pairs is not a subset of the other.
        for (Pair p : this.pairs) {
            if (that.coeff(p.exponent) != p.coefficient) {
                return false;
            }
        }

        for (Pair p : that.pairs) {
            if (this.coeff(p.exponent) != p.coefficient) {
                return false;
            }
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

    /**
     * Utility function to handle signed toString operations.
     *
     * @param isFirstTerm whether or not we are at the start of the string
     * @return a string representation of the polynomial
     */
    private String innerToString(boolean isFirstTerm) {
        // Return zero for an empty polynomial
        if (pairs.length == 0) {
            return "0";
        }
        // If this is the first term in the polynomial we don't worry about sign.
        if (pairs.length == 1 && isFirstTerm) {
            return pairs[0].toString();
        }

        int deg = this.degree();
        int coeff = this.coeff(deg);

        // If this is not the first term, the sign will already have been taken
        // care of, so we should use the absolute value of the coefficient.
        if (pairs.length == 1) {
            return (new Pair(Math.abs(coeff), deg).toString());
        }

        // Split the polynomial into the first term and the remainder.
        Poly firstTerm = new Poly(this.coeff(deg), deg);
        Poly rest = this.add(firstTerm.minus());
        if (rest.equals(new Poly())) {
            // We have reached the end.
            return firstTerm.innerToString(false);
        }
        // There are more terms in the remainder, so we need to determine whether
        // the coefficient of the next term is positive or negative.
        boolean restIsPositive = rest.coeff(rest.degree()) > 0;

        // Join the first term to the rest with an appropriate conjunction.
        return String.format(
                "%s %s %s",
                firstTerm.innerToString(isFirstTerm),
                restIsPositive ? "+" : "-",
                rest.innerToString(false)
        );
    }

    @Override
    public String toString() {
        return this.innerToString(true);
    }

    //</editor-fold>
}
