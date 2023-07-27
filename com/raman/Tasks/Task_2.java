//Task 2 -> Word Counter.

package com.raman.com.raman.Tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //Main Menu
        System.out.println(">>>>>> Word Counter <<<<<<");
        System.out.println("***** Main menu *****");
        System.out.println("Read from : ");
        System.out.println("1. Text input \n2. Text File \n3. Exit");

        //storing in the String
        String word = "";
        while (true) {
            int choice = in.nextInt();
            in.nextLine();
            if (choice == 1) {
                word = textInput(in);
                break;
            } else if (choice == 2) {
                word = fileInput();
                break;
            } else if (choice == 3) {
                System.out.println("Exiting..");
                break;
            } else {
                System.out.println("Wrong Selection..");
            }
        }

        String[] splitWords = word.split(" ");
        //Display count;
        countWords(splitWords, in);
    }

    private static void countWords(String[] splitWords, Scanner in) {
        int count = 0;
        Map<String, Integer> map = new HashMap<>();

        for (String str : splitWords) {
            if (!str.isBlank() && str.length() > 1) {
                str = str.toLowerCase().trim();
                if (map.containsKey(str)) {
                    map.put(str, map.get(str) + 1);
                } else {
                    map.put(str, 1);
                }
                count++;
            }
        }


        //Stats Display
        System.out.println("Count of Words : " + count);
        System.out.println("Count of Unique Words : " + map.size());


        System.out.println("Select option : \n1. Reveal Words : Occurrence \n2.Exit");
        int choice = in.nextInt();
        if (choice == 1) {
            System.out.println(map.toString());
        } else {
            return;
        }

    }

    private static String textInput(Scanner in) {
        System.out.print("Enter a text : ");
        return in.nextLine();
    }

    private static String fileInput() {
        return "sdfasg";
    }
}
