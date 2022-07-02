package co.architecture.chess.chessboard;

import co.architecture.chess.chessboard.tile.Rank;
import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.move.config.MovePattern;
import co.architecture.chess.move.special.config.SpecialMovePattern;
import co.architecture.chess.move.special.pawn.move.PawnMove;
import co.architecture.chess.piece.PieceType;
import co.architecture.chess.piece.Team;
import co.architecture.chess.piece.config.Piece;
import co.architecture.chess.utils.PromotionChecker;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
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

    public Tile getJustNowPawnJumpedTile() {
        return justNowPawnJumpedTile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoard that = (ChessBoard) o;
        return Objects.equals(getBoard(), that.getBoard()) && Objects.equals(justNowPawnJumpedTile, that.justNowPawnJumpedTile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBoard(), justNowPawnJumpedTile);
    }
}
