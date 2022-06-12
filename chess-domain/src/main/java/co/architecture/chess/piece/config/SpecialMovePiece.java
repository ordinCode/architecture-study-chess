package co.architecture.chess.piece.config;

import co.architecture.chess.move.config.MovePattern;
import co.architecture.chess.piece.PieceType;
import co.architecture.chess.piece.Team;
import co.architecture.chess.chessboard.tile.Tile;

import java.util.Map;

public abstract class SpecialMovePiece extends Piece {
    public SpecialMovePiece(Team team, PieceType type) {
        super(team, type);
    }

    public abstract MovePattern findMovePattern(Tile source, Tile target, Map<Tile, Piece> board);
}
