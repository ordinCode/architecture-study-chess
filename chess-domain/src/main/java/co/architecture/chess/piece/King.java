package co.architecture.chess.piece;

import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.exception.move.InvalidMovePatternException;
import co.architecture.chess.move.config.MovePattern;
import co.architecture.chess.move.special.king.KingMoveType;
import co.architecture.chess.piece.config.Piece;
import co.architecture.chess.piece.config.SpecialMovePiece;

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
