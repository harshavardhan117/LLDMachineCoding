package TicTacToe.domain;
public class Tile {
    private Symbol symbol;
    private final int row;
    private final int col;

    public Tile(int row, int col) {
        this.symbol = null;
        this.row = row;
        this.col = col;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    boolean placeSymbol(Symbol symbol) {
        if (symbol == null || isOccupied()) {
            return false;
        }
        this.symbol = symbol;
        return true;
    }

    public boolean isOccupied() {
        return this.symbol != null;
    }
}
