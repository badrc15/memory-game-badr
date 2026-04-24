package uk.ac.gold.memorygame.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.Parent;
import uk.ac.gold.memorygame.MemoryGameApp;
import uk.ac.gold.memorygame.model.TicTacToeModel;
import uk.ac.gold.memorygame.view.TicTacToeView;

public class TicTacToeController {

    private static final Logger LOGGER = LogManager.getLogger();

    private final MemoryGameApp app;
    private final TicTacToeModel model;
    private final TicTacToeView view;

    public TicTacToeController(MemoryGameApp app) {
        this.app = app;
        this.model = new TicTacToeModel();
        this.view = new TicTacToeView();

        setCellHandlers();
        setButtonHandlers();
        updateView();
    }

    public Parent getView() {
        return view.getRoot();
    }

    private void setCellHandlers() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                final int selectedRow = row;
                final int selectedCol = col;

                view.setCellClickHandler(row, col, event -> onCellClick(selectedRow, selectedCol));
            }
        }
    }

    private void setButtonHandlers() {
        view.setRestartClickHandler(event -> onRestartClick());
        view.setMenuClickHandler(event -> app.showStartScreen());
    }

    private void onCellClick(int row, int col) {
        LOGGER.debug("Tic Tac Toe cell clicked: {}, {}", row, col);

        model.makeMove(row, col);
        updateView();
    }

    private void onRestartClick() {
        LOGGER.debug("Restarting Tic Tac Toe");

        model.reset();
        updateView();
    }

    private void updateView() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                view.setCellText(row, col, model.getCell(row, col));
                view.setCellDisabled(row, col, model.isGameOver() || !model.getCell(row, col).equals(""));
                view.resetCellStyle(row, col);
            }
        }

        if (model.isGameOver()) {
            if (model.getWinner().equals("Draw")) {
                view.setStatusText("Draw!");
            } else {
                view.setStatusText("Winner: " + model.getWinner());
                highlightWinningLine(model.getWinner());
            }
        } else {
            view.setStatusText("Current player: " + model.getCurrentPlayer());
        }
    }

    private void highlightWinningLine(String player) {
        for (int i = 0; i < 3; i++) {
            if (model.getCell(i, 0).equals(player)
                    && model.getCell(i, 1).equals(player)
                    && model.getCell(i, 2).equals(player)) {
                view.highlightCell(i, 0);
                view.highlightCell(i, 1);
                view.highlightCell(i, 2);
                return;
            }

            if (model.getCell(0, i).equals(player)
                    && model.getCell(1, i).equals(player)
                    && model.getCell(2, i).equals(player)) {
                view.highlightCell(0, i);
                view.highlightCell(1, i);
                view.highlightCell(2, i);
                return;
            }
        }

        if (model.getCell(0, 0).equals(player)
                && model.getCell(1, 1).equals(player)
                && model.getCell(2, 2).equals(player)) {
            view.highlightCell(0, 0);
            view.highlightCell(1, 1);
            view.highlightCell(2, 2);
            return;
        }

        if (model.getCell(0, 2).equals(player)
                && model.getCell(1, 1).equals(player)
                && model.getCell(2, 0).equals(player)) {
            view.highlightCell(0, 2);
            view.highlightCell(1, 1);
            view.highlightCell(2, 0);
        }
    }
}