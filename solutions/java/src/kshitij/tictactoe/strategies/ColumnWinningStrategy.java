package kshitij.tictactoe.strategies;

import kshitij.tictactoe.models.Board;
import kshitij.tictactoe.models.Player;

public class ColumnWinningStrategy implements WinningStrategy{

    @Override
    public boolean checkWinner(Board board, Player player) {
        int size = board.getSize();
        for(int j = 0; j < size; j++) {
            boolean win = true;
            for(int i = 0; i < size; i++) {
                if(board.getCell(i, j).getSymbol() != player.getSymbol()) {
                    win = false;
                    break;
                }
            }
            if(win) return true;

        }
        return false;
    }

}
