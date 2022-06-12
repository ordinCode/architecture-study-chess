package co.architecture.chess.chessboard.tile;

import java.util.Arrays;

public enum Rank {
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8);

    private final String sysbol;
    private final int number;

    Rank(String sysbol, int number) {
        this.sysbol = sysbol;
        this.number = number;
    }

    public static Rank of(int number) {
        return Arrays.stream(Rank.values())
                .filter(x -> x.number == number)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public int calculateDistance(Rank rank) {
        return this.number - rank.number;
    }

    public String getSymbol() {
        return sysbol;
    }

    public int getNumber() {
        return number;
    }
}
