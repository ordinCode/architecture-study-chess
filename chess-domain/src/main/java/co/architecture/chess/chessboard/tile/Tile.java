package co.architecture.chess.chessboard.tile;

import co.architecture.chess.move.Direction;

import java.util.Objects;

public class Tile {
    private Rank rank;
    private File file;

    public Tile(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public int calculateRankDistance(Tile target) {
        return this.rank.calculateDistance(target.getRank());
    }

    public int calculateFileDistance(Tile target) {
        return this.file.calculateDistance(target.getFile());
    }

    public boolean isSameRank(Tile target) {
        return this.rank == target.getRank();
    }

    public boolean isSameFile(Tile target) {
        return this.file == target.getFile();
    }

    public void move(Direction direction) {
        int x = direction.getXDegree();
        int y = direction.getYDegree();
        this.file = File.of(this.file.getNumber() + x);
        this.rank = Rank.of(this.rank.getNumber() + y);
    }

    public Rank getRank() {
        return rank;
    }

    public File getFile() {
        return file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return getRank() == tile.getRank() && getFile() == tile.getFile();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRank(), getFile());
    }
}
