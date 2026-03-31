package kshitij.tictactoe.states;

import kshitij.tictactoe.Game;
import kshitij.tictactoe.models.Player;

public interface GameState {
    void handleMove(Game game, Player player, int row, int col);

}
