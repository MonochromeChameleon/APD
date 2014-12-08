package com.monochromechameleon.apd.cw5.runners;

import com.monochromechameleon.apd.cw5.Fold;
import com.monochromechameleon.apd.cw5.Joiner;
import com.monochromechameleon.apd.cw5.OrderedLists;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SortList {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();
        System.out.println("Enter some numbers:");
        String line = in.nextLine();
        String[] words = line.split(" ");
        for (String word : words) {
            numbers.add(Integer.parseInt(word));
        }
        
        Joiner j = new OrderedLists();
        
        List<Integer> ordered = Fold.fold(numbers, new ArrayList<Integer>(), j);

        System.out.println("The ordered list is:\n");
        System.out.println("  " + ordered);
    }    
}
