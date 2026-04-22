package uk.ac.gold.memorygame.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Platform;
import javafx.scene.Parent;
import uk.ac.gold.memorygame.MemoryGameApp;
import uk.ac.gold.memorygame.config.Difficulty;
import uk.ac.gold.memorygame.view.components.GameOverView;

public class GameOverController {

    private static final Logger LOGGER = LogManager.getLogger();

    private final MemoryGameApp app;
    private final int score;

    private GameOverView gameOverView;

    public GameOverController(MemoryGameApp app, int score) {
        this.app = app;
        this.score = score;
        createView();
    }

    public Parent getView() {
        LOGGER.debug("Getting game over screen view");
        return gameOverView.getRoot();
    }

    private void createView() {
        gameOverView = new GameOverView(score);
        setRestartButtonHandler();
        setQuitButtonHandler();
    }

    private void setRestartButtonHandler() {
        gameOverView.setRestartClickHandler(_ -> onRestartButtonClick());
    }

    private void setQuitButtonHandler() {
        gameOverView.setQuitClickHandler(_ -> onQuitButtonClick());
    }

    private void onRestartButtonClick() {
        LOGGER.debug("Restart button click");
        app.showGameScreen(Difficulty.MEDIUM);
    }

    private void onQuitButtonClick() {
        LOGGER.debug("Quit button click");
        Platform.exit();
    }
}