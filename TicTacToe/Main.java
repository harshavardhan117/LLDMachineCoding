package TicTacToe;

import java.util.Scanner;

import TicTacToe.controller.GameController;
import TicTacToe.domain.Player;
import TicTacToe.domain.Symbol;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Player player1 = readPlayer(scanner, "Player 1", Symbol.X);
            Player player2 = readPlayer(scanner, "Player 2", Symbol.O);

            GameController controller = new GameController(player1, player2);
            controller.play(scanner);
        }
    }

    private static Player readPlayer(Scanner scanner, String label, Symbol symbol) {
        while (true) {
            System.out.printf("Enter name for %s (%s): ", label, symbol);
            String name = scanner.nextLine();

            try {
                return new Player(name, symbol);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
