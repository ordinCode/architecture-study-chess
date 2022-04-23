package co.chess.domain.chessboard;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.exception.move.ExistPieceOnPathException;
import co.chess.domain.move.Direction;
import co.chess.domain.move.general.GeneralMovePattern;
import co.chess.domain.piece.config.Piece;

import java.util.Map;

public class MovePathValidator {
    public static void validatePath(Tile source, GeneralMovePattern movePattern, Map<Tile, Piece> board) {
        Direction direction = movePattern.getDirection();
        Tile pathChecker = new Tile(source.getRank(), source.getFile());
        for (int i = 1; i < movePattern.getMoveCount(); i++) {
            pathChecker.move(direction);
            checkExistPieceOnPath(pathChecker, board);
        }
    }

    private static void checkExistPieceOnPath(Tile tile, Map<Tile, Piece> board) {
        if (board.get(tile) != null) {
            throw new ExistPieceOnPathException();
        }
    }
}
