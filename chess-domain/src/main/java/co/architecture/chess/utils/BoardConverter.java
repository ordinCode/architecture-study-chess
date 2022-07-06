package co.architecture.chess.utils;

import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.chessboard.tile.TileFactory;
import co.architecture.chess.piece.PieceType;
import co.architecture.chess.piece.Team;
import co.architecture.chess.piece.config.Piece;
import co.architecture.common.StringUtil;

import java.util.HashMap;
import java.util.Map;

public class BoardConverter {
    public static Map<String, String> toStringStringMap(Map<Tile, Piece> map) {
        Map<String, String> board = new HashMap<>();
        for (Map.Entry<Tile, Piece> tilePieceEntry : map.entrySet()) {
            Tile tile = tilePieceEntry.getKey();
            Piece piece = tilePieceEntry.getValue();
            board.put(tile.toString(), piece.toSymbol());
        }
        return board;
    }


    public static Map<Tile, Piece> toTilePieceMap(Map<String, String> board1) {
        Map<Tile, Piece> board = new HashMap<>();
        for (Map.Entry<String, String> boardEntry : board1.entrySet()) {
            String pieceType = boardEntry.getValue();
            String tile = boardEntry.getKey();
            board.put(TileFactory.from(tile), PieceType.ofSymbol(pieceType).toObj(getTeamByPieceType(pieceType)));
        }
        return board;
    }

    private static Team getTeamByPieceType(String pieceType) {
        if (StringUtil.isUpperCase(pieceType)) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }
}
