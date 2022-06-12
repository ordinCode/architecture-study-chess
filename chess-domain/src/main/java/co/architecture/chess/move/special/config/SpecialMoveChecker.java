package co.architecture.chess.move.special.config;

import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.piece.config.Piece;

import java.util.Map;

public interface SpecialMoveChecker {
    boolean isConform(Tile tile, Tile target, Map<Tile, Piece> board);
}
