package co.chess.chess.move.special.pawn;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.move.config.MovePattern;
import co.chess.chess.move.config.MovePatternCreator;
import co.chess.chess.move.special.config.SpecialMoveChecker;
import co.chess.chess.move.special.pawn.generalattack.PawnGeneralAttack;
import co.chess.chess.move.special.pawn.generalattack.PawnGeneralAttackChecker;
import co.chess.chess.move.special.pawn.move.PawnMove;
import co.chess.chess.move.special.pawn.move.PawnMoveChecker;
import co.chess.chess.piece.config.Piece;

import java.util.Map;

public enum PawnMoveType {
    MOVE(PawnMoveChecker::isConformPawnMove, PawnMove::of),
    ATTACK(PawnGeneralAttackChecker::isConformPawnGeneralAttack, PawnGeneralAttack::of);

    private final SpecialMoveChecker pawnMoveRuleChecker;
    private final MovePatternCreator movePatternCreator;

    PawnMoveType(SpecialMoveChecker pawnMoveRuleChecker, MovePatternCreator movePatternCreator) {
        this.pawnMoveRuleChecker = pawnMoveRuleChecker;
        this.movePatternCreator = movePatternCreator;
    }

    public boolean isConform(Tile source, Tile target, Map<Tile, Piece> board) {
        return this.pawnMoveRuleChecker.isConform(source, target, board);
    }

    public MovePattern toObj(Tile source, Tile target) {
        return this.movePatternCreator.create(source, target);
    }
}
