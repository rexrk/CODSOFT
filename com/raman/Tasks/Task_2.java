//Task 2 -> Word Counter.

package com.raman.com.raman.Tasks;

import java.io.FileReader;
import java.io.IOException;
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
        System.out.println("1. Text input \n2. File input \n3. Exit");

        //storing in the String
        String word = "";
        while (true) {
            int choice = in.nextInt();
            in.nextLine(); // To fix error while getting input as string next time.
            if (choice == 1) {
                word = textInput(in);
                break;
            } else if (choice == 2) {
                word = fileInput(in);
                break;
            } else if (choice == 3) {
                System.out.println("Exiting..");
                break;
            } else {
                System.out.println("Wrong Selection..");
            }
        }

        String[] splitWords = word.split("\\W+");
        //Display count;
        countWords(splitWords, in);
        in.close();
    }

    private static void countWords(String[] splitWords, Scanner in) {
        int count = 0;
        Map<String, Integer> map = new HashMap<>();

        for (String str : splitWords) {
            if (!str.isBlank()) {
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
        if (in.nextInt() == 1) {
            System.out.println(map.toString());
        }

    }

    private static String textInput(Scanner in) {
        System.out.print("Enter a text : ");
        return in.nextLine();
    }

    private static String fileInput(Scanner in)  {
        System.out.print("Please provide the file path : ");
        String filePath = in.nextLine();
        StringBuilder text = new StringBuilder();
        //Reading from file
        try (Scanner reader = new Scanner(new FileReader(filePath))){
            while (reader.hasNextLine()) {
                text.append(reader.nextLine());
            }

        } catch (IOException e) {
            System.out.println("File error !!");
            e.printStackTrace();
        }

            return text.toString();
    }
}
