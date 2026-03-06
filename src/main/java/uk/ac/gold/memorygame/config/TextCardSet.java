package uk.ac.gold.memorygame.config;

import java.util.List;

public class TextCardSet implements CardSet {

    private final String name;
    private final List<String> items;

    public TextCardSet(String name, List<String> items) {
        this.name = name;
        this.items = items;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int numberOfItems() {
        return items.size();
    };

    @Override
    public List<String> getItems() {
        return items;
    }

    @Override
    public String get(int pairId) {
        return items.get(pairId);
    }
}
