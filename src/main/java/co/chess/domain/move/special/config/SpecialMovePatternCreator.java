package co.chess.domain.move.special.config;

import co.chess.domain.chessboard.tile.Tile;

public interface SpecialMovePatternCreator {
    SpecialMovePattern create(Tile source, Tile target);
}
