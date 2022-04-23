package co.chess.domain.piece;

import co.chess.domain.chessboard.tile.Rank;
import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.exception.move.InvalidMovePatternException;
import co.chess.domain.move.Direction;
import co.chess.domain.move.config.MovePattern;
import co.chess.domain.move.special.pawn.PawnMoveType;
import co.chess.domain.piece.config.Piece;
import co.chess.domain.piece.config.SpecialMovePiece;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Pawn extends SpecialMovePiece {
    public Pawn(Team team) {
        super(team, PieceType.PAWN);
    }

    @Override
    public MovePattern findMovePattern(Tile source, Tile target, Map<Tile, Piece> board) {
        return Arrays.stream(PawnMoveType.values())
                .filter(pawnMoveType -> pawnMoveType.isSatisfy(source, target, board))
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
