package co.chess.chess.piece;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.exception.move.InvalidMovePatternException;
import co.chess.chess.move.config.MovePattern;
import co.chess.chess.move.special.knight.KnightMove;
import co.chess.chess.move.special.knight.KnightMoveChecker;
import co.chess.chess.piece.config.Piece;
import co.chess.chess.piece.config.SpecialMovePiece;

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
