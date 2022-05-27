package co.chess.domain.utils;

import co.chess.domain.chessboard.tile.File;
import co.chess.domain.chessboard.tile.Rank;
import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.piece.PieceType;
import co.chess.domain.piece.Team;
import co.chess.domain.piece.config.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PromotionChecker {
    private static final List<Tile> whitePromotionLine = new ArrayList<>();
    private static final List<Tile> blackPromotionLine = new ArrayList<>();

    static {
        for (File file : File.values()) {
            whitePromotionLine.add(new Tile(Rank.EIGHT, file));
            blackPromotionLine.add(new Tile(Rank.ONE, file));
        }
    }

    public static boolean isPromotion(Map<Tile, Piece> board) {
        return existWhitePromotionPawn(board) || existBlackPromotionPawn(board);
    }

    public static Optional<Tile> findPromotionTile(Map<Tile, Piece> board) {
        for (Tile tile : whitePromotionLine) {
            if (canPromotion(board, tile, Team.WHITE)) {
                return Optional.of(tile);
            }
        }
        for (Tile tile : blackPromotionLine) {
            if (canPromotion(board, tile, Team.BLACK)) {
                return Optional.of(tile);
            }
        }
        return Optional.empty();
    }

    private static boolean existWhitePromotionPawn(Map<Tile, Piece> board) {
        return whitePromotionLine.stream()
                .anyMatch(tile -> canPromotion(board, tile, Team.WHITE));
    }

    private static boolean canPromotion(Map<Tile, Piece> board, Tile tile, Team tem) {
        Piece piece = board.get(tile);
        if (piece == null) {
            return false;
        }
        return piece.getType() == PieceType.PAWN && piece.getTeam() == tem;
    }

    private static boolean existBlackPromotionPawn(Map<Tile, Piece> board) {
        return blackPromotionLine.stream()
                .anyMatch(tile -> canPromotion(board, tile, Team.BLACK));
    }
}
