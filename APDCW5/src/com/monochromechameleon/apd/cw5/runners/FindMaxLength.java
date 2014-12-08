package com.monochromechameleon.apd.cw5.runners;

import com.monochromechameleon.apd.cw5.Fold;
import com.monochromechameleon.apd.cw5.Joiner;
import com.monochromechameleon.apd.cw5.MaxLength;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FindMaxLength {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> text = new ArrayList<>();
        System.out.println("Enter some text:");
        String line = in.nextLine();
        String[] words = line.split(" ");
        text.addAll(Arrays.asList(words));
        
        Joiner j = new MaxLength();
        
        Integer maxLength = Fold.fold(text, 0, j);

        System.out.println("The length of the longest word supplied is:\n");
        System.out.println("  " + maxLength);
    }
}
