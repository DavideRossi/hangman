package it.unibo.ss.hangman;

import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class Game {
    private final String targetWord;
    private final int maxErrors;
    private int remainingErrors;
    private final Set<Character> guessedLetters;

    public Game(String targetWord, int maxErrors) {
        this.targetWord = targetWord.toLowerCase();
        this.maxErrors = maxErrors;
        this.remainingErrors = maxErrors;
        this.guessedLetters = new HashSet<>();
    }

    public boolean guess(char letter) {
        letter = Character.toLowerCase(letter);
        this.guessedLetters.add(letter);
        if (this.targetWord.indexOf(letter) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public String grid() {
        StringBuilder result = new StringBuilder();
        String vowels = "aeiou";
        for (char c : this.targetWord.toCharArray()) {
            if (this.guessedLetters.contains(c)) {
                result.append(Character.toUpperCase(c));
            } else if (vowels.indexOf(c) != -1) {
                result.append('+');
            } else if (Character.isAlphabetic(c)) {
                result.append('-');
            } else {
                result.append(c);
            }
            result.append(' ');
        }
        result.append("/ errors left: ").append(this.remainingErrors);
        return result.toString().trim();
    }

    public char readGuess(Scanner scanner) {
        Optional<Character> guess = Optional.empty();
        while(guess.isEmpty()) {
            System.out.print("Guess a letter: ");
            String input = scanner.nextLine().trim();
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Please enter a single letter.");
            } else {
                char inputChar = input.charAt(0);
                if (this.guessedLetters.contains(inputChar)) {
                    System.out.println("Already guessed that letter, try another");
                } else {
                    guess = Optional.of(inputChar);
                }
            }
        }
        return guess.get();
    }

    public void play(Scanner scanner) {
        while (this.remainingErrors > 0) {
            System.out.println(grid());
            char guess = readGuess(scanner);
            if(guess(guess)) {
                boolean allGuessed = true;
                for (char c : this.targetWord.toCharArray()) {
                    if (Character.isLetter(c) && !this.guessedLetters.contains(c)) {
                        allGuessed = false;
                        break;
                    }
                }
                if (allGuessed) {
                    System.out.println("Congratulations! You've guessed the word: " + this.targetWord.toUpperCase());
                    return;
                }
            } else {
                this.remainingErrors--;
                System.out.println("Wrong guess! You have " + this.remainingErrors + " errors left.");
            }
        }
        System.out.println("Game over! The word was: " + this.targetWord.toUpperCase());
    }
}
