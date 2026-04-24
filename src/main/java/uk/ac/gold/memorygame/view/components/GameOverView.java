package uk.ac.gold.memorygame.view.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameOverView {

    private final VBox root;
    private final Button quitButton;
    private final Button restartButton;
    private final Text titleText;
    private final Text finalScore;

    public GameOverView(int score) {
        root = new VBox();
        root.setSpacing(15);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 30; -fx-background-color: #f4f4f4;");

        titleText = new Text("Game Over");
        titleText.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        finalScore = new Text("Final score: " + score);
        finalScore.setStyle("-fx-font-size: 18px;");

        restartButton = new Button("Restart");
        restartButton.setStyle("-fx-font-size: 14px; -fx-padding: 8 20 8 20;");

        quitButton = new Button("Quit");
        quitButton.setStyle("-fx-font-size: 14px; -fx-padding: 8 20 8 20;");

        root.getChildren().addAll(titleText, finalScore, restartButton, quitButton);
    }

    public Parent getRoot() {
        return root;
    }

    public void setRestartClickHandler(EventHandler<ActionEvent> handler) {
        restartButton.setOnAction(handler);
    }

    public void setQuitClickHandler(EventHandler<ActionEvent> handler) {
        quitButton.setOnAction(handler);
    }
}