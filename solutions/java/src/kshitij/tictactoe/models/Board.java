package kshitij.tictactoe.models;

import kshitij.tictactoe.enums.Symbol;
import kshitij.tictactoe.exceptions.InvalidMoveException;

public class Board {
    private int size;
    private Cell[][] board;

    private int moves;


    public Board(int size) {
        this.size = size;
        moves = 0;
        this.board = new Cell[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public Cell getCell(int row, int col) {
        return  board[row][col];
    }

    public int getSize() {
        return size;
    }

    public int getMoves() {
        return moves;
    }

    public boolean placeSymbol(Symbol s, int row, int col) {
        if(!isValid(row, col)) {
            throw new InvalidMoveException("Invalid move! Out of bounds!");
        }
        if(board[row][col].getSymbol() != Symbol.EMPTY) {
            throw new InvalidMoveException("Invalid move! Cell already occupied");
        }
        board[row][col].setSymbol(s);
        moves++;
        return true;
    }

    public boolean isBoardFull() {
        return moves == size * size;
    }

    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < size; i++) {
            System.out.print("| ");
            for (int j = 0; j < size; j++) {
                Symbol symbol = board[i][j].getSymbol();
                System.out.print(symbol.getSymbol() + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && col >= 0 && row < size && col < size;
    }
}
