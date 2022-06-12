package co.chess.chess.move.special.pawn.generalattack;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.move.general.CrossMove;
import co.chess.chess.move.general.GeneralMovePattern;
import co.chess.chess.move.general.GeneralMovePatternFinder;
import co.chess.chess.piece.Pawn;
import co.chess.chess.piece.config.Piece;

import java.util.Map;

public class PawnGeneralAttackChecker {
    public static boolean isConformPawnGeneralAttack(Tile source, Tile target, Map<Tile, Piece> board) {
        if (notExistEnemy(source, target, board)) {
            return false;
        }
        return isConformPawnAttackMovePattern(source, target, board);
    }

    public static boolean isConformPawnAttackMovePattern(Tile source, Tile target, Map<Tile, Piece> board) {
        GeneralMovePattern movePattern = GeneralMovePatternFinder.find(source, target);
        if (!(movePattern instanceof CrossMove && movePattern.getMoveCount() == 1)) {
            return false;
        }

        Pawn sourcePawn = (Pawn) board.get(source);
        return sourcePawn.getCanAttackDirections().contains(movePattern.getDirection());
    }


    private static boolean notExistEnemy(Tile source, Tile target, Map<Tile, Piece> board) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        return targetPiece == null
                || sourcePiece.getTeam() == targetPiece.getTeam();
    }
}
