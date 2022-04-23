package co.chess.domain.move.config;

import co.chess.domain.chessboard.tile.Tile;

public interface MovePatternCreator {
    MovePattern create(Tile source, Tile target);
}
