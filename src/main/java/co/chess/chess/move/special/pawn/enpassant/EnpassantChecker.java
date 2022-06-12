package co.chess.chess.move.special.pawn.enpassant;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.move.special.pawn.generalattack.PawnGeneralAttackChecker;
import co.chess.chess.piece.config.Piece;
import co.chess.chess.utils.TileUtil;

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
