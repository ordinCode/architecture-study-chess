package co.architecture.chess.chessboard;

import co.architecture.chess.piece.Pawn;
import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.exception.move.InvalidMovePatternException;
import co.architecture.chess.exception.move.MoveException;
import co.architecture.chess.move.config.MovePattern;
import co.architecture.chess.move.general.GeneralMovePattern;
import co.architecture.chess.move.general.GeneralMovePatternFinder;
import co.architecture.chess.piece.config.GeneralMovePiece;
import co.architecture.chess.piece.config.Piece;
import co.architecture.chess.piece.config.SpecialMovePiece;

import java.util.Map;
import java.util.Optional;

public class PieceMovePatternMatcher {
    public static MovePattern findMovePattern(Tile source, Tile target, Map<Tile, Piece> board, Tile justNowPawnJumpedTile) {
        Piece sourcePiece = Optional.ofNullable(board.get(source))
                .orElseThrow(() -> new MoveException("source 위치에 말이 존재하지 않습니다."));

        Optional.ofNullable(board.get(target))
                .ifPresent(targetPiece -> {
                    if (sourcePiece.getTeam() == targetPiece.getTeam()) {
                        throw new MoveException("같은 팀이 있는 타일로 이동할 수 없습니다.");
                    }
                });

        if (sourcePiece instanceof Pawn) {
            Pawn pawn = (Pawn) sourcePiece;
            return pawn.findMovePattern(source, target, board, justNowPawnJumpedTile);
        }

        if (sourcePiece instanceof SpecialMovePiece) {
            SpecialMovePiece specialMovePiece = (SpecialMovePiece) sourcePiece;
            return specialMovePiece.findMovePattern(source, target, board);
        }

        return findGeneralMovePattern(source, target, (GeneralMovePiece) sourcePiece);
    }

    private static MovePattern findGeneralMovePattern(Tile source, Tile target, GeneralMovePiece sourcePiece) {
        GeneralMovePattern movePattern = GeneralMovePatternFinder.find(source, target);
        if (sourcePiece.isAvailablePattern(movePattern)) {
            return movePattern;
        }
        throw new InvalidMovePatternException();
    }
}
