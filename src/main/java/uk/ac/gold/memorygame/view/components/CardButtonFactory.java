package uk.ac.gold.memorygame.view.components;

import uk.ac.gold.memorygame.config.CardDeck;
import uk.ac.gold.memorygame.model.Card;

public class CardButtonFactory {

    public static <E> CardButton create(CardDeck<E> cardDeck, Card cardModel) {
        E item = cardDeck.get(cardModel.getPairId());

        if (item instanceof String text) {
            return new TextCardButton(cardModel, text);
        }

        throw new IllegalArgumentException(
                "Unsupported card deck item type: " +
                (item == null ? "null" : item.getClass().getName()));
    }
}