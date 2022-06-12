package co.architecture.chess.move.config;

import co.architecture.chess.chessboard.tile.Tile;

public interface MovePatternCreator {
    MovePattern create(Tile source, Tile target);
}
