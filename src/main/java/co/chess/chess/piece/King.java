package co.chess.chess.piece;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.exception.move.InvalidMovePatternException;
import co.chess.chess.move.config.MovePattern;
import co.chess.chess.move.special.king.KingMoveType;
import co.chess.chess.piece.config.Piece;
import co.chess.chess.piece.config.SpecialMovePiece;

import java.util.Arrays;
import java.util.Map;

public class King extends SpecialMovePiece {
    public King(Team team) {
        super(team, PieceType.KING);
    }

    @Override
    public MovePattern findMovePattern(Tile source, Tile target, Map<Tile, Piece> board) {
        return Arrays.stream(KingMoveType.values())
                .filter(kingMoveType -> kingMoveType.isConform(source, target, board))
                .findAny()
                .map(satisfiedPattern -> satisfiedPattern.toObj(source, target))
                .orElseThrow(InvalidMovePatternException::new);
    }
}
