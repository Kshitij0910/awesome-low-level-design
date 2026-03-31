package kshitij.tictactoe;

import java.util.Scanner;
import kshitij.tictactoe.enums.GameStatus;
import kshitij.tictactoe.enums.Symbol;
import kshitij.tictactoe.models.Player;

public class TicTacToeDriver {
    public static void main(String[] args) {
        Player player1 = new Player("Alice", Symbol.X);
        Player player2 = new Player("Bob", Symbol.O);

        Game game = new Game(player1, player2);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println(player1.getId() + " (" + player1.getSymbol() + ") vs " + player2.getId() + " (" + player1.getSymbol() + ")");

        while (game.getStatus() == GameStatus.IN_PROGRESS) {
            // Display board
            game.getBoard().printBoard();

            // Get current player input
            Player currentPlayer = game.getCurrentPlayer();
            System.out.println(currentPlayer.getId() + "'s turn (" + currentPlayer.getSymbol().getSymbol() + ")");
            System.out.print("Enter row (0-2): ");
            int row = scanner.nextInt();
            System.out.print("Enter col (0-2): ");
            int col = scanner.nextInt();

            try {
                game.makeMove(currentPlayer, row, col);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Game over - display results
        game.getBoard().printBoard();
        if (game.getWinner() != null) {
            System.out.println("Winner: " + game.getWinner().getId());
        } else {
            System.out.println("It's a draw!");
        }

        scanner.close();
    }

}
