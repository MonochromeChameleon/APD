package com.monochromechameleon.apd.cw4.sampleCode;

import com.monochromechameleon.apd.cw4.Filter;
import com.monochromechameleon.apd.cw4.MySet;
import com.monochromechameleon.apd.cw4.Odd;
import java.util.Iterator;
import java.util.Scanner;


class UseIntSets2 {
// Integer set maintenance program, with iterator test

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        MySet<Integer> set = new MySet<>();
        System.out.print(": ");
        String line = in.nextLine();
        while (line.charAt(0) != 'q') {
            String[] numbers;
            switch (line.charAt(0)) {
                case 's':
                    System.out.println(set.size());
                    break;
                case 'p':
                    System.out.println(set);
                    break;
                case 'i':
                    numbers = line.split(" ");
                    for (int i = 1; i < numbers.length; i++) {
                        set.insert(new Integer(numbers[i]));
                    }
                    break;
                case 'r':
                    numbers = line.split(" ");
                    for (int i = 1; i < numbers.length; i++) {
                        set.remove(new Integer(numbers[i]));
                    }
                    break;
                case 'm':
                    numbers = line.split(" ");
                    for (int i = 1; i < numbers.length; i++) {
                        System.out.println(set.isIn(new Integer(numbers[i])));
                    }
                    break;
                default:
                    System.out.print("i - insert, r - remove, m - member,");
                    System.out.println(" p - print, s - size, q - quit");
                    break;
            }
            System.out.print(": ");
            line = in.nextLine();
        }
        Iterator<Integer> it = new Filter<>(set.iterator(), new Odd());
        System.out.println("Odd set elements are:\n");
        while (it.hasNext()) {
            System.out.println("  " + it.next());
        }
    }
}
