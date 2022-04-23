package co.chess.consolechess.dto;

import co.chess.domain.chessboard.ChessBoard;
import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.piece.config.Piece;

import java.util.HashMap;
import java.util.Map;

public class ChessBoardDto {
    private Map<TileDto, PieceDto> tilePieceMap;

    public ChessBoardDto() {
    }

    public ChessBoardDto(Map<TileDto, PieceDto> tilePieceMap) {
        this.tilePieceMap = tilePieceMap;
    }

    public static ChessBoardDto of(ChessBoard chessBoard) {
        Map<TileDto, PieceDto> tilePieceMap = new HashMap<>();
        Map<Tile, Piece> board = chessBoard.getBoard();
        for (Map.Entry<Tile, Piece> tilePieceEntry : board.entrySet()) {
            Tile tile = tilePieceEntry.getKey();
            Piece piece = tilePieceEntry.getValue();
            tilePieceMap.put(new TileDto(tile.getRank(), tile.getFile()), PieceDto.of(piece));
        }
        return new ChessBoardDto(tilePieceMap);
    }

    public Map<TileDto, PieceDto> getTilePieceMap() {
        return tilePieceMap;
    }
}
