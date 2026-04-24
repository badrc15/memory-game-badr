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
    private final Label titleLabel;
    private final Label subtitleLabel;

    private final ChoiceBox<Difficulty> difficultyChoice;
    private final Label difficultyLabel;

    public GameStartView() {
        root = new VBox();
        root.setSpacing(14);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 30; -fx-background-color: #f4f4f4;");

        titleLabel = new Label("Memory Game");
        titleLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");

        subtitleLabel = new Label("Match all cards before time runs out!");
        subtitleLabel.setStyle("-fx-font-size: 15px;");

        difficultyLabel = new Label("Select difficulty:");
        difficultyLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        difficultyChoice = new ChoiceBox<>();
        difficultyChoice.getItems().addAll(Difficulty.values());
        difficultyChoice.setValue(Difficulty.MEDIUM);

        startButton = new Button("Start Game");
        startButton.setStyle("-fx-font-size: 14px; -fx-padding: 8 20 8 20;");

        root.getChildren().addAll(
                titleLabel,
                subtitleLabel,
                difficultyLabel,
                difficultyChoice,
                startButton
        );
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
