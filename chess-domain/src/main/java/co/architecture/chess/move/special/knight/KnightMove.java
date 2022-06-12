package co.architecture.chess.move.special.knight;

import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.move.special.config.SpecialMovePattern;
import co.architecture.chess.piece.config.Piece;

import java.util.Map;

public class KnightMove extends SpecialMovePattern {
    private final Tile source;
    private final Tile target;

    private KnightMove(Tile source, Tile target) {
        this.source = source;
        this.target = target;
    }

    public static KnightMove of(Tile source, Tile target) {
        return new KnightMove(source, target);
    }

    @Override
    public void applySpecialMoveResult(Map<Tile, Piece> board) {
        Piece sourcePiece = board.get(source);
        board.put(target, sourcePiece);
        board.remove(source);
    }

    @Override
    public void validatePath(Tile source, Map<Tile, Piece> board) {
        //do nothing
    }
}
