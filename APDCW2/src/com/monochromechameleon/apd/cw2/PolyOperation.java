package com.monochromechameleon.apd.cw2;

/**
 * Utility class for parsing command-line input into two polynomials and an operation.
 */
public class PolyOperation {
    private Operation operation = Operation.TOSTRING;
    private final Poly p1;
    private final Poly p2;
    
    public PolyOperation(String[] args) {
        // Assume that the input is a sequence of integers, optionally followed
        // by a string defining the operation to be performed and then a further
        // sequence of integers.
        // e.g. 2 1 3 mult 4 2 = 2x + 3 MULT 4x^2

        // Determine the index of the operation string in our input arguments.
        int operationIndex;
        for (operationIndex = 0; operationIndex < args.length; operationIndex += 1) {
            if (!isLikelyNumeric(args[operationIndex])) {
                operation = Operation.valueOf(args[operationIndex].toUpperCase());
                break;
            }
        }

        // p1 is the polynomial for everything before our operation
        p1 = Poly.parse(args, 0, operationIndex);
        // p2 is the polynomial for everything after our operation
        p2 = Poly.parse(args, operationIndex + 1, args.length);
    }
    
    private static boolean isLikelyNumeric(String str) {
        // We can check for numeric values by looking at the last character of
        // the string: negative integers will still pass, while our string
        // operations would fail.
        return (Character.digit(str.charAt(str.length() - 1), 10) >= 0);
    }

    @Override
    public String toString() {
        return operation.expression(p1, p2) + " = " + operation.result(p1, p2);
    }
}
