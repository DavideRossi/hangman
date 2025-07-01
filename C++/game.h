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
        letter = toLower(letter);
        guessedLetters.insert(letter);
        if (targetWord.find(letter) != std::string::npos) {
            return true;
        } else {
            return false;
        }
    }

    std::string grid() const {
        std::string result;
        std::string vowels = "aeiou";
        for (char c : targetWord) {
            if (guessedLetters.count(c)) {
                result += std::toupper(static_cast<unsigned char>(c));
            } else if (vowels.find(c) != std::string::npos) {
                result += '+';
            } else if (std::isalpha(static_cast<unsigned char>(c))) {
                result += '-';
            } else {
                result += c;
            }
            result += ' ';
        }
        result += "/ errors left: " + std::to_string(remainingErrors);
        return result;
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
        while (remainingErrors > 0) {
            std::cout << grid() << std::endl;
            char g = readGuess();
            if (guess(g)) {
                bool allGuessed = true;
                for (char c : targetWord) {
                    if (std::isalpha(static_cast<unsigned char>(c)) && !guessedLetters.count(c)) {
                        allGuessed = false;
                        break;
                    }
                }
                if (allGuessed) {
                    std::cout << "Congratulations! You've guessed the word: ";
                    for (char c : targetWord) std::cout << (char)std::toupper(static_cast<unsigned char>(c));
                    std::cout << std::endl;
                    return;
                }
            } else {
                --remainingErrors;
                std::cout << "Wrong guess! You have " << remainingErrors << " errors left." << std::endl;
            }
        }
        std::cout << "Game over! The word was: ";
        for (char c : targetWord) std::cout << (char)std::toupper(static_cast<unsigned char>(c));
        std::cout << std::endl;
    }
};

