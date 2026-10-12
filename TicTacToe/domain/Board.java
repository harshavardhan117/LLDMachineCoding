package TicTacToe.domain;

public class Board {
    private static final int SIZE = 3;
    private final Tile[][] grid = new Tile[SIZE][SIZE];

    public Board() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = new Tile(i, j);
            }
        }
    }

    public Symbol getSymbol(int row, int col) {
        if (!isWithinBounds(row, col)) {
            throw new IllegalArgumentException("Coordinates are outside the board");
        }
        return grid[row][col].getSymbol();
    }

    private boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    private boolean isValidMove(int row, int col) {
        return isWithinBounds(row, col) && !grid[row][col].isOccupied();
    }

    public boolean placeSymbol(Symbol symbol, int row, int col) {
        if (symbol == null || !isValidMove(row, col)) {
            return false;
        }
        return grid[row][col].placeSymbol(symbol);
    }


    public GameStatus currentState(){

        Symbol winner = checkWinner();
        if (winner != null) {
            return (winner == Symbol.X) ? GameStatus.X_WON : GameStatus.O_WON;
        }
        if(isBoardFull()){
            return GameStatus.DRAW;
        }
        return GameStatus.IN_PROGRESS;
    }


    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!grid[i][j].isOccupied()) {
                    return false;
                }
            }
        }
        return true;
    }

    private Symbol checkWinner() {
        for (int row = 0; row < SIZE; row++) {
            Symbol current = grid[row][0].getSymbol();
            if (current == null) {
                continue;
            }
            boolean rowMatches = true;
            for (int col = 1; col < SIZE; col++) {
                if (current != grid[row][col].getSymbol()) {
                    rowMatches = false;
                    break;
                }
            }
            if (rowMatches) {
                return current;
            }
        }

        for (int col = 0; col < SIZE; col++) {
            Symbol current = grid[0][col].getSymbol();
            if (current == null) {
                continue;
            }
            boolean columnMatches = true;
            for (int row = 1; row < SIZE; row++) {
                if (current != grid[row][col].getSymbol()) {
                    columnMatches = false;
                    break;
                }
            }
            if (columnMatches) {
                return current;
            }
        }

        Symbol current = grid[0][0].getSymbol();
        boolean diagonalMatches = current != null;
        for (int index = 1; index < SIZE && diagonalMatches; index++) {
            diagonalMatches = current == grid[index][index].getSymbol();
        }
        if (diagonalMatches) {
            return current;
        }

        current = grid[0][SIZE - 1].getSymbol();
        boolean antiDiagonalMatches = current != null;
        for (int index = 1; index < SIZE && antiDiagonalMatches; index++) {
            antiDiagonalMatches = current == grid[index][SIZE - 1 - index].getSymbol();
        }
        if (antiDiagonalMatches) {
            return current;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder display = new StringBuilder();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Symbol symbol = grid[row][col].getSymbol();
                display.append(symbol == null ? "." : symbol);
                if (col < SIZE - 1) {
                    display.append(" | ");
                }
            }
            if (row < SIZE - 1) {
                display.append(System.lineSeparator())
                        .append("---------")
                        .append(System.lineSeparator());
            }
        }
        return display.toString();
    }
}
