package kshitij.tictactoe.strategies;

import kshitij.tictactoe.models.Board;
import kshitij.tictactoe.models.Player;

public interface WinningStrategy {
    boolean checkWinner(Board board, Player player);

}
