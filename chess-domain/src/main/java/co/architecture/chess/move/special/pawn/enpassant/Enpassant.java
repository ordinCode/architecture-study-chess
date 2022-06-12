package co.architecture.chess.move.special.pawn.enpassant;

import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.move.special.config.SpecialMovePattern;
import co.architecture.chess.piece.config.Piece;
import co.architecture.chess.utils.TileUtil;

import java.util.Map;

public class Enpassant extends SpecialMovePattern {
    private final Tile source;
    private final Tile target;
    private final Tile attacked;

    public Enpassant(Tile source, Tile target, Tile attacked) {
        this.source = source;
        this.target = target;
        this.attacked = attacked;
    }

    public static Enpassant of(Tile source, Tile target) {
        return new Enpassant(source, target, TileUtil.findEnpassantAttackedTile(source, target));
    }

    @Override
    public void applySpecialMoveResult(Map<Tile, Piece> board) {
        Piece sourcePiece = board.get(source);
        sourcePiece.move();
        board.put(target, sourcePiece);
        board.remove(attacked);
        board.remove(source);
    }

    @Override
    public void validatePath(Tile source, Map<Tile, Piece> board) {
        //do nothing
    }
}
