package co.chess.consolechess.dto;

import co.chess.domain.piece.config.Piece;
import co.chess.domain.piece.PieceType;
import co.chess.domain.piece.Team;

public class PieceDto {
    private Team team;
    private PieceType pieceType;

    public PieceDto() {
    }

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
