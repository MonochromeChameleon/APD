package com.monochromechameleon.apd.cw2;

/**
 * Enum value defining the available operations for our command-line testing
 * and the evaluations of them.
 */
public enum Operation {

    DEGREE,
    COEFF,
    ADD,
    SUB,
    MULT,
    MINUS,
    EQUALS,
    TOSTRING;

    public String expression(Poly thisPoly, Poly thatPoly) {
        return thisPoly.toString() + " " + name() + " " + thatPoly.toString();
    }

    public String result(Poly thisPoly, Poly thatPoly) {
        switch (this) {
            case DEGREE:
                return String.valueOf(thisPoly.degree());
            case COEFF:
                return String.valueOf(thisPoly.coeff(thatPoly.coeff(0)));
            case ADD:
                return thisPoly.add(thatPoly).toString();
            case SUB:
                return thisPoly.sub(thatPoly).toString();
            case MULT:
                return thisPoly.mult(thatPoly).toString();
            case MINUS:
                return thisPoly.minus().toString();
            case EQUALS:
                return String.valueOf(thisPoly.equals(thatPoly));
            default:
                return thisPoly.toString();
        }
    }
}
