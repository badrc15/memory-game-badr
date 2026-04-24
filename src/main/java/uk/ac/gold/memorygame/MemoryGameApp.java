package uk.ac.gold.memorygame;

import java.util.List;
import java.util.Random;
import java.util.prefs.Preferences;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.ac.gold.memorygame.config.CardDeck;
import uk.ac.gold.memorygame.config.Difficulty;
import uk.ac.gold.memorygame.config.TextCardDeck;
import uk.ac.gold.memorygame.controller.GameOverController;
import uk.ac.gold.memorygame.controller.GamePlayController;
import uk.ac.gold.memorygame.controller.GameStartController;
import uk.ac.gold.memorygame.controller.TicTacToeController;

/**
 * JavaFX App: MemoryGame
 *
 * MemoryGame, a subclass of JavaFX Application, is the bridge between the
 * game implementation and the JavaFX framework. It defines three methods that
 * correspond to distinct modes of user interaction with the application:
 *
 * - showStartScreen()
 * - showGameScreen()
 * - showGameOverScreen()
 *
 * Each method instantiates a controller, which is the entry point into the MVC
 * structured code.
 *
 * The abstraction boundary here is the javafx.stage.Stage class. The
 * application is responsible for managing the stage. Controllers are
 * responsible for deciding what should be placed on the stage, but they have
 * no direct access to the stage itself. Instead, controllers call the public
 * application methods when they want the stage to be updated.
 */
public class MemoryGameApp extends Application {

    private static final Logger LOGGER = LogManager.getLogger();

    private Stage primaryStage;
    private Difficulty currentDifficulty;
    private CardDeck<String> currentDeck;

    // Application/user-related state: single instance that persists for the
    // lifetime of the application. Could also be saved and reloaded at
    // start-up to restore the user's game history or preferences.
    private Preferences prefs = Preferences.userRoot().node("memorygame");

    @Override
    public void start(Stage stage) {
        LOGGER.debug("Application starting");

        primaryStage = stage;

        // Show the start screen when the application starts.
        showStartScreen();
        primaryStage.show();
    }

    public void showStartScreen() {
        LOGGER.debug("Creating start screen");

        GameStartController controller = new GameStartController(this);
        primaryStage.setScene(new Scene(controller.getView(), 640, 480));
    }

    private CardDeck<String> createRandomDeck() {
    List<CardDeck<String>> decks = List.of(
            new TextCardDeck(
                    "Animals",
                    List.of("bat", "bug", "cat", "cow", "dog", "pig", "fox", "owl")
            ),
            new TextCardDeck(
                    "Numbers",
                    List.of("1", "2", "3", "4", "5", "6", "7", "8")
            ),
            new TextCardDeck(
                    "Emoji",
                    List.of("😀", "🐶", "🍎", "🚗", "⚽", "🎵", "🌟", "🔥")
            )
    );

    Random random = new Random();
    return decks.get(random.nextInt(decks.size()));
    }

    public void showTicTacToeScreen() {
        LOGGER.debug("Creating Tic Tac Toe screen");

        TicTacToeController controller = new TicTacToeController(this);
        primaryStage.setScene(new Scene(controller.getView(), 640, 480));
    }

    public void showGameScreen(Difficulty difficulty) {
        LOGGER.debug("Creating game screen");

        currentDifficulty = difficulty;
        currentDeck = createRandomDeck();

        GamePlayController controller = new GamePlayController(this, currentDeck, difficulty);
        primaryStage.setScene(new Scene(controller.getView(), 640, 480));
    }

    public void showGameScreen(Difficulty difficulty, CardDeck<String> deck) {
    LOGGER.debug("Creating game screen from saved settings");

    currentDifficulty = difficulty;
    currentDeck = deck;

    GamePlayController controller = new GamePlayController(this, currentDeck, currentDifficulty);
    primaryStage.setScene(new Scene(controller.getView(), 640, 480));
    }

    public void restartCurrentGame() {
    LOGGER.debug("Restarting current game");

    if (currentDifficulty == null || currentDeck == null) {
        showStartScreen();
        return;
    }

    showGameScreen(currentDifficulty, currentDeck);
    }

    public void showGameOverScreen(int score) {
        LOGGER.debug("Creating game over screen");

        GameOverController controller = new GameOverController(this, score);
        primaryStage.setScene(new Scene(controller.getView(), 640, 480));
    }

    public Preferences getPrefs() {
        return prefs;
    }

    public static void main(String[] args) {
        launch();
    }
}
