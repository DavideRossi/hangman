#pragma once

#include <fstream>
#include <vector>
#include <string>
#include <algorithm>
#include <random>
#include <stdexcept>
#include <optional>

class Dictionary {
    std::string filename;
    std::vector<std::string> words;

public:
    Dictionary(const std::string& filename) : filename(filename) {
        std::ifstream file(filename);
        std::string line;
        while (std::getline(file, line)) {
            if (!line.empty()) {
                words.push_back(line);
            }
        }
    }

    void add(const std::string& word) {
        if (std::find(words.begin(), words.end(), word) == words.end()) {
            words.push_back(word);
        }
    }

    bool remove(const std::string& word) {
        auto it = std::find(words.begin(), words.end(), word);
        if (it != words.end()) {
            words.erase(it);
            return true;
        }
        return false;
    }

    void sync() const {
        std::ofstream file(filename);
        for (const auto& word : words) {
            file << word << '\n';
        }
    }

    std::vector<std::string> get_words() const {
        return words;
    }

    std::string get_random_word() const {
        if (words.empty()) {
            throw std::runtime_error("Dictionary is empty.");
        }
        static std::random_device rd;
        static std::mt19937 gen(rd());
        std::uniform_int_distribution<> dis(0, words.size() - 1);
        std::string word = words[dis(gen)];
        // Convert to lowercase
        std::transform(word.begin(), word.end(), word.begin(), ::tolower);
        return word;
    }
};
