import random
from dictionary import Dictionary
from game import Game

class Hangman:
    MAX_ERRORS = 6

    def __init__(self):
        self.dictionary = Dictionary('../words.txt')

    def show_menu(self):
        while True:
            print("\n--- Hangman Main Menu ---")
            print("1. Show dictionary")
            print("2. Add word to dictionary")
            print("3. Remove word from dictionary")
            print("4. Start new game")
            print("5. Exit")
            choice = input("Select an option (1-5): ").strip()
            if choice == "1":
                self.show_dictionary_paginated()
            elif choice == "2":
                self.add_word_to_dictionary()
            elif choice == "3":
                self.remove_word_from_dictionary()
            elif choice == "4":
                self.play_game()
            elif choice == "5":
                print("Goodbye!")
                break
            else:
                print("Invalid option. Please try again.")

    def play_game(self):
        max_errors = input(f"Enter the maximum number of errors allowed (default is {self.MAX_ERRORS}): ").strip()
        if max_errors.isdigit() and int(max_errors) > 0:
            max_errors = int(max_errors)
        else:
            print(f"Invalid number of errors. Setting to default ({self.MAX_ERRORS}).")
            max_errors = self.MAX_ERRORS
        word = self.dictionary.get_random_word()
        game = Game(word, max_errors)
        game.play()

    def show_dictionary_paginated(self, page_size=20):
        #TODO fill me
        pass

    def add_word_to_dictionary(self):
        #TODO fill me
        pass

    def remove_word_from_dictionary(self):
        word = input("Enter a word to remove from the dictionary: ").strip()
        if word:
            if self.dictionary.remove(word):
                self.dictionary.sync()
                print(f"Word '{word}' removed from the dictionary.")
            else:
                print(f"Word '{word}' not found in the dictionary.")
        else:
            print("No word entered.")

if __name__ == "__main__":
    hangman = Hangman()
    hangman.show_menu()
