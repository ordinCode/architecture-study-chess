package co.chess.domain.utils;

import co.chess.domain.chessboard.tile.Tile;

public class TileUtil {
    public static int calculateStraightDistance(Tile source, Tile target) {
        return Math.abs(source.calculateFileDistance(target) + source.calculateRankDistance(target));
    }

    public static int calculateCrossDistance(Tile source, Tile target) {
        return Math.abs(source.calculateRankDistance(target));
    }

    public static Tile findEnpassantAttackedTile(Tile source, Tile target) {
        return new Tile(source.getRank(), target.getFile());
    }
}
