package co.chess.domain.piece.config;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.move.config.MovePattern;
import co.chess.domain.piece.PieceType;
import co.chess.domain.piece.Team;

import java.util.Map;

public abstract class SpecialMovePiece extends Piece {
    public SpecialMovePiece(Team team, PieceType type) {
        super(team, type);
    }

    public abstract MovePattern findMovePattern(Tile source, Tile target, Map<Tile, Piece> board);
}
