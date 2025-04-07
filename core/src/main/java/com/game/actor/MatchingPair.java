package com.game.actor;

public class MatchingPair {
    private String imagePath;
    private String word;

    public MatchingPair(String word, String imagePath) {
        this.imagePath = imagePath;
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MatchingPair) {
            MatchingPair other = (MatchingPair) o;

            return other.getWord() == this.word && other.getImagePath() == this.imagePath;
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s:%s]", word, imagePath);
    }
}
