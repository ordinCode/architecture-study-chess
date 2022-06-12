package co.chess.chess.piece;

import co.chess.chess.chessboard.tile.Rank;
import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.exception.move.InvalidMovePatternException;
import co.chess.chess.move.Direction;
import co.chess.chess.move.config.MovePattern;
import co.chess.chess.move.special.pawn.PawnMoveType;
import co.chess.chess.move.special.pawn.enpassant.Enpassant;
import co.chess.chess.move.special.pawn.enpassant.EnpassantChecker;
import co.chess.chess.piece.config.Piece;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Pawn extends Piece {
    public Pawn(Team team) {
        super(team, PieceType.PAWN);
    }

    public MovePattern findMovePattern(Tile source, Tile target, Map<Tile, Piece> board, Tile justNowPawnJumpedTile) {
        if (EnpassantChecker.isConformPawnEnpassantAttack(source, target, board, justNowPawnJumpedTile)) {
            return Enpassant.of(source, target);
        }
        return Arrays.stream(PawnMoveType.values())
                .filter(pawnMoveType -> pawnMoveType.isConform(source, target, board))
                .findAny()
                .map(satisfiedPattern -> satisfiedPattern.toObj(source, target))
                .orElseThrow(InvalidMovePatternException::new);
    }

    public int getCanMoveCount(Tile target) {
        Rank startLine = getStartLine();
        if (target.getRank() == startLine) {
            return 2;
        }
        return 1;
    }

    private Rank getStartLine() {
        if (this.team == Team.WHITE) {
            return Rank.TWO;
        }
        return Rank.SEVEN;
    }

    public Direction getCanForwardDirection() {
        if (this.team == Team.WHITE) {
            return Direction.UP;
        }
        return Direction.DOWN;
    }

    public List<Direction> getCanAttackDirections() {
        if (this.team == Team.WHITE) {
            return Arrays.asList(Direction.UP_LEFT, Direction.UP_RIGHT);
        }
        return Arrays.asList(Direction.DOWN_LEFT, Direction.DOWN_RIGHT);
    }

    @Override
    public void move() {
        super.move();

    }
}
