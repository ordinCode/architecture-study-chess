package co.architecture.consolechess.dto;

import co.architecture.chess.chessboard.tile.File;
import co.architecture.chess.chessboard.tile.Rank;

import java.util.Objects;

public class TileDto {
    private final Rank rank;
    private final File file;

    public TileDto(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
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
        TileDto tileDto = (TileDto) o;
        return getRank() == tileDto.getRank() && getFile() == tileDto.getFile();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRank(), getFile());
    }
}
