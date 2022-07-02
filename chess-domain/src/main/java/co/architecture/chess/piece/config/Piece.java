package co.architecture.chess.piece.config;

import co.architecture.chess.piece.PieceType;
import co.architecture.chess.piece.Team;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return isHaveNotEverMoved() == piece.isHaveNotEverMoved() && getTeam() == piece.getTeam() && getType() == piece.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTeam(), getType(), isHaveNotEverMoved());
    }
}
