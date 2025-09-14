package kshitij.tictactoe;

import java.util.ArrayList;
import java.util.List;
import kshitij.tictactoe.enums.GameStatus;
import kshitij.tictactoe.models.Board;
import kshitij.tictactoe.models.Player;
import kshitij.tictactoe.states.GameState;
import kshitij.tictactoe.states.InProgressState;
import kshitij.tictactoe.strategies.ColumnWinningStrategy;
import kshitij.tictactoe.strategies.DiagonalWinningStrategy;
import kshitij.tictactoe.strategies.RowWinningStrategy;
import kshitij.tictactoe.strategies.WinningStrategy;

public class Game {
    private Player player1;
    private Player player2;
    private Board board;
    private Player currentPlayer;
    private Player winner;
    private List<WinningStrategy> strategies;
    private GameStatus status;
    private GameState state;


    public Game(Player player1, Player player2) {
        this.board = new Board(3);
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.state = new InProgressState();
        this.status = GameStatus.IN_PROGRESS;
        this.strategies = List.of(
            new RowWinningStrategy(),
            new ColumnWinningStrategy(),
            new DiagonalWinningStrategy()
        );
    }

    public void makeMove(Player player, int row, int col) {
        state.handleMove(this, player, row, col);
    }

    public boolean checkWinner(Player player) {
        for (WinningStrategy strategy : strategies) {
            if (strategy.checkWinner(board, player)) {
                return true;
            }
        }
        return false;
    }

    public void switchPlayer() {
        this.currentPlayer = this.currentPlayer == this.player1 ? this.player2 : this.player1;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public List<WinningStrategy> getStrategies() {
        return strategies;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public kshitij.tictactoe.enums.GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }
}
