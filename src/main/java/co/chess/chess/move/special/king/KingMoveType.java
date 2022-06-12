package co.chess.chess.move.special.king;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.move.config.MovePattern;
import co.chess.chess.move.config.MovePatternCreator;
import co.chess.chess.move.special.config.SpecialMoveChecker;
import co.chess.chess.move.special.king.casting.Castling;
import co.chess.chess.move.special.king.casting.CastlingMovePatternChecker;
import co.chess.chess.move.special.king.move.KingMove;
import co.chess.chess.move.special.king.move.KingMoveChecker;
import co.chess.chess.piece.config.Piece;

import java.util.Map;

public enum KingMoveType {
    CASTLING(CastlingMovePatternChecker::isConformCastling, Castling::of),
    MOVE(KingMoveChecker::isConformKingMove, KingMove::of);

    private final SpecialMoveChecker kingMoveChecker;
    private final MovePatternCreator kingMovePatternCreator;

    KingMoveType(SpecialMoveChecker kingMoveChecker, MovePatternCreator kingMovePatternCreator) {
        this.kingMoveChecker = kingMoveChecker;
        this.kingMovePatternCreator = kingMovePatternCreator;
    }

    public boolean isConform(Tile source, Tile target, Map<Tile, Piece> board) {
        return this.kingMoveChecker.isConform(source, target, board);
    }

    public MovePattern toObj(Tile source, Tile target) {
        return this.kingMovePatternCreator.create(source, target);
    }
}
