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
        //TODO fill me
        return false;
    }

    public String grid() {
        //TODO fill me
        return null;
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
        //TODO fill me
    }
}
