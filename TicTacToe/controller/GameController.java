package TicTacToe.controller;

import java.util.Scanner;

import TicTacToe.domain.Game;
import TicTacToe.domain.GameStatus;
import TicTacToe.domain.Player;

public class GameController {
    private final Game game;

    public GameController(Player player1, Player player2) {
        this.game = new Game(player1, player2);
    }

    public boolean makeMove(int row, int col) {
        return game.makeMove(row, col);
    }

    public Player getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    public GameStatus getGameStatus() {
        return game.getGameStatus();
    }

    public String getBoardDisplay() {
        return game.getBoardDisplay();
    }

    public void play(Scanner scanner) {
        while (getGameStatus() == GameStatus.IN_PROGRESS) {
            System.out.println(getBoardDisplay());
            Player currentPlayer = getCurrentPlayer();
            System.out.printf("%s (%s), enter row and column (0-2): ",
                    currentPlayer.getName(), currentPlayer.getSymbol());

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter two numbers.");
                scanner.nextLine();
                continue;
            }
            int row = scanner.nextInt();

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter two numbers.");
                scanner.nextLine();
                continue;
            }
            int col = scanner.nextInt();
            scanner.nextLine();

            if (!makeMove(row, col)) {
                System.out.println("Invalid move. Choose an empty position between 0 and 2.");
            }
        }

        System.out.println(getBoardDisplay());
        if (getGameStatus() == GameStatus.DRAW) {
            System.out.println("The game is a draw.");
        } else {
            System.out.println(getCurrentPlayer().getName() + " wins!");
        }
    }
}
