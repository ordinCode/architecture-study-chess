package co.chess.domain.piece.factory;

import co.chess.domain.piece.Bishop;
import co.chess.domain.piece.King;
import co.chess.domain.piece.Knight;
import co.chess.domain.piece.Pawn;
import co.chess.domain.piece.PieceType;
import co.chess.domain.piece.Queen;
import co.chess.domain.piece.Rook;
import co.chess.domain.piece.Team;
import co.chess.domain.piece.config.Piece;

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
