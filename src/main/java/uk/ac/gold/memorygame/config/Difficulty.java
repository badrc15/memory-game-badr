package uk.ac.gold.memorygame.config;

public enum Difficulty {
    EASY(3, 60),
    MEDIUM(6, 45),
    HARD(8, 30);

    private final int pairs;
    private final int timeLimitSeconds;

    Difficulty(int pairs, int timeLimitSeconds) {
        this.pairs = pairs;
        this.timeLimitSeconds = timeLimitSeconds;
    }

    public int getPairs() {
        return pairs;
    }

    public int getTimeLimitSeconds() {
        return timeLimitSeconds;
    }

    @Override
    public String toString() {
        return switch (this) {
            case EASY -> "Easy (6 cards, 60s)";
            case MEDIUM -> "Medium (12 cards, 45s)";
            case HARD -> "Hard (16 cards, 30s)";
        };
    }
}