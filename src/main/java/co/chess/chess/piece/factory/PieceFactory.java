package co.chess.chess.piece.factory;

import co.chess.chess.piece.Bishop;
import co.chess.chess.piece.King;
import co.chess.chess.piece.Knight;
import co.chess.chess.piece.Pawn;
import co.chess.chess.piece.PieceType;
import co.chess.chess.piece.Queen;
import co.chess.chess.piece.Rook;
import co.chess.chess.piece.Team;
import co.chess.chess.piece.config.Piece;

import java.util.HashSet;
import java.util.Set;

public class PieceFactory {
    private final static Set<Piece> pieceBundle = new HashSet<>();

    static {
        addPiecesByTeam(Team.BLACK);
        addPiecesByTeam(Team.WHITE);
    }

    private static void addPiecesByTeam(Team team) {
        pieceBundle.add(new King(team));
        pieceBundle.add(new Queen(team));
        pieceBundle.add(new Bishop(team));
        pieceBundle.add(new Knight(team));
        pieceBundle.add(new Rook(team));
        pieceBundle.add(new Pawn(team));
    }

    public static Piece of(Team team, PieceType pieceType) {
        return pieceBundle.stream()
                .filter(piece -> piece.equalsBy(team, pieceType))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
