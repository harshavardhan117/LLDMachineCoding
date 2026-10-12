package TicTacToe.domain;


public class Player{
    private final String name;
    private final Symbol symbol;

    public Player(String name, Symbol symbol) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Player name cannot be blank");
        }
        if (symbol == null) {
            throw new IllegalArgumentException("Player symbol cannot be null");
        }
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }
}
