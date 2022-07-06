package co.architecture.persistance.jpa.chessgame;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoardConverter {
    public static final String DELIMITER = ",";

    public static Map<String, String> toBoardMap(String boardPayload) {
        Map<String, String> board = new HashMap<>();
        for (String tilePieceItem : boardPayload.split(DELIMITER)) {
            String tile = tilePieceItem.substring(0, 2);
            String piece = tilePieceItem.substring(2, 3);
            board.put(tile, piece);
        }
        return board;
    }

    public static String toBoardPayload(Map<String, String> chessBoard) {
        return chessBoard.entrySet().stream()
                .map(tilePieceEntry -> tilePieceEntry.getKey() + tilePieceEntry.getValue())
                .collect(Collectors.joining(DELIMITER));
    }
}
