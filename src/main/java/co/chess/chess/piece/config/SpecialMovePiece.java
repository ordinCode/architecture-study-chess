package co.chess.chess.piece.config;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.move.config.MovePattern;
import co.chess.chess.piece.PieceType;
import co.chess.chess.piece.Team;

import java.util.Map;

public abstract class SpecialMovePiece extends Piece {
    public SpecialMovePiece(Team team, PieceType type) {
        super(team, type);
    }

    public abstract MovePattern findMovePattern(Tile source, Tile target, Map<Tile, Piece> board);
}
