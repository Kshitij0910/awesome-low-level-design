package kshitij.tictactoe.states;

import kshitij.tictactoe.Game;
import kshitij.tictactoe.exceptions.InvalidMoveException;
import kshitij.tictactoe.models.Player;

public class DrawState implements GameState{

    @Override
    public void handleMove(Game game, Player player, int row, int col) {
        throw new InvalidMoveException("Game TIED!");
    }
}
