package co.architecture.chess.move.special.king;

import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.move.config.MovePattern;
import co.architecture.chess.move.config.MovePatternCreator;
import co.architecture.chess.piece.config.Piece;
import co.architecture.chess.move.special.config.SpecialMoveChecker;
import co.architecture.chess.move.special.king.casting.Castling;
import co.architecture.chess.move.special.king.casting.CastlingMovePatternChecker;
import co.architecture.chess.move.special.king.move.KingMove;
import co.architecture.chess.move.special.king.move.KingMoveChecker;

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
