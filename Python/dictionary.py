class Dictionary:
    def __init__(self, filename):
        self.filename = filename
        with open(filename, 'r') as f:
            self.words = [line.strip() for line in f if line.strip()]

    def add(self, word):
        if word not in self.words:
            self.words.append(word)

    def remove(self, word):
        if word in self.words:
            self.words.remove(word)
            return True
        return False

    def sync(self):
        with open(self.filename, 'w') as f:
            for word in self.words:
                f.write(word + '\n')

    def get_random_word(self):
        import random
        if not self.words:
            raise ValueError("Dictionary is empty.")
        return random.choice(self.words).lower()
