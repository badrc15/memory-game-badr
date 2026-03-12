package uk.ac.gold.memorygame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uk.ac.gold.memorygame.model.exceptions.BoardStateException;

public class Board {

    private final List<Card> cards = new ArrayList<>();

    public Board(int numberOfPairs) {
        this(numberOfPairs, true);
    }

    public Board(int numberOfPairs, boolean shuffle) {
        for (int i = 0; i < numberOfPairs; i++) {
            cards.add(new Card(i));
            cards.add(new Card(i));
        }

        if (shuffle) {
            Collections.shuffle(cards);
        }
    }

    /*
     * -----------------------------
     * Access
     * -----------------------------
     */

    public List<Card> getCards() {
        // Prevent modification.
        return Collections.unmodifiableList(cards);
    }

    public Card getCard(int id) {
        return cards.get(id);
    }

    public int numberOfCards() {
        return cards.size();
    }

    public int numberOfPairs() {
        return cards.size() / 2;
    }

    /*
     * -----------------------------
     * Game-related queries
     * -----------------------------
     */

    public int countMatchedCards() {
        int count = 0;

        for (Card card : cards) {
            if (card.isMatched()) {
                count++;
            }
        }

        if (count % 2 != 0) {
            throw new BoardStateException(
                    "Matched card count must be even, counted: " + count
            );
        }

        return count;
    }

    public int countMatchedPairs() {
        return countMatchedCards() / 2;
    }

    public boolean allCardsMatched() {
        return countMatchedCards() == numberOfCards();
    }
}
