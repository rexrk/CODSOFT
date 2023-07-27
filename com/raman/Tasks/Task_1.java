//Task 1 - > Guess the Number.

package com.raman.com.raman.Tasks;

import java.util.Random;
import java.util.Scanner;

public class Task_1 {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner in = new Scanner(System.in);

        // Main Interface
        byte option;
        do {
            System.out.println("<<<<<<<< GUESS THE NUMBER GAME >>>>>>>>");
            System.out.println("----- Main menu ----- \n");

            System.out.println("    1. User Picks a Number !");
            System.out.println("    2. Computer picks a Number !");
            System.out.println("    3. How to Play ?");
            System.out.println("    4. Quit !");

            System.out.print("Select an option : ");
            option = in.nextByte();

            switch (option) {
                case 1:
                    userPlays(random, in, 1, 0);
                    break;
                case 2:
                    computerPlays(in, random);
                    break;
                case 3:
                    instruction(in);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Wrong option !!! Try Again..");
            }

        } while (option != 4);

    }

    private static void userPlays(Random random, Scanner in, int round, int roundWins) {
        System.out.println("Select a Category : ");
        System.out.println("1. Hard \n2. Easy \n3. Default");
        int category = in.nextInt();

        // Random number Generator
        int generatedNumber = random.nextInt(100);
        if (category == 1) {
            generatedNumber = random.nextInt(1000);
        } else if (category == 2) {
            generatedNumber = random.nextInt(50);
        } else {
            System.out.println("Default Selected...");
        }
        System.out.println("Computer is Thinking A Number ...");

        // Gameplay
        int movesLeft = 10;
        System.out.print("Round : <" + round + ">     Attempts Left : <" + (movesLeft) + ">");
        while (movesLeft >= 0) {
            // If moves over
            if (movesLeft == 0) {
                System.out.println("Uh Oh! You Failed to guess the number.");
                break;
            }

            // Guess the number
            System.out.print("\nGuess The Number: ");
            int n = in.nextInt();

            if (generatedNumber == n) {
                System.out.println("Correct! The number is : " + n +
                        ". You guessed in " + (11 - movesLeft) + " attempts. ");
                roundWins++;
                break;
            } else {
                // Display the feedback
                System.out.println(logicGame(generatedNumber, n));
                movesLeft--;
            }

        }
        System.out.println("Do you Want to Play Again ? \n1. Confirm \n2. Quit");
        if (in.nextInt() == 1) {
            userPlays(random, in, round + 1, roundWins);
        } else {

            System.out.println((char) 3 + "No. of Rounds won: " + roundWins);
            return;
        }
    }

    private static String logicGame(int genNum, int userNum) {
        if (userNum > genNum) {
            if (userNum - genNum < 5) {
                return "Too Close ! Choose a lesser number.";
            } else {
                return "Number is too High.";
            }
        } else {
            if (genNum - userNum < 5) {
                return "Too Close ! Choose a higher number.";

            } else {
                return "Number is too low.";
            }
        }
    }

    private static void computerPlays(Scanner in, Random random) {
        System.out.println("Think a Number between 1 - 100.");
        System.out.print("Press 1 to start: ");
        int n = in.nextInt();
        int selection = 0;
        int s = 1;
        int e = 100;
        int m;
        while (selection != 1 || s <= e) {

            if (s == 1 && e == 100) {
                m = random.nextInt(100);
            } else {
                m = s + (e - s) / 2;

            }

            System.out.println("\n>>>>> Is this the number: " + m + " <<<<<");
            System.out.println("1. Yes this is Correct.");
            System.out.println("2. No, Try Lower.");
            System.out.println("3. No, Go Higher.");
            int userInp = in.nextInt();

            if (userInp == 1) {
                System.out.println("  YaYY !! The number was: " + m);
                break;

            } else if (userInp == 2) {
                e = m - 1;

            } else {
                s = m + 1;
            }

        }
        System.out.println("       xoxo___xoxo \n");

    }

    public static void instruction(Scanner in) {
        System.out.println("\nHOW TO PLAY ???");
        System.out.println((char) 4 + " The [ Computer/User ] will pick a secret number.");
        System.out.println((char) 4 + " [ You/Computer ] guess what number it is.");
        System.out.println((char) 4 + " If your guess is too high or too low, It will give you a hint.");
        System.out.println((char) 4 + " See how many turns it takes you to win ! \n");

        System.out.print("Press 1 to go back to Main Menu : ");
        String fake = in.next();
        System.out.println();
        return;

    }

}
