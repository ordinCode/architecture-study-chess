package co.architecture.chess.move.config;

import co.architecture.chess.chessboard.tile.Tile;

public interface DirectionPredicate {
    boolean test(Tile source,Tile target);
}
