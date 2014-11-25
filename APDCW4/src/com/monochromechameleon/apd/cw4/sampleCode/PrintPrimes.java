package com.monochromechameleon.apd.cw4.sampleCode;

import java.util.Scanner;

class PrintPrimes {

    public static void main(String[] args) {
        Primes primes = new Primes();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number of prime numbers you wish to generate: ");
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println(primes.next());
        }
    }
}
