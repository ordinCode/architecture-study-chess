package co.chess.domain.chessboard;

import co.chess.domain.chessboard.tile.Rank;
import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.move.config.MovePattern;
import co.chess.domain.move.special.config.SpecialMovePattern;
import co.chess.domain.move.special.pawn.move.PawnMove;
import co.chess.domain.piece.PieceType;
import co.chess.domain.piece.Team;
import co.chess.domain.piece.config.Piece;
import co.chess.domain.utils.PromotionChecker;

import java.util.Map;
import java.util.Optional;

public class ChessBoard {
    private final Map<Tile, Piece> board;
    private Tile justNowPawnJumpedTile;

    public ChessBoard(Map<Tile, Piece> board, Tile justNowPawnJumpedTile) {
        this.board = board;
        this.justNowPawnJumpedTile = justNowPawnJumpedTile;
    }

    public static ChessBoard init(Map<Tile, Piece> board) {
        return new ChessBoard(board, null);
    }

    public void move(Tile source, Tile target) {
        MovePattern movePattern = PieceMovePatternMatcher.findMovePattern(source, target, board, justNowPawnJumpedTile);
        movePattern.validatePath(source, board);
        movePiece(source, target, movePattern);
    }

    private void movePiece(Tile source, Tile target, MovePattern movePattern) {
        recordPawnJumpedTileLatest(target, movePattern);

        if (movePattern instanceof SpecialMovePattern) {
            SpecialMovePattern specialMovePattern = (SpecialMovePattern) movePattern;
            specialMovePattern.applySpecialMoveResult(board);
            return;
        }

        Piece piece = board.get(source);
        piece.move();
        board.put(target, piece);
        board.remove(source);
    }

    private void recordPawnJumpedTileLatest(Tile target, MovePattern movePattern) {
        if (movePattern instanceof PawnMove) {
            PawnMove pawnForwardMove = (PawnMove) movePattern;
            if (pawnForwardMove.getMoveCount() == 2) {
                this.justNowPawnJumpedTile = target;
                return;
            }
            this.justNowPawnJumpedTile = null;
        }
    }

    public boolean isPromotion() {
        return PromotionChecker.isPromotion(board);
    }

    public void promotion(PieceType pieceType) {
        PromotionChecker.findPromotionTile(board).ifPresent(tile -> {
            Team team = getTeamByPromotionTile(tile);
            board.put(tile, pieceType.toObj(team));
        });
    }

    public Optional<Piece> getPieceByTile(Tile tile) {
        return Optional.ofNullable(board.get(tile));
    }

    private Team getTeamByPromotionTile(Tile tile) {
        Team team = Team.WHITE;
        if (tile.getRank() == Rank.ONE) {
            team = Team.BLACK;
        }
        return team;
    }

    public Map<Tile, Piece> getBoard() {
        return board;
    }
}
