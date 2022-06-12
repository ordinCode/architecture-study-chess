package co.chess.chess.move.config;

import co.chess.chess.chessboard.tile.Tile;

public interface DirectionPredicate {
    boolean test(Tile source,Tile target);
}
