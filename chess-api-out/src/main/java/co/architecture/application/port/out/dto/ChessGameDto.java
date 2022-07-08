package co.architecture.application.port.out.dto;

import java.util.Map;

public class ChessGameDto {
    private Long id;
    private String chessRuleType;
    private String gameState;
    private String turn;
    private Map<String, String> board;
    private String justNowPawnJumpedTile;

    public ChessGameDto(Long id, String chessRuleType, String gameState, String turn, Map<String, String> board, String justNowPawnJumpedTile) {
        this.id = id;
        this.chessRuleType = chessRuleType;
        this.gameState = gameState;
        this.turn = turn;
        this.board = board;
        this.justNowPawnJumpedTile = justNowPawnJumpedTile;
    }

    public Long getId() {
        return id;
    }

    public String getChessRuleType() {
        return chessRuleType;
    }

    public String getGameState() {
        return gameState;
    }

    public String getTurn() {
        return turn;
    }

    public Map<String, String> getBoard() {
        return board;
    }

    public String getJustNowPawnJumpedTile() {
        return justNowPawnJumpedTile;
    }
}
