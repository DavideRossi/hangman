#include <iostream>
#include <string>
#include <vector>
#include <limits>
#include "dictionary.h"
#include "game.h"

class Hangman {
private:
    Dictionary dictionary;

public:
    static const int MAX_ERRORS = 6;

    Hangman() : dictionary("../words.txt") {}

    void show_menu() {
        while (true) {
            std::cout << "\n--- Hangman Main Menu ---\n";
            std::cout << "1. Show dictionary\n";
            std::cout << "2. Add word to dictionary\n";
            std::cout << "3. Remove word from dictionary\n";
            std::cout << "4. Start new game\n";
            std::cout << "5. Exit\n";
            std::cout << "Select an option (1-5): ";
            std::string choice;
            std::getline(std::cin, choice);

            if (choice == "1") {
                show_dictionary_paginated();
            } else if (choice == "2") {
                add_word_to_dictionary();
            } else if (choice == "3") {
                remove_word_from_dictionary();
            } else if (choice == "4") {
                play_game();
            } else if (choice == "5") {
                std::cout << "Goodbye!\n";
                break;
            } else {
                std::cout << "Invalid option. Please try again.\n";
            }
        }
    }

    void play_game() {
        std::cout << "Enter the maximum number of errors allowed (default is " << MAX_ERRORS << "): ";
        std::string input;
        std::getline(std::cin, input);
        int max_errors;
        try {
            max_errors = std::stoi(input);
            if (max_errors < 1) {
                throw std::out_of_range("Maximum errors must be at least 1");
            }
        } catch (...) {
            std::cout << "Invalid number of errors. Setting to default (" << MAX_ERRORS << ").\n";
            max_errors = MAX_ERRORS;
        }
        std::string word = dictionary.get_random_word();
        Game game(word, max_errors);
        game.play();
    }

    void show_dictionary_paginated(int page_size = 20) {
        //TODO fill me
    }

    void add_word_to_dictionary() {
        //TODO fill me
    }

    void remove_word_from_dictionary() {
        std::cout << "Enter a word to remove from the dictionary: ";
        std::string word;
        std::getline(std::cin, word);
        if (!word.empty()) {
            if (dictionary.remove(word)) {
                dictionary.sync();
                std::cout << "Word '" << word << "' removed from the dictionary.\n";
            } else {
                std::cout << "Word '" << word << "' not found in the dictionary.\n";
            }
        } else {
            std::cout << "No word entered.\n";
        }
    }
};

int main() {
    Hangman hangman;
    hangman.show_menu();
    return 0;
}
