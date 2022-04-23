package co.chess.domain.piece.config;

import co.chess.domain.piece.PieceType;
import co.chess.domain.piece.Team;

public abstract class Piece {
    protected final Team team;
    protected final PieceType type;
    protected boolean haveNotEverMoved = true;

    public Piece(Team team, PieceType type) {
        this.team = team;
        this.type = type;
    }

    public void move() {
        this.haveNotEverMoved = false;
    }

    public boolean isHaveNotEverMoved() {
        return haveNotEverMoved;
    }

    public Team getTeam() {
        return team;
    }

    public PieceType getType() {
        return type;
    }

    public boolean equalsBy(Team team, PieceType pieceType) {
        return this.team == team && this.type == pieceType;
    }

    public boolean equalsBy(PieceType pieceType) {
        return this.type == pieceType;
    }
}
