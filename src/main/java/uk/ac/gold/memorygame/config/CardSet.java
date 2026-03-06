package uk.ac.gold.memorygame.config;

import java.util.List;

public interface CardSet {
    String getName();

    int numberOfItems();

    List<String> getItems();

    String get(int pairId);
}
