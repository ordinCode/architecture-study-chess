package co.chess.chess.move.special.config;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.move.config.MovePattern;
import co.chess.chess.piece.config.Piece;

import java.util.Map;

public abstract class SpecialMovePattern implements MovePattern {
    public abstract void applySpecialMoveResult(Map<Tile, Piece> board);

    public abstract void validatePath(Tile source, Map<Tile, Piece> board);
}
