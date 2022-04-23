package co.chess.domain.move.special.pawn.enpassant;

import co.chess.domain.PawnJumpRecorder;
import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.move.special.pawn.generalattack.PawnGeneralAttackChecker;
import co.chess.domain.piece.config.Piece;

import java.util.Map;

public class EnpassantChecker {
    public static boolean isConformPawnEnpassantAttack(Tile source, Tile target, Map<Tile, Piece> board) {
        Tile attackedTile = findEnpassantAttackedTile(source, target);

        Boolean isJustNowJumpTile = PawnJumpRecorder.getJustNowPawnJumpedTile()
                .map(lastEnpassant -> lastEnpassant.equals(attackedTile))
                .orElse(false);
        if (!isJustNowJumpTile) {
            return false;
        }
        return PawnGeneralAttackChecker.isConformPawnAttackMovePattern(source, target, board);
    }

    private static Tile findEnpassantAttackedTile(Tile source, Tile target) {
        return new Tile(source.getRank(), target.getFile());
    }
}
