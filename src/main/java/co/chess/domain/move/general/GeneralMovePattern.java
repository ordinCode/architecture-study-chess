package co.chess.domain.move.general;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.exception.move.ExistPieceOnPathException;
import co.chess.domain.move.Direction;
import co.chess.domain.move.config.MovePattern;
import co.chess.domain.piece.config.Piece;

import java.util.Map;

public abstract class GeneralMovePattern implements MovePattern {
    public abstract Direction getDirection();

    public abstract int getMoveCount();

    @Override
    public void validatePath(Tile source, Map<Tile, Piece> board) {
        Direction direction = this.getDirection();
        Tile pathChecker = new Tile(source.getRank(), source.getFile());
        for (int i = 1; i < this.getMoveCount(); i++) {
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
