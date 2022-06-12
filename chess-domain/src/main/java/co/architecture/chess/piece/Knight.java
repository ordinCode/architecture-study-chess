package co.architecture.chess.piece;

import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.exception.move.InvalidMovePatternException;
import co.architecture.chess.move.config.MovePattern;
import co.architecture.chess.move.special.knight.KnightMove;
import co.architecture.chess.move.special.knight.KnightMoveChecker;
import co.architecture.chess.piece.config.Piece;
import co.architecture.chess.piece.config.SpecialMovePiece;

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
