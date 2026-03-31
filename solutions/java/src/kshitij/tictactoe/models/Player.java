package kshitij.tictactoe.models;

import kshitij.tictactoe.enums.Symbol;

public class Player {
    private String id;
    private Symbol symbol;

    public Player(String id, Symbol symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public String getId() {
        return id;
    }

    public Symbol getSymbol() {
        return symbol;
    }
}
