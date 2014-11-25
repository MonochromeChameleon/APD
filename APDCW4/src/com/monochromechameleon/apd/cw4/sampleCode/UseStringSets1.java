package com.monochromechameleon.apd.cw4.sampleCode;

import com.monochromechameleon.apd.cw4.MySet;
import java.util.Scanner;

class UseStringSets1 {
// String set maintenance program

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        MySet<String> set = new MySet<>();
        System.out.print(": ");
        String line = in.nextLine();
        while (line.charAt(0) != 'q') {
            String[] words;
            switch (line.charAt(0)) {
                case 's':
                    System.out.println(set.size());
                    break;
                case 'p':
                    System.out.println(set);
                    break;
                case 'i':
                    words = line.split(" ");
                    for (int i = 1; i < words.length; i++) {
                        set.insert(words[i]);
                    }
                    break;
                case 'r':
                    words = line.split(" ");
                    for (int i = 1; i < words.length; i++) {
                        set.remove(words[i]);
                    }
                    break;
                case 'm':
                    words = line.split(" ");
                    for (int i = 1; i < words.length; i++) {
                        System.out.println(set.isIn(words[i]));
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
    }
}
