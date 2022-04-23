package co.chess.domain.move.special.knight;

import co.chess.domain.chessboard.tile.Tile;

public class KnightMoveChecker {
    public static boolean isConform(Tile source, Tile target) {
        return (Math.abs(source.calculateFileDistance(target)) == 1
                && Math.abs(source.calculateRankDistance(target)) == 2)
                || (Math.abs(source.calculateRankDistance(target)) == 1
                && Math.abs(source.calculateFileDistance(target)) == 2);
    }
}
