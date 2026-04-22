package uk.ac.gold.memorygame.view.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameOverView {

    private final VBox root;
    private final Button quitButton;
    private final Button restartButton;
    private final Text finalScore;

    public GameOverView(int score) {
        root = new VBox();
        root.setSpacing(10);

        quitButton = new Button("Quit");
        restartButton = new Button("Restart");
        finalScore = new Text("Final score: " + score);

        root.getChildren().addAll(finalScore, restartButton, quitButton);
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