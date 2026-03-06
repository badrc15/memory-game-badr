package uk.ac.gold.memorygame.config;

import java.util.Arrays;

public class LettersCardSet extends TextCardSet {

    public LettersCardSet() {
        super("Letters", Arrays.asList("ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("")));
    }
}
