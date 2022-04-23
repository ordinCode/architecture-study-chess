package co.chess.consolechess.dto;

import co.chess.domain.chessboard.tile.File;
import co.chess.domain.chessboard.tile.Rank;

import java.util.Objects;

public class TileDto {
    private Rank rank;
    private File file;

    public TileDto() {
    }

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
