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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import uk.ac.gold.memorygame.config.Difficulty;

public class GameStartView {

    private static final Logger LOGGER = LogManager.getLogger();

    // Root JavaFX parent node.
    private final VBox root;

    private final Button startButton;
    private final Button ticTacToeButton;
    private final Label titleLabel;
    private final Label subtitleLabel;

    private final ChoiceBox<Difficulty> difficultyChoice;
    private final Label difficultyLabel;

    public GameStartView() {
        root = new VBox();
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 30; -fx-background-color: #f4f4f4;");

        titleLabel = new Label("Game Hub");
        titleLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");

        subtitleLabel = new Label("Choose a game to play");
        subtitleLabel.setStyle("-fx-font-size: 15px;");

        difficultyLabel = new Label("Select difficulty:");
        difficultyLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        difficultyChoice = new ChoiceBox<>();
        difficultyChoice.getItems().addAll(Difficulty.values());
        difficultyChoice.setValue(Difficulty.MEDIUM);

        startButton = new Button("Play Memory Game");
        startButton.setStyle("-fx-font-size: 14px; -fx-padding: 8 20 8 20;");

        ticTacToeButton = new Button("Play Vanishing Tic Tac Toe");
        ticTacToeButton.setStyle("-fx-font-size: 14px; -fx-padding: 8 20 8 20;");

        HBox gameChoiceBox = new HBox();
        gameChoiceBox.setSpacing(30);
        gameChoiceBox.setAlignment(Pos.CENTER);

        VBox memoryPanel = new VBox();
        memoryPanel.setSpacing(12);
        memoryPanel.setAlignment(Pos.CENTER);
        memoryPanel.setStyle("-fx-padding: 25; -fx-background-color: white; -fx-border-color: #cccccc; -fx-border-radius: 10; -fx-background-radius: 10;");

        Label memoryTitle = new Label("Memory Game");
        memoryTitle.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Label memoryDescription = new Label("Match all cards before the timer runs out.");
        memoryDescription.setWrapText(true);

        memoryPanel.getChildren().addAll(
                memoryTitle,
                memoryDescription,
                difficultyLabel,
                difficultyChoice,
                startButton
        );

        VBox ticTacToePanel = new VBox();
        ticTacToePanel.setSpacing(12);
        ticTacToePanel.setAlignment(Pos.CENTER);
        ticTacToePanel.setStyle("-fx-padding: 25; -fx-background-color: white; -fx-border-color: #cccccc; -fx-border-radius: 10; -fx-background-radius: 10;");

        Label ticTacToeTitle = new Label("Vanishing Tic Tac Toe");
        ticTacToeTitle.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Label ticTacToeDescription = new Label("Take turns placing Xs and Os. First to three wins. Your oldest move will vanish when you place a fourth!");
        ticTacToeDescription.setWrapText(true);

        ticTacToePanel.getChildren().addAll(
                ticTacToeTitle,
                ticTacToeDescription,
                ticTacToeButton
        );

        gameChoiceBox.getChildren().addAll(memoryPanel, ticTacToePanel);

        root.getChildren().addAll(
                titleLabel,
                subtitleLabel,
                gameChoiceBox
        );

        memoryPanel.setPrefWidth(260);
        memoryPanel.setMinWidth(260);
        memoryPanel.setMaxWidth(260);

        ticTacToePanel.setPrefWidth(260);
        ticTacToePanel.setMinWidth(260);
        ticTacToePanel.setMaxWidth(260);

        memoryDescription.setWrapText(true);
        memoryDescription.setMaxWidth(220);

        ticTacToeDescription.setWrapText(true);
        ticTacToeDescription.setMaxWidth(220);

        ticTacToeTitle.setWrapText(true);
        ticTacToeTitle.setMaxWidth(280);
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

    public void setTicTacToeClickHandler(EventHandler<ActionEvent> handler) {
    ticTacToeButton.setOnAction(handler);
    }
}
