package co.architecture.chess.move.config;

import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.piece.config.Piece;

import java.util.Map;

public interface MovePattern {
    void validatePath(Tile source, Map<Tile, Piece> board);
}
