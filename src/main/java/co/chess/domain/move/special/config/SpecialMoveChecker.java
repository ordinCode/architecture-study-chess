package co.chess.domain.move.special.config;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.piece.config.Piece;

import java.util.Map;

public interface SpecialMoveChecker {
    boolean isConform(Tile tile, Tile target, Map<Tile, Piece> board);
}
