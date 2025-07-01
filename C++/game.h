#pragma once

#include <iostream>
#include <string>
#include <unordered_set>
#include <cctype>
#include <algorithm>

class Game {
private:
    std::string targetWord;
    int maxErrors;
    int remainingErrors;
    std::unordered_set<char> guessedLetters;

    static char toLower(char c) {
        return static_cast<char>(std::tolower(static_cast<unsigned char>(c)));
    }

public:
    Game(const std::string& word, int maxErrors)
        : targetWord(word), maxErrors(maxErrors), remainingErrors(maxErrors) {
        std::transform(targetWord.begin(), targetWord.end(), targetWord.begin(), toLower);
    }

    bool guess(char letter) {
        //TODO fill me
    }

    std::string grid() const {
        //TODO fill me
    }

    char readGuess() {
        char inputChar;
        while (true) {
            std::cout << "Guess a letter: ";
            std::string input;
            std::getline(std::cin, input);
            if (input.length() != 1 || !std::isalpha(static_cast<unsigned char>(input[0]))) {
                std::cout << "Please enter a single letter." << std::endl;
            } else {
                inputChar = toLower(input[0]);
                if (guessedLetters.count(inputChar)) {
                    std::cout << "Already guessed that letter, try another" << std::endl;
                } else {
                    break;
                }
            }
        }
        return inputChar;
    }

    void play() {
        //TODO fill me
    }
};

