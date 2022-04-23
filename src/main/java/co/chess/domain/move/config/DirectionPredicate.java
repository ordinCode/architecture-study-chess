package co.chess.domain.move.config;

import co.chess.domain.chessboard.tile.Tile;

public interface DirectionPredicate {
    boolean test(Tile source,Tile target);
}
