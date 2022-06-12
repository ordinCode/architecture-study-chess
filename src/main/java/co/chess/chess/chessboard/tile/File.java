package co.chess.chess.chessboard.tile;

import java.util.Arrays;

public enum File {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String symbol;
    private final int number;

    File(String symbol, int number) {
        this.symbol = symbol;
        this.number = number;
    }

    public static File of(int number) {
        return Arrays.stream(File.values())
                .filter(x -> x.number == number)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public int calculateDistance(File file) {
        return this.number - file.number;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getNumber() {
        return number;
    }
}
