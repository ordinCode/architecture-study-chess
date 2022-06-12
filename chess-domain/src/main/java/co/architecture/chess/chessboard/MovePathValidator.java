package co.architecture.chess.chessboard;

import co.architecture.chess.move.Direction;
import co.architecture.chess.move.general.GeneralMovePattern;
import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.exception.move.ExistPieceOnPathException;
import co.architecture.chess.piece.config.Piece;

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
