package co.chess.chess.move.config;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.piece.config.Piece;

import java.util.Map;

public interface MovePattern {
    void validatePath(Tile source, Map<Tile, Piece> board);
}
