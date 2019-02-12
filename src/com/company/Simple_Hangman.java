package com.company;

/* Initialize your array list with the following words: 
tree
rain
bear
encourage
promise
soup
chess
insurance
pancakes
stream
When you run the application, randomly choose a word from that list for the user to guess.
For each letter the user guesses wrong, let them know along with how many wrong guesses they have left.
The user is allowed up to 5 wrong guesses. On the 6th wrong guess, they lose the game.
If the user guesses correctly, output the word with the letters
they have guessed correctly so far and blanks for letters they have not guessed yet.
The user can input either a letter or a word for their guess.
If the user guesses the word correctly, output "You've won!" and end the game.
*/

import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;

public class Simple_Hangman {


    private static ArrayList<Character> generate_word(){

        ArrayList<Character> word = new ArrayList<Character>();

        Random rand_word = new Random();







        String[] dictionary = {"tree", "rain" , "bear", "encourage", "promise", "soup", "chess", "insurance", "pancakes", "stream"};

        int rand_int = rand_word.nextInt(dictionary.length);

        String word_string = dictionary[rand_int];

        char[] stringToCharArray = word_string.toCharArray();


        for (char value:stringToCharArray) {
            word.add(value);
        }

        return word;
    }

    private static boolean check_letter(ArrayList<Character> mystery_word, char guess_letter){
         int count = 0;
         for (char value: mystery_word){
             if (value == guess_letter)
                 count++;
         }
         return (count > 0);
    }

    private static boolean check_win(ArrayList<Character> mystery_word, ArrayList<Character> guess_word) {
        int count = 0;
        for (int i = 0; i < mystery_word.size(); i++) {
            if (guess_word.get(i) == mystery_word.get(i))
                count++;
        }

        return (count == mystery_word.size());

        }

    public static void main(String[] args) {
        ArrayList<Character> random_word = new ArrayList<Character>();
        ArrayList<Character> guess_word = new ArrayList<Character>();



        System.out.println("Welcome, let's play hangman!");
        random_word = generate_word();
        System.out.print("\nHere is the word I am thinking of: ");

        for (int i=0; i<random_word.size(); i++) {
            guess_word.add('_');
            System.out.print("_ ");
        }

        Scanner scanner = new Scanner(System.in);
        String formatted = "";
        String another = "n";
        String response = "";
        int wrong_count = 0;
        boolean check = false;

        char[] stringToCharArray = new char[1];

        do {
            System.out.print("\n\nEnter your guess: ");
            String guess = scanner.next();
            char guess_char = guess.toCharArray()[0];

            check = check_win(random_word, guess_word);

                if ((check_letter(random_word, guess_char)) && (wrong_count <5) && (!check)) {
                    System.out.println("Good job. There is/are a/an '" + guess_char + "' in the mystery word.");
                        for (int i=0; i < random_word.size(); i++) {
                            if (guess_char == random_word.get(i))
                            guess_word.set(i, guess_char);
                        }
                        formatted = guess_word.toString().replace("[", "").replace("]", "").replace(",", "");
                        System.out.println("What you've guessed so far: " + formatted);

                    System.out.println("\nDo you want to continue guessing? (y/n)");
                    another = scanner.nextLine();

                    }

                else if ( (wrong_count < 5) && !(check_win(random_word, guess_word)) ) {
                    System.out.println("Sorry. There is no '" + guess_char + "' in the mystery word.");
                    wrong_count += 1;
                    System.out.println("You have guessed incorrectly " + wrong_count + "/6 times.");
                    formatted = guess_word.toString().replace("[", "").replace("]", "").replace(",", "");
                    System.out.println("What you've guessed so far: " + formatted);
                    System.out.println("\nDo you want to continue guessing? (y/n)");
                    another = scanner.nextLine();
                }
                else if ( (wrong_count == 5) && !(check_win(random_word, guess_word)) ) {
                    System.out.println("Sorry. There is no '" + guess_char + "' in the mystery word.");
                    wrong_count += 1;
                    System.out.println("You have guessed incorrectly " + wrong_count + "/6 times.");
                    System.out.println("You have no more guesses left.  The word was ");
                    for (char value:random_word) {
                        System.out.print(value);
                    }
                    System.out.print(".");
                }

                if (check_win(random_word, guess_word)) {
                    System.out.println("You've won. The word was ");
                    for (char value:random_word) {
                        System.out.print(value);
                    }
                    System.out.print(".");
                }

        } while ((another.equals("y")) || (another.equals("Y")) || (another.equals("yes")) || (another.equals("YES")) || (another.equals("Yes")) ||
                (wrong_count <= 5) || (check));



/*        System.out.print("\n\nThe word was ");
        for (char value:random_word) {
            System.out.print(value);
        }
        System.out.print(".");
*/




    }
}