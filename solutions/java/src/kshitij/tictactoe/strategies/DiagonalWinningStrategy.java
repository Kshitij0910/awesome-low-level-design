package kshitij.tictactoe.strategies;

import kshitij.tictactoe.models.Board;
import kshitij.tictactoe.models.Player;

public class DiagonalWinningStrategy implements WinningStrategy {

    @Override
    public boolean checkWinner(Board board, Player player) {
        int size = board.getSize();
        boolean mainDiagWin = true;
        for(int i = 0; i < size; i++) {
            if(board.getCell(i, i).getSymbol() != player.getSymbol()) {
                mainDiagWin = false;
                break;
            }
        }
        if(mainDiagWin) return true;

        boolean antiDiagWin = true;
        for(int i = 0; i < size; i++) {
            if(board.getCell(size - i - 1, i).getSymbol() != player.getSymbol()) {
                antiDiagWin = false;
                break;
            }
        }

        return antiDiagWin;
    }

}
