package uk.ac.gold.memorygame.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class TicTacToeView {

    private final VBox root;
    private final GridPane grid;
    private final Button[][] buttons;
    private final Label titleLabel;
    private final Label statusLabel;
    private final Button restartButton;
    private final Button menuButton;

    public TicTacToeView() {
        root = new VBox();
        root.setSpacing(15);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 30; -fx-background-color: #f4f4f4;");

        titleLabel = new Label("Noughts and Crosses");
        titleLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        statusLabel = new Label("Current player: X");
        statusLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(8);
        grid.setAlignment(Pos.CENTER);

        buttons = new Button[3][3];

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button("");
                button.setPrefSize(90, 90);
                button.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
                buttons[row][col] = button;
                grid.add(button, col, row);
            }
        }

        restartButton = new Button("Restart");
        restartButton.setStyle("-fx-font-size: 14px; -fx-padding: 8 20 8 20;");

        menuButton = new Button("Main Menu");
        menuButton.setStyle("-fx-font-size: 14px; -fx-padding: 8 20 8 20;");

        root.getChildren().addAll(titleLabel, statusLabel, grid, restartButton, menuButton);
    }

    public Parent getRoot() {
        return root;
    }

    public void setCellClickHandler(int row, int col, EventHandler<ActionEvent> handler) {
        buttons[row][col].setOnAction(handler);
    }

    public void setRestartClickHandler(EventHandler<ActionEvent> handler) {
        restartButton.setOnAction(handler);
    }

    public void setMenuClickHandler(EventHandler<ActionEvent> handler) {
        menuButton.setOnAction(handler);
    }

    public void setCellText(int row, int col, String text) {
        buttons[row][col].setText(text);
    }

    public void setCellDisabled(int row, int col, boolean disabled) {
        buttons[row][col].setDisable(disabled);
    }

    public void setStatusText(String text) {
        statusLabel.setText(text);
    }

    public void highlightCell(int row, int col) {
        buttons[row][col].setStyle(
                "-fx-font-size: 28px; -fx-font-weight: bold; -fx-background-color: #8fd694;"
        );
    }

    public void resetCellStyle(int row, int col) {
        buttons[row][col].setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
    }
}