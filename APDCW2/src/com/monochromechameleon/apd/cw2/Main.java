package com.monochromechameleon.apd.cw2;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // Assume that the input is a sequence of integers, optionally followed
        // by a string defining the operation to be performed and then a further
        // sequence of integers.
        // e.g. 2 1 3 mult 4 2 = 2x + 3 MULT 4x^2

        // Determine the index of the operation string in our input arguments, and
        // the operation to be performed.
        int operationIndex;
        Operation operation = Operation.TOSTRING;
        for (operationIndex = 0; operationIndex < args.length; operationIndex += 1) {
            if (!isLikelyNumeric(args[operationIndex])) {
                operation = Operation.valueOf(args[operationIndex].toUpperCase());
                break;
            }
        }

        // p1 is the polynomial for everything before our operation
        Poly p1 = Poly.parse(args, 0, operationIndex);
        // p2 is the polynomial for everything after our operation
        Poly p2 = Poly.parse(args, operationIndex + 1, args.length);

        String output = String.format(
                "%s = %s",
                operation.expression(p1, p2),
                operation.result(p1, p2)
        );

        System.out.println(output);
    }

    private static boolean isLikelyNumeric(String str) {
        // We can check for numeric values by looking at the last character of
        // the string: negative integers will still pass, while our string
        // operations would fail.
        return (Character.digit(str.charAt(str.length() - 1), 10) >= 0);
    }
}
