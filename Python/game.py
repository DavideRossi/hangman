class Game:
    def __init__(self, target_word, max_errors):
        self.target_word = target_word.lower()
        self.max_errors = max_errors
        self.remaining_errors = max_errors
        self.guessed_letters = set()  # Track guessed letters

    def guess(self, letter):
        letter = letter.lower()
        self.guessed_letters.add(letter)
        if letter not in self.target_word:
            return False
        else:
            return True

    def grid(self):
        result = []
        vowels = 'aeiou'
        for char in self.target_word:
            if char in self.guessed_letters:
                result.append(char.upper())
            elif char in vowels:
                result.append('+')
            elif char.isalpha():
                result.append('-')
            else:
                result.append(char)
        return ' '.join(result) + ' / errors left: ' + str(self.remaining_errors)

    def read_guess(self):
        while True:
            guess = input("Guess a letter: ").strip()
            if len(guess) != 1 or not guess.isalpha():
                print("Please enter a single letter.")
            elif guess.lower() in self.guessed_letters:
                print("Already guessed that letter, try another")
            else:
                return guess.lower()


    def play(self):
        while self.remaining_errors > 0:
            print(self.grid())
            guess = self.read_guess()
            if self.guess(guess):
                if all(char in self.guessed_letters for char in self.target_word):
                    print(f"Congratulations! You've guessed the word: {self.target_word.upper()}")
                    return
            else:
                self.remaining_errors -= 1
                print(f"Wrong guess! You have {self.remaining_errors} errors left.")
        print(f"Game over! The word was: {self.target_word.upper()}")
