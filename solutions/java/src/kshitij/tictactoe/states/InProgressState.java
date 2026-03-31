package kshitij.tictactoe.states;

import kshitij.tictactoe.Game;
import kshitij.tictactoe.enums.GameStatus;
import kshitij.tictactoe.enums.Symbol;
import kshitij.tictactoe.exceptions.InvalidMoveException;
import kshitij.tictactoe.models.Player;

public class InProgressState implements GameState {

    @Override
    public void handleMove(Game game, Player player, int row, int col) {
        //Validate if it is the player's turn;
        if(game.getCurrentPlayer()!= player) {
            throw new InvalidMoveException("Not your turn!");
        }

        game.getBoard().placeSymbol(player.getSymbol(), row, col);
        if(game.checkWinner(player)) {
            game.setWinner(player);
            game.setStatus(player.getSymbol() == Symbol.X ? GameStatus.WINNER_X : GameStatus.WINNER_O);
            game.setState(new WinnerState());
        } else if(game.getBoard().isBoardFull()) {
            game.setStatus(GameStatus.DRAW);
            game.setState(new DrawState());
        } else {
            game.switchPlayer();
        }

    }
}
