package co.chess.domain.move.general;

import co.chess.domain.chessboard.tile.Tile;

public class GeneralMovePatternChecker {
    public static boolean isCrossPattern(Tile source, Tile target) {
        return Math.abs(source.calculateRankDistance(target)) == Math.abs(source.calculateFileDistance(target));
    }

    public static boolean isStraightPattern(Tile source, Tile target) {
        return source.isSameRank(target) || source.isSameFile(target);
    }
}
