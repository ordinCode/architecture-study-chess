package co.chess.chess.move.special.config;

import co.chess.chess.chessboard.tile.Tile;

public interface SpecialMovePatternCreator {
    SpecialMovePattern create(Tile source, Tile target);
}
