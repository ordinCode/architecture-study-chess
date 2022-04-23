package co.chess.domain.rule;

import co.chess.domain.chessboard.tile.File;
import co.chess.domain.chessboard.tile.Rank;
import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.chessboard.tile.TileFactory;
import co.chess.domain.piece.config.Piece;
import co.chess.domain.piece.PieceType;
import co.chess.domain.piece.Team;
import co.chess.domain.piece.factory.PieceFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultChessRule implements ChessRule {
    @Override
    public Team findWinner(Map<Tile, Piece> board) {
        List<Piece> collect = board.values().stream()
                .filter(piece -> piece.getType() == PieceType.KING)
                .collect(Collectors.toList());
        if (collect.size() >= 2) {
            throw new IllegalArgumentException();
        }
        return collect.get(0).getTeam();
    }

    @Override
    public boolean isGameOver(Map<Tile, Piece> board) {
        List<Piece> kings = board.values().stream()
                .filter(piece -> piece.equalsBy(PieceType.KING))
                .collect(Collectors.toList());
        return kings.size() == 1;
    }

    @Override
    public Team getFirstTurn() {
        return Team.WHITE;
    }

    @Override
    public Map<Tile, Piece> settingInitChessBoard() {
        Map<Tile, Piece> board = new HashMap<>();
        putWhitePieces(board);
        putBlackPieces(board);
        return board;
    }

    private void putWhitePieces(Map<Tile, Piece> board) {
        board.put(TileFactory.from("a1"), PieceFactory.of(Team.WHITE, PieceType.ROOK));
        board.put(TileFactory.from("b1"), PieceFactory.of(Team.WHITE, PieceType.KNIGHT));
        board.put(TileFactory.from("c1"), PieceFactory.of(Team.WHITE, PieceType.BISHOP));
        board.put(TileFactory.from("d1"), PieceFactory.of(Team.WHITE, PieceType.QUEEN));
        board.put(TileFactory.from("e1"), PieceFactory.of(Team.WHITE, PieceType.KING));
        board.put(TileFactory.from("f1"), PieceFactory.of(Team.WHITE, PieceType.BISHOP));
        board.put(TileFactory.from("g1"), PieceFactory.of(Team.WHITE, PieceType.KNIGHT));
        board.put(TileFactory.from("h1"), PieceFactory.of(Team.WHITE, PieceType.ROOK));
        putWhitePawn(board);
    }

    private void putWhitePawn(Map<Tile, Piece> board) {
        for (File file : File.values()) {
            board.put(new Tile(Rank.TWO, file), PieceFactory.of(Team.WHITE, PieceType.PAWN));
        }
    }

    private void putBlackPieces(Map<Tile, Piece> board) {
        board.put(TileFactory.from("a8"), PieceFactory.of(Team.BLACK, PieceType.ROOK));
        board.put(TileFactory.from("b8"), PieceFactory.of(Team.BLACK, PieceType.KNIGHT));
        board.put(TileFactory.from("c8"), PieceFactory.of(Team.BLACK, PieceType.BISHOP));
        board.put(TileFactory.from("d8"), PieceFactory.of(Team.BLACK, PieceType.QUEEN));
        board.put(TileFactory.from("e8"), PieceFactory.of(Team.BLACK, PieceType.KING));
        board.put(TileFactory.from("f8"), PieceFactory.of(Team.BLACK, PieceType.BISHOP));
        board.put(TileFactory.from("g8"), PieceFactory.of(Team.BLACK, PieceType.KNIGHT));
        board.put(TileFactory.from("h8"), PieceFactory.of(Team.BLACK, PieceType.ROOK));
        putBlackPawn(board);
    }

    private void putBlackPawn(Map<Tile, Piece> board) {
        for (File file : File.values()) {
            board.put(new Tile(Rank.SEVEN, file), PieceFactory.of(Team.BLACK, PieceType.PAWN));
        }
    }
}
