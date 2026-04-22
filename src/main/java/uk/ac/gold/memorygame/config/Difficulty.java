package uk.ac.gold.memorygame.config;

public enum Difficulty {
    EASY(3),
    MEDIUM(6),
    HARD(8);

    private final int pairs;

    Difficulty(int pairs) {
        this.pairs = pairs;
    }

    public int getPairs() {
        return pairs;
    }

    @Override
    public String toString() {
        return switch (this) {
            case EASY -> "Easy";
            case MEDIUM -> "Medium";
            case HARD -> "Hard";
        };
    }
}