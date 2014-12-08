package com.monochromechameleon.apd.cw5.runners;

import com.monochromechameleon.apd.cw5.AddIntegers;
import com.monochromechameleon.apd.cw5.Fold;
import com.monochromechameleon.apd.cw5.Joiner;
import java.util.ArrayList;
import java.util.Scanner;

public class DoAddIntegers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();
        System.out.println("Enter some numbers:");
        String line = in.nextLine();
        String[] words = line.split(" ");
        for (String word : words) {
            numbers.add(Integer.parseInt(word));
        }
        
        Joiner j = new AddIntegers();
        
        Integer total = Fold.fold(numbers, 0, j);

        System.out.println("The sum of the list is:\n");
        System.out.println("  " + total);
    }    
}
