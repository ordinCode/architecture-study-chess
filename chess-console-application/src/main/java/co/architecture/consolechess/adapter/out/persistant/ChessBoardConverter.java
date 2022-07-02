package co.architecture.consolechess.adapter.out.persistant;

import co.architecture.chess.chessboard.ChessBoard;
import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.chessboard.tile.TileFactory;
import co.architecture.chess.piece.PieceType;
import co.architecture.chess.piece.Team;
import co.architecture.chess.piece.config.Piece;
import co.architecture.common.StringUtil;
import co.architecture.consolechess.utils.PieceSymbolConverter;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoardConverter {
    public static final String DELIMITER = ",";

    public static ChessBoard toChessBoard(String boardPayload, String justNowPawnJumpedTilePayload) {
        Map<Tile, Piece> board = new HashMap<>();
        for (String tilePieceItem : boardPayload.split(DELIMITER)) {
            Tile tile = getTile(tilePieceItem);
            Piece piece = getPiece(tilePieceItem);
            board.put(tile, piece);
        }
        Gson gson = new Gson();
        Tile justNowPawnJumpedTile = gson.fromJson(justNowPawnJumpedTilePayload, Tile.class);
        return new ChessBoard(board, justNowPawnJumpedTile);
    }

    private static Piece getPiece(String tilePieceItem) {
        String pieceType = tilePieceItem.substring(2, 3);
        return PieceType.ofSymbol(pieceType).toObj(getTeamByPieceType(pieceType));
    }

    private static Tile getTile(String tilePieceItem) {
        String tile = tilePieceItem.substring(0, 2);
        return TileFactory.from(tile);
    }

    private static Team getTeamByPieceType(String pieceType) {
        if (StringUtil.isUpperCase(pieceType)) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }

    public static String toBoardPayload(ChessBoard chessBoard) {
        return chessBoard.getBoard().entrySet().stream()
                .map(tilePieceEntry -> {
                    Tile tile = tilePieceEntry.getKey();
                    Piece piece = tilePieceEntry.getValue();
                    String symbol = PieceSymbolConverter.toSymbol(piece);
                    return tile.toString() + symbol;
                })
                .collect(Collectors.joining(DELIMITER));
    }
}
