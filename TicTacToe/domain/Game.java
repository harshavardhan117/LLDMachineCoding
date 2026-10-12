package TicTacToe.domain;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private GameStatus gameStatus;

    public Game(Player player1, Player player2) {
        if (player1 == null || player2 == null) {
            throw new IllegalArgumentException("Players cannot be null");
        }
        if (player1.getSymbol() == player2.getSymbol()) {
            throw new IllegalArgumentException("Players must use different symbols");
        }
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    public boolean makeMove(int row, int col) {
        if (gameStatus != GameStatus.IN_PROGRESS) {
            return false;
        }

        if (!board.placeSymbol(currentPlayer.getSymbol(), row, col)) {
            return false;
        }

        gameStatus = board.currentState();
        if (gameStatus == GameStatus.IN_PROGRESS) {
            switchPlayer();
        }
        return true;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public String getBoardDisplay() {
        return board.toString();
    }

}
