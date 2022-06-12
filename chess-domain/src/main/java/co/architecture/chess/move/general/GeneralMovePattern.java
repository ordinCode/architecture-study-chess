package co.architecture.chess.move.general;

import co.architecture.chess.move.Direction;
import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.exception.move.ExistPieceOnPathException;
import co.architecture.chess.move.config.MovePattern;
import co.architecture.chess.piece.config.Piece;

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
