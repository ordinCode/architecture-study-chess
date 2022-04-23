package co.chess.domain.move.special.config;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.move.config.MovePattern;
import co.chess.domain.piece.config.Piece;

import java.util.Map;

public abstract class SpecialMovePattern implements MovePattern {
    public abstract void applySpecialMoveResult(Map<Tile, Piece> board);

    public abstract void validatePath(Tile source, Map<Tile, Piece> board);
}
