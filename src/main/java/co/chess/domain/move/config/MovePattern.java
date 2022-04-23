package co.chess.domain.move.config;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.piece.config.Piece;

import java.util.Map;

public interface MovePattern {
    void validatePath(Tile source, Map<Tile, Piece> board);
}
