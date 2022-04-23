package co.chess.domain.piece;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.exception.move.InvalidMovePatternException;
import co.chess.domain.move.config.MovePattern;
import co.chess.domain.move.special.knight.KnightMove;
import co.chess.domain.move.special.knight.KnightMoveChecker;
import co.chess.domain.piece.config.Piece;
import co.chess.domain.piece.config.SpecialMovePiece;

import java.util.Map;

public class Knight extends SpecialMovePiece {
    public Knight(Team team) {
        super(team, PieceType.KNIGHT);
    }

    @Override
    public MovePattern findMovePattern(Tile source, Tile target, Map<Tile, Piece> board) {
        if (!KnightMoveChecker.isConform(source, target)) {
            throw new InvalidMovePatternException();
        }
        return KnightMove.of(source, target);
    }
}
