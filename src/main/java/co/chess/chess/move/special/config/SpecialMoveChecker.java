package co.chess.chess.move.special.config;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.piece.config.Piece;

import java.util.Map;

public interface SpecialMoveChecker {
    boolean isConform(Tile tile, Tile target, Map<Tile, Piece> board);
}
