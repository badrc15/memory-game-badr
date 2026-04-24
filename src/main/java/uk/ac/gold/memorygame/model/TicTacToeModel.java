package uk.ac.gold.memorygame.model;

public class TicTacToeModel {

    private final String[][] board;
    private String currentPlayer;
    private String winner;
    private boolean gameOver;

    public TicTacToeModel() {
        board = new String[3][3];
        reset();
    }

    public void reset() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = "";
            }
        }

        currentPlayer = "X";
        winner = "";
        gameOver = false;
    }

    public boolean makeMove(int row, int col) {
        if (gameOver || !board[row][col].equals("")) {
            return false;
        }

        board[row][col] = currentPlayer;
        checkGameState();

        if (!gameOver) {
            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        }

        return true;
    }

    public String getCell(int row, int col) {
        return board[row][col];
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public String getWinner() {
        return winner;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private void checkGameState() {
        if (hasWon(currentPlayer)) {
            winner = currentPlayer;
            gameOver = true;
        } else if (isBoardFull()) {
            winner = "Draw";
            gameOver = true;
        }
    }

    private boolean hasWon(String player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }

            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }

        return board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)
                || board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player);
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col].equals("")) {
                    return false;
                }
            }
        }

        return true;
    }
}