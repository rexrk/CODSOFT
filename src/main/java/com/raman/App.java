package com.raman;

import java.io.Console;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner in = new Scanner(System.in);
        
        
        //Main Interface
        byte option;
        do {
            System.out.println("<<<<<<<< GUESS THE NUMBER >>>>>>>>");
            
            System.out.println("    1. User Picks a Number !");
            System.out.println("    2. Computer picks a Number !");
            System.out.println("    3. How to Play ?");
            System.out.println("    4. Quit !");
            
            System.out.print("Select an option : ");
            option = in.nextByte();

            switch(option) {
                case 1 : userPlays();
                case 2 : computerPlays();
                case 3 : instruction();
                case 4 : break;
            }

        } while (option != 4);

    }
    
    public static void userPlays() {

    }
    public static void computerPlays() {

    }

    public static void instruction() {
        System.out.println("\nHOW TO PLAY ???");
        System.out.println((char)4 + " The Computer will pick a secret number.");
        System.out.println((char)4 + " You guess what number it is.");
        System.out.println((char)4 + " If your guess is too high or too low, It will give you a hint.");
        System.out.println((char)4 + " See how many turns it takes you to win !");
        System.out.println();
    }



}
