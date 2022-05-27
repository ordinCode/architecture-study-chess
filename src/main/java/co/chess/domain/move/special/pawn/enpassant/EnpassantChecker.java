package co.chess.domain.move.special.pawn.enpassant;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.move.special.pawn.generalattack.PawnGeneralAttackChecker;
import co.chess.domain.piece.config.Piece;
import co.chess.domain.utils.TileUtil;

import java.util.Map;
import java.util.Optional;

public class EnpassantChecker {
    public static boolean isConformPawnEnpassantAttack(Tile source, Tile target, Map<Tile, Piece> board, Tile justNowPawnJumpedTile) {
        Tile attackedTile = TileUtil.findEnpassantAttackedTile(source, target);

        Boolean isJustNowJumpTile = Optional.ofNullable(justNowPawnJumpedTile)
                .map(lastEnpassant -> lastEnpassant.equals(attackedTile))
                .orElse(false);
        if (!isJustNowJumpTile) {
            return false;
        }
        return PawnGeneralAttackChecker.isConformPawnAttackMovePattern(source, target, board);
    }
}
