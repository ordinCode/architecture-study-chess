package co.chess.domain.move.special.king.casting;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.chessboard.tile.TileFactory;
import co.chess.domain.exception.move.ExistPieceOnPathException;
import co.chess.domain.piece.config.Piece;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum CastlingType {
    WHITE_RIGHT(TileFactory.from("e1"),
            TileFactory.from("g1"),
            TileFactory.from("h1"),
            TileFactory.from("f1"),
            Arrays.asList(
                    TileFactory.from("f1"),
                    TileFactory.from("g1")
            )),
    WHITE_LEFT(TileFactory.from("e1"),
            TileFactory.from("c1"),
            TileFactory.from("a1"),
            TileFactory.from("d1"),
            Arrays.asList(
                    TileFactory.from("b1"),
                    TileFactory.from("c1"),
                    TileFactory.from("d1")
            )),
    BLACK_RIGHT(TileFactory.from("e8"),
            TileFactory.from("g8"),
            TileFactory.from("h8"),
            TileFactory.from("f8"),
            Arrays.asList(
                    TileFactory.from("f8"),
                    TileFactory.from("g8")
            )),
    BLACK_LEFT(TileFactory.from("e8"),
            TileFactory.from("c8"),
            TileFactory.from("a8"),
            TileFactory.from("d8"),
            Arrays.asList(
                    TileFactory.from("b8"),
                    TileFactory.from("c8"),
                    TileFactory.from("d8")
            ));

    private final Tile kingSource;
    private final Tile kingDestination;
    private final Tile rookSource;
    private final Tile rookDestination;
    private final List<Tile> castlingPath;

    CastlingType(Tile kingSource, Tile kingDestination, Tile rookSource, Tile rookDestination, List<Tile> castlingPath) {
        this.kingSource = kingSource;
        this.kingDestination = kingDestination;
        this.rookSource = rookSource;
        this.rookDestination = rookDestination;
        this.castlingPath = castlingPath;
    }

    public static CastlingType of(Tile source, Tile target) {
        return Arrays.stream(CastlingType.values())
                .filter(castlingType -> source.equals(castlingType.kingSource) && target.equals(castlingType.kingDestination))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static boolean anyMatch(Tile source, Tile target) {
        return Arrays.stream(CastlingType.values())
                .anyMatch(castlingType -> source.equals(castlingType.kingSource) && target.equals(castlingType.kingDestination));
    }

    public void applySpecialMoveResult(Map<Tile, Piece> board) {
        Piece king = board.get(this.kingSource);
        king.move();
        board.put(this.kingDestination, king);
        board.remove(kingSource);

        Piece rook = board.get(this.rookSource);
        rook.move();
        board.put(this.rookDestination, rook);
        board.remove(rookSource);
    }

    public void validatePath(Map<Tile, Piece> board) {
        boolean existPieceOnPath = this.castlingPath.stream()
                .anyMatch(path -> board.get(path) != null);
        if (existPieceOnPath) {
            throw new ExistPieceOnPathException();
        }
    }

    public Tile getKingSource() {
        return kingSource;
    }

    public Tile getRookSource() {
        return rookSource;
    }
}
