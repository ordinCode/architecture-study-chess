package co.architecture.chess.move.special.config;

import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.move.config.MovePattern;
import co.architecture.chess.piece.config.Piece;

import java.util.Map;

public abstract class SpecialMovePattern implements MovePattern {
    public abstract void applySpecialMoveResult(Map<Tile, Piece> board);

    public abstract void validatePath(Tile source, Map<Tile, Piece> board);
}
