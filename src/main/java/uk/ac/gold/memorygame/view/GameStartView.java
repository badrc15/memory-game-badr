package uk.ac.gold.memorygame.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import uk.ac.gold.memorygame.config.Difficulty;

public class GameStartView {

    private static final Logger LOGGER = LogManager.getLogger();

    // Root JavaFX parent node.
    private final VBox root;

    private final Button startButton;

    private final ChoiceBox<Difficulty> difficultyChoice;
    private final Label difficultyLabel;

    public GameStartView() {
        root = new VBox();
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        startButton = new Button("Start Game");

        difficultyChoice = new ChoiceBox<>();
        difficultyChoice.getItems().addAll(Difficulty.values());
        difficultyChoice.setValue(Difficulty.MEDIUM);

        difficultyLabel = new Label("Select Difficulty:");

        root.getChildren().addAll(difficultyLabel, difficultyChoice, startButton);
    }

    public Parent getRoot() {
        return root;
    }

    public Difficulty getSelectedDifficulty() {
    return difficultyChoice.getValue();
    }   

    public void setStartClickHandler(EventHandler<ActionEvent> handler) {
        LOGGER.debug("Setting start click handler");
        startButton.setOnAction(handler);
    }
}
