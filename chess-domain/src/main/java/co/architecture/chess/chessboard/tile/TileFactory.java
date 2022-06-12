package co.architecture.chess.chessboard.tile;

import java.util.HashMap;
import java.util.Map;

public class TileFactory {
    private static final Map<String, Tile> factory = new HashMap<>();

    static {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                factory.put(file.getSymbol() + rank.getSymbol(), new Tile(rank, file));
            }
        }
    }

    public static Tile from(String input) {
        if (input == null || !factory.containsKey(input)) {
            throw new IllegalArgumentException("올바른 타일 입력 포맷이 아닙니다.");
        }
        return factory.get(input);
    }
}
