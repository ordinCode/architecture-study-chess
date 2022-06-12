package co.chess.chess.move.config;

import co.chess.chess.chessboard.tile.Tile;

public interface MovePatternCreator {
    MovePattern create(Tile source, Tile target);
}
