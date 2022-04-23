package co.chess.domain.utils;

import co.chess.domain.chessboard.tile.Tile;

public class TileUtil {
    public static int getStraightDistance(Tile source, Tile target) {
        return Math.abs(source.calculateFileDistance(target) + source.calculateRankDistance(target));
    }

    public static int getCrossDistance(Tile source, Tile target) {
        return Math.abs(source.calculateRankDistance(target));
    }
}
