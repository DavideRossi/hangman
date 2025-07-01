package it.unibo.ss.hangman;

import java.io.*;
import java.util.*;

public class Dictionary {
    private final String filename;
    private final List<String> words;

    public Dictionary(String filename) throws IOException {
        this.filename = filename;
        this.words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    this.words.add(line);
                }
            }
        }
    }

    public void add(String word) {
        if (!words.contains(word)) {
            this.words.add(word);
        }
    }

    public boolean remove(String word) {
        return this.words.remove(word);
    }

    public void sync() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filename))) {
            for (String word : this.words) {
                writer.write(word);
                writer.newLine();
            }
        }
    }

    public List<String> getWords() {
        return this.words;
    }

    public String getRandomWord() {
        if (this.words.isEmpty()) {
            throw new IllegalStateException("Dictionary is empty.");
        }
        Random random = new Random();
        return this.words.get(random.nextInt(this.words.size())).toLowerCase();
    }
}
