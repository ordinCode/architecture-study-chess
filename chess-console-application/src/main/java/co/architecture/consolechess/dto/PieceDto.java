package co.architecture.consolechess.dto;

import co.architecture.chess.piece.PieceType;
import co.architecture.chess.piece.Team;
import co.architecture.chess.piece.config.Piece;

public class PieceDto {
    private final Team team;
    private final PieceType pieceType;

    public PieceDto(Team team, PieceType pieceType) {
        this.team = team;
        this.pieceType = pieceType;
    }

    public static PieceDto of(Piece piece) {
        return new PieceDto(piece.getTeam(), piece.getType());
    }

    public Team getTeam() {
        return team;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
