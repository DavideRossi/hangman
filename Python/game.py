class Game:
    def __init__(self, target_word, max_errors):
        self.target_word = target_word.lower()
        self.max_errors = max_errors
        self.remaining_errors = max_errors
        self.guessed_letters = set()  # Track guessed letters

    def guess(self, letter):
        #TODO fill me
        pass

    def grid(self):
        #TODO fill me
        pass

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
        #TODO fill me
        pass
