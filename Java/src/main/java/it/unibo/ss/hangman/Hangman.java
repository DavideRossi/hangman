package it.unibo.ss.hangman;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Hangman {
    private static final int MAX_ERRORS = 6;
    private final Dictionary dictionary;
    private final Scanner scanner;

    public Hangman() throws IOException {
        this.dictionary = new Dictionary("../words.txt");
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() throws IOException {
        while (true) {
            System.out.println("\n--- Hangman Main Menu ---");
            System.out.println("1. Show dictionary");
            System.out.println("2. Add word to dictionary");
            System.out.println("3. Remove word from dictionary");
            System.out.println("4. Start new game");
            System.out.println("5. Exit");
            System.out.print("Select an option (1-5): ");
            String choice = this.scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    showDictionaryPaginated();
                    break;
                case "2":
                    addWordToDictionary();
                    break;
                case "3":
                    removeWordFromDictionary();
                    break;
                case "4":
                    playGame();
                    break;
                case "5":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void playGame() {
        System.out.print("Enter the maximum number of errors allowed (default is " + MAX_ERRORS + "): ");
        String input = this.scanner.nextLine().trim();
        int maxErrors;
        try {
            maxErrors = Integer.parseInt(input);
            if (maxErrors <= 0) {
                System.out.println("Invalid number of errors. Setting to default (" + MAX_ERRORS + ").");
                maxErrors = MAX_ERRORS;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number of errors. Setting to default (" + MAX_ERRORS + ").");
            maxErrors = MAX_ERRORS;
        }
        String word = this.dictionary.getRandomWord();
        Game game = new Game(word, maxErrors);
        game.play(this.scanner);
    }

    public void showDictionaryPaginated() {
        showDictionaryPaginated(20);
    }

    public void showDictionaryPaginated(int pageSize) {
        List<String> words = this.dictionary.getWords();
        int totalWords = words.size();
        int totalPages = (totalWords + pageSize - 1) / pageSize;
        for (int i = 0; i < totalWords; i += pageSize) {
            int end = Math.min(i + pageSize, totalWords);
            for (int j = i; j < end; j++) {
                System.out.println(words.get(j));
            }
            System.out.println("\nPage " + ((i / pageSize) + 1) + " of " + totalPages);
            if (end < totalWords) {
                System.out.print("'q' to quit, Enter to continue to the next page...");
                String input = this.scanner.nextLine().trim().toLowerCase();
                if ("q".equals(input)) {
                    return;
                }
            }
        }
        System.out.println("End of dictionary.");
    }

    public void addWordToDictionary() throws IOException {
        System.out.print("Enter a word to add to the dictionary: ");
        String word = this.scanner.nextLine().trim();
        if (!word.isEmpty()) {
            this.dictionary.add(word);
            this.dictionary.sync();
            System.out.println("Word '" + word + "' added to the dictionary.");
        } else {
            System.out.println("No word entered.");
        }
    }

    public void removeWordFromDictionary() throws IOException {
        System.out.print("Enter a word to remove from the dictionary: ");
        String word = this.scanner.nextLine().trim();
        if (!word.isEmpty()) {
            if (this.dictionary.remove(word)) {
                this.dictionary.sync();
                System.out.println("Word '" + word + "' removed from the dictionary.");
            } else {
                System.out.println("Word '" + word + "' not found in the dictionary.");
            }
        } else {
            System.out.println("No word entered.");
        }
    }

    public static void main(String[] args) throws IOException {
        Hangman hangman = new Hangman();
        hangman.showMenu();
    }
}
