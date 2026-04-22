package uk.ac.gold.memorygame.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.Parent;
import uk.ac.gold.memorygame.MemoryGameApp;
import uk.ac.gold.memorygame.config.CardDeck;
import uk.ac.gold.memorygame.model.Board;
import uk.ac.gold.memorygame.model.Card;
import uk.ac.gold.memorygame.model.GameModel;
import uk.ac.gold.memorygame.model.ScoringStrategy;
import uk.ac.gold.memorygame.observer.GameModelObserver;
import uk.ac.gold.memorygame.view.GamePlayView;

public class GamePlayController implements GameModelObserver {

    private static final Logger LOGGER = LogManager.getLogger();

    private final MemoryGameApp app;
    private final GameModel gameModel;
    private GamePlayView gamePlayView;

    public GamePlayController(MemoryGameApp app, CardDeck<String> cardSet, int numberOfPairs) {
        this.app = app;
        this.gameModel = createGameModel(numberOfPairs);

        gameModel.addObserver(this);

        createView(cardSet);
        setCardsClickHandler();

        // Ensure score and other UI are shown correctly when the screen opens.
        gamePlayView.update();
    }

    public Parent getView() {
        LOGGER.debug("Getting game screen view");
        return gamePlayView.getRoot();
    }

    private GameModel createGameModel(int numberOfPairs) {
    LOGGER.debug("Creating game model with {} pairs", numberOfPairs);

    Board board = new Board(numberOfPairs);

    ScoringStrategy scoring = new ScoringStrategy() {
        private int score = 0;

        @Override
        public void updateScore(boolean isMatch) {
            if (isMatch) {
                score++;
            }
        }

        @Override
        public int getScore() {
            return score;
        }

        @Override
        public void reset() {
            score = 0;
        }
    };

    return new GameModel(board, scoring);
    }

    //  Create the gameplay view
    private void createView(CardDeck<?> cardSet) {
        LOGGER.debug("Creating game play view");
        gamePlayView = new GamePlayView(gameModel, cardSet);
    }

    // Pass the card click handler into the view.
    private void setCardsClickHandler() {
        LOGGER.debug("Passing card click handler to game play view");
        gamePlayView.setCardClickHandler(this::onCardClick);
    }

    // Handle a card click from the user by passing the selected card into the model.
    private void onCardClick(Card card) {
        LOGGER.debug("Card clicked {}", card);
        gameModel.selectCard(card);
    }

    @Override
    public void onCardFlipUp(Card card) {
        LOGGER.debug("Observer: card flipped up {}", card);
        gamePlayView.updateCard(card);
        gamePlayView.update();
    }

    @Override
    public void onMatch(List<Card> cards) {
        LOGGER.debug("Observer: match {}", cards);
        gamePlayView.matchCards(cards);
        gamePlayView.update();
    }

    @Override
    public void onMismatch(List<Card> cards) {
        LOGGER.debug("Observer: mismatch {}", cards);
        gamePlayView.update();

        gamePlayView.hideCardsDelayed(() -> gamePlayView.updateCards(cards));
    }

    @Override
    public void onStateChange() {
        LOGGER.debug("Observer: state change");
        gamePlayView.update();
    }

    @Override
    public void onGameOver() {
        LOGGER.debug("Observer: game over");

        gamePlayView.cancelMismatchedPause();
        gameModel.removeObserver(this);

        app.showGameOverScreen(gameModel.getScore());
    }
}