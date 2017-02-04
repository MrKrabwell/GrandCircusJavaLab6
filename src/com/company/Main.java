package com.company;

import sun.management.snmp.jvmmib.EnumJvmMemoryGCCall;

import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.Collectors;


public class Main {

    /*************************************
     * This method get's the user string *
     *************************************/
    public static String getUserString() {
        // Variable declarations
        Scanner scnr = new Scanner(System.in);       // Scanner object for getting user input
        String str = "";                             // String to hold input

        // Prompt user and get string, until it is a valid input
        while (str == null || str.isEmpty() || !str.matches(".*\\w.*")) {
            System.out.print("Enter a line to be translated: ");
            str = scnr.nextLine();
        }

        return str;
    }


    /****************************************************
     * This method gets the Pig Latin for a single word *
     ****************************************************/
    public static String getPigLatinWord(String englishWord) {
        // determines whether first letter is capital letter or not.
        boolean firstLetterCapital = (englishWord.charAt(0) != englishWord.toLowerCase().charAt(0));

        // determines whether entire word is capital letter or not.
        // TODO: make it so it can take all capital letter as an input.
        boolean entireWordCapital = (englishWord.equals(englishWord.toLowerCase()));

        // determines whether the word is an contraction
        boolean isContraction = englishWord.matches("^[a-zA-Z]+('[a-zA-Z]+)?$");

        // if word contains a number or special character, but not a contraction:
        if (englishWord.matches(".*\\d+.*") || (englishWord.matches(".*\\W+.*") && !isContraction)) {
            // just return the same word, no need to translate.
            return englishWord;
        }

        // if vowel is first letter:
        else if ( englishWord.charAt(0) == 'a' ||
                  englishWord.charAt(0) == 'e' ||
                  englishWord.charAt(0) == 'i' ||
                  englishWord.charAt(0) == 'o' ||
                  englishWord.charAt(0) == 'u' ||
                  englishWord.charAt(0) == 'A' ||
                  englishWord.charAt(0) == 'E' ||
                  englishWord.charAt(0) == 'I' ||
                  englishWord.charAt(0) == 'O' ||
                  englishWord.charAt(0) == 'U') {

            // If first letter is capital, make sure to keep the capitalization
            // else, just add "way" to the end of the word
            if (firstLetterCapital) {
                return (englishWord.toUpperCase().charAt(0) + englishWord.substring(1) + "way");
            }
            else {
                return (englishWord + "way");
            }
        }

        // else, consonant is first letter
        else {
            // Loop through the world to see when the first vowel is
            for (int i = 0; i < englishWord.length(); i++) {
                    // if at index i, it is a vowel...
                    if (     englishWord.charAt(i) == 'a' ||
                             englishWord.charAt(i) == 'e' ||
                             englishWord.charAt(i) == 'i' ||
                             englishWord.charAt(i) == 'o' ||
                             englishWord.charAt(i) == 'u' ||
                             englishWord.charAt(i) == 'A' ||
                             englishWord.charAt(i) == 'E' ||
                             englishWord.charAt(i) == 'I' ||
                             englishWord.charAt(i) == 'O' ||
                             englishWord.charAt(i) == 'U') {
                        // if first letter is capital, make sure to keep the capitalization,
                        // else, move all consonants that appear before the first vowel to the end of the word
                        // then add "ay" to the end of the word.
                        if (firstLetterCapital) {
                            return (englishWord.toUpperCase().charAt(i) + englishWord.substring(i+1).toLowerCase() +
                                    englishWord.substring(0,i).toLowerCase() + "ay");
                        }
                        else {
                            return (englishWord.substring(i) + englishWord.substring(0,i) + "ay");
                        }
                }
            }
        }

        // If it gets here, then there are no vowels in the word.
        // If first letter is capital, make sure to keep the capitalization,
        // else, just add "ay" to the end.
        if (firstLetterCapital) {
            return englishWord.toUpperCase().charAt(0) + englishWord.substring(1) + "ay";
        }
        else {
            return englishWord + "ay";
        }
    }


    /**************************************************
     * This method translates the string to pig Latin *
     **************************************************/
    public static String getPigLatinString(String strEnglish) {
        // Variable declarations
        String pigLatinString = "";                            // String object for Pig Latin

        // Separate each word and put into an array
        String[] strArray = strEnglish.trim().split("\\s+");

        // Iterate through each word to translate into Pig Latin
        for (int i = 0; i < strArray.length; i++) {
            strArray[i] = getPigLatinWord(strArray[i]);
        }

        // Build back the string, note this feature is only available from Java 8
        pigLatinString = Arrays.stream(strArray).collect(Collectors.joining(" "));

        // Capitalize the first letter of the sentence.  Don't capitalize if single word.
        if (strArray.length != 1) {
            pigLatinString = pigLatinString.substring(0,1).toUpperCase() + pigLatinString.substring(1);
        }

        // Return the converted string
        return pigLatinString;
    }


    /*************************************************************************
     * This method asks the user if they want to continue the program or not *
     *************************************************************************/
    public static boolean askUserToContinue() {
        // Variable declarations
        String userInput = "";                  // User input string
        Scanner scan = new Scanner(System.in);  // Scanner object for user input

        // Prompt user to enter yes or no
        System.out.print("Do you want to continue? (y/n): ");
        userInput = scan.nextLine();
        System.out.println("");

        // Validate whether user input is ok, and continue asking until right
        while (!userInput.equalsIgnoreCase("y") &&
                !userInput.equalsIgnoreCase("yes") &&
                !userInput.equalsIgnoreCase("n") &&
                !userInput.equalsIgnoreCase("no")) {
            System.out.println("That is not a valid input.  Please try again. ");
            System.out.print("Do you want to continue? (y/n): ");
            userInput = scan.nextLine();
            System.out.println("");
        }

        // Return true if user says yes, and false if user says no
        if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
            return true;
        } else {
            return false;
        }
    }


    /****************************
     * This is the main program *
     ****************************/
    public static void main(String[] args) {
	    // Variable declarations
        boolean continueProgram = true;      // boolean to keep program running
        String userString = "";              // user's string
        String pigLatinString = "";          // Pig Latin string

        // Welcome message
        System.out.println("Welcome to the Pig Latin Translator!\n");

        // Continue the program until the user decides to quit.
        while (continueProgram) {
            // Get the string from the user
            userString = getUserString();

            // get the pig latin
            pigLatinString = getPigLatinString(userString);
            System.out.println("");

            // Print the pig latin string
            System.out.println(pigLatinString + "\n");

            // Ask the user if they want to continue
            continueProgram = askUserToContinue();
        }

        // Exit message
        System.out.println("Goodbye!\n");

    }
}
