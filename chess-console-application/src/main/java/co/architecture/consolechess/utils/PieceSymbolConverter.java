package co.architecture.consolechess.utils;

import co.architecture.chess.piece.PieceType;
import co.architecture.chess.piece.Team;
import co.architecture.chess.piece.config.Piece;
import co.architecture.consolechess.dto.PieceDto;

import java.util.HashMap;
import java.util.Map;

public class PieceSymbolConverter {
    private static final Map<PieceType, String> mapper = new HashMap<>();

    static {
        mapper.put(PieceType.KING, "k");
        mapper.put(PieceType.QUEEN, "q");
        mapper.put(PieceType.BISHOP, "b");
        mapper.put(PieceType.KNIGHT, "n");
        mapper.put(PieceType.ROOK, "r");
        mapper.put(PieceType.PAWN, "p");
    }

    public static String toSymbol(Piece piece) {
        String pieceInitial = mapper.get(piece.getType());
        if (piece.getTeam() == Team.WHITE) {
            return pieceInitial.toUpperCase();
        }
        return pieceInitial;
    }

    public static String toSymbol(PieceDto pieceDto) {
        String pieceInitial = mapper.get(pieceDto.getPieceType());
        if (pieceDto.getTeam() == Team.WHITE) {
            return pieceInitial.toUpperCase();
        }
        return pieceInitial;
    }

    public static PieceType toPieceType(String symbol) {
        for (Map.Entry<PieceType, String> pieceTypeStringEntry : mapper.entrySet()) {
            if (pieceTypeStringEntry.getValue().equals(symbol)) {
                return pieceTypeStringEntry.getKey();
            }
        }
        throw new IllegalArgumentException("존재하지 않는 PieceType 입니다.");
    }
}
