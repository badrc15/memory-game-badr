package uk.ac.gold.memorygame.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.util.Duration;
import uk.ac.gold.memorygame.MemoryGameApp;
import uk.ac.gold.memorygame.config.CardDeck;
import uk.ac.gold.memorygame.config.Difficulty;
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
    private Timeline timer;
    private int timeRemaining;

    public GamePlayController(MemoryGameApp app, CardDeck<String> cardSet, Difficulty difficulty) {
        this.app = app;
        this.timeRemaining = difficulty.getTimeLimitSeconds();
        this.gameModel = createGameModel(difficulty.getPairs());

        gameModel.addObserver(this);

        createView(cardSet);
        setCardsClickHandler();
        setMenuButtonHandler();

        // Ensure score and other UI are shown correctly when the screen opens.
        gamePlayView.update();
        startTimer();
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
    
    private void setMenuButtonHandler() {
    gamePlayView.setMenuClickHandler(event -> onMenuButtonClick());
}

private void onMenuButtonClick() {
    if (timer != null) {
        timer.stop();
    }

    gamePlayView.cancelMismatchedPause();
    gameModel.removeObserver(this);

    app.showStartScreen();
    }

    // Handle a card click from the user by passing the selected card into the model.
    private void onCardClick(Card card) {
        LOGGER.debug("Card clicked {}", card);
        gameModel.selectCard(card);
    }

    private void startTimer() {
    updateTimerLabel();

    timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
        timeRemaining--;
        updateTimerLabel();

        if (timeRemaining <= 0) {
            timer.stop();
            onTimeUp();
        }
    }));

    timer.setCycleCount(Timeline.INDEFINITE);
    timer.play();
    }

    private void updateTimerLabel() {
        gamePlayView.setTimerText("Time left: " + timeRemaining);
    }

    private void onTimeUp() {
    LOGGER.debug("Time is up");

    if (timer != null) {
        timer.stop();
    }

    gamePlayView.cancelMismatchedPause();
    gameModel.removeObserver(this);

    app.showGameOverScreen(gameModel.getScore());
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