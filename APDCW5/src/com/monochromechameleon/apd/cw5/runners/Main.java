package com.monochromechameleon.apd.cw5.runners;

import com.monochromechameleon.apd.cw5.AddIntegers;
import com.monochromechameleon.apd.cw5.Fold;
import com.monochromechameleon.apd.cw5.Joiner;
import com.monochromechameleon.apd.cw5.MaxInteger;
import com.monochromechameleon.apd.cw5.MaxLength;
import com.monochromechameleon.apd.cw5.OrderedLists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        chooseTask(in);
    }
    
    private static void chooseTask(Scanner in) {
        System.out.println("Select a task:");
        System.out.println("  1: Add integers in a list");
        System.out.println("  2: Find the largest integer in a list");
        System.out.println("  3: Sort integers in a list");
        System.out.println("  4: Find the length of the longest word");
        
        String line = in.nextLine();
        
        String promptString = "Enter numbers:";
        String resultString;
        Joiner join;
        
        switch(line) {
            case "1":
                resultString = "The sum of the list is";
                join = new AddIntegers();
                break;
            case "2":
                resultString = "The largest value in the list is";
                join = new MaxInteger();
                break;
            case "3":
                resultString = "The sorted list is";
                join = new OrderedLists();
                break;
            case "4":
                promptString = "Enter text";
                resultString = "The length of the longest word supplied is";
                join = new MaxLength();
                break;
            default:
                System.out.println("Invalid choice - type 1, 2, 3 or 4");
                chooseTask(in);
                return;
        }
        
        List<String> input = readWords(promptString, in);
        System.out.println(resultString);
        
        switch (line) {
            case "1":
            case "2":
                System.out.println("  " + Fold.fold(mapIntegers(input), 0, join));
                break;
            case "3":
                System.out.println("  " + Fold.fold(mapIntegers(input), new ArrayList<Integer>(), join));
                break;
            case "4":
                System.out.println("  " + Fold.fold(input, 0, join));
        }
    }
    
    private static List<String> readWords(String prompt, Scanner in) {
        System.out.println(prompt);
        String line = in.nextLine();
        return Arrays.asList(line.split(" "));
    }
    
    private static List<Integer> mapIntegers(List<String> strings) {
        List<Integer> out = new ArrayList<>();
        for (String str : strings) {
            out.add(Integer.parseInt(str));
        }
        return out;
    }
}
