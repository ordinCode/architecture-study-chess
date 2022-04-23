package co.chess.domain.chessboard;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.exception.MoveException;
import co.chess.domain.exception.move.InvalidMovePatternException;
import co.chess.domain.move.config.MovePattern;
import co.chess.domain.move.general.GeneralMovePattern;
import co.chess.domain.move.general.GeneralMovePatternFinder;
import co.chess.domain.piece.config.GeneralMovePiece;
import co.chess.domain.piece.config.Piece;
import co.chess.domain.piece.config.SpecialMovePiece;

import java.util.Map;
import java.util.Optional;

public class PieceMovePatternMatcher {
    public static MovePattern findMovePattern(Tile source, Tile target, Map<Tile, Piece> board) {
        Piece sourcePiece = Optional.ofNullable(board.get(source))
                .orElseThrow(() -> new MoveException("source 위치에 말이 존재하지 않습니다."));

        Optional.ofNullable(board.get(target))
                .ifPresent(targetPiece -> {
                    if (sourcePiece.getTeam() == targetPiece.getTeam()) {
                        throw new MoveException("같은 팀이 있는 타일로 이동할 수 없습니다.");
                    }
                });

        if (sourcePiece instanceof SpecialMovePiece) {
            SpecialMovePiece specialMovePiece = (SpecialMovePiece) sourcePiece;
            return specialMovePiece.findMovePattern(source, target, board);
        }

        return getGeneralMovePattern(source, target, (GeneralMovePiece) sourcePiece);
    }

    private static MovePattern getGeneralMovePattern(Tile source, Tile target, GeneralMovePiece sourcePiece) {
        GeneralMovePattern movePattern = GeneralMovePatternFinder.find(source, target);
        if (sourcePiece.isAvailablePattern(movePattern)) {
            return movePattern;
        }
        throw new InvalidMovePatternException();
    }
}
