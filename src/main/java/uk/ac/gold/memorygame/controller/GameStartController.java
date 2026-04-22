package uk.ac.gold.memorygame.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.Parent;
import uk.ac.gold.memorygame.MemoryGameApp;
import uk.ac.gold.memorygame.view.GameStartView;

public class GameStartController {

    private static final Logger LOGGER = LogManager.getLogger();

    private final MemoryGameApp app;

    private GameStartView gameStartView;

    public GameStartController(MemoryGameApp app) {
        this.app = app;
        createView();
    }

    public Parent getView() {
        LOGGER.debug("Getting start screen view");
        return gameStartView.getRoot();
    }

    private void createView() {
        gameStartView = new GameStartView();
        setStartButtonHandler();
    }

    private void setStartButtonHandler() {
        LOGGER.debug("Passing start button handler to start screen view");
        gameStartView.setStartClickHandler(event -> onStartButtonClick());
    }

    private void onStartButtonClick() {
        LOGGER.debug("Start button click");
        app.showGameScreen(gameStartView.getSelectedDifficulty());
    }
}