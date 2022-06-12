package co.architecture.chess.move.special.config;

import co.architecture.chess.chessboard.tile.Tile;

public interface SpecialMovePatternCreator {
    SpecialMovePattern create(Tile source, Tile target);
}
