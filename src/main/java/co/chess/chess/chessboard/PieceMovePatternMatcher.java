package co.chess.chess.chessboard;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.exception.move.InvalidMovePatternException;
import co.chess.chess.exception.move.MoveException;
import co.chess.chess.move.config.MovePattern;
import co.chess.chess.move.general.GeneralMovePattern;
import co.chess.chess.move.general.GeneralMovePatternFinder;
import co.chess.chess.piece.Pawn;
import co.chess.chess.piece.config.GeneralMovePiece;
import co.chess.chess.piece.config.Piece;
import co.chess.chess.piece.config.SpecialMovePiece;

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
