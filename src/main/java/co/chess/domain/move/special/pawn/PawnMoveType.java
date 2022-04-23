package co.chess.domain.move.special.pawn;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.move.config.MovePattern;
import co.chess.domain.move.config.MovePatternCreator;
import co.chess.domain.move.special.config.SpecialMoveChecker;
import co.chess.domain.move.special.pawn.enpassant.Enpassant;
import co.chess.domain.move.special.pawn.enpassant.EnpassantChecker;
import co.chess.domain.move.special.pawn.generalattack.PawnGeneralAttack;
import co.chess.domain.move.special.pawn.generalattack.PawnGeneralAttackChecker;
import co.chess.domain.move.special.pawn.move.PawnMove;
import co.chess.domain.move.special.pawn.move.PawnMoveChecker;
import co.chess.domain.piece.config.Piece;

import java.util.Map;

public enum PawnMoveType {
    MOVE(PawnMoveChecker::isConformPawnMove, PawnMove::of),
    ATTACK(PawnGeneralAttackChecker::isConformPawnGeneralAttack, PawnGeneralAttack::of),
    ENPASSANT(EnpassantChecker::isConformPawnEnpassantAttack, Enpassant::of);

    private final SpecialMoveChecker pawnMoveRuleChecker;
    private final MovePatternCreator movePatternCreator;

    PawnMoveType(SpecialMoveChecker pawnMoveRuleChecker, MovePatternCreator movePatternCreator) {
        this.pawnMoveRuleChecker = pawnMoveRuleChecker;
        this.movePatternCreator = movePatternCreator;
    }

    public boolean isSatisfy(Tile source, Tile target, Map<Tile, Piece> board) {
        return this.pawnMoveRuleChecker.isConform(source, target, board);
    }

    public MovePattern toObj(Tile source, Tile target) {
        return this.movePatternCreator.create(source, target);
    }
}
