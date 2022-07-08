package co.architecture.consolechess.adapter.out.persistant.entity;

import co.architecture.chess.GameState;
import co.architecture.chess.piece.Team;
import co.architecture.chess.rule.ChessRuleType;

public class ChessGameJdbcEntity {
    private Long id;
    private String chessRuleType;
    private String gameState;
    private String turn;
    private String boardPayload;
    private String justNowPawnJumpedTilePayload;

    public ChessGameJdbcEntity(Long id, String chessRuleType, String gameState, String turn, String boardPayload, String justNowPawnJumpedTilePayload) {
        this.id = id;
        this.chessRuleType = chessRuleType;
        this.gameState = gameState;
        this.turn = turn;
        this.boardPayload = boardPayload;
        this.justNowPawnJumpedTilePayload = justNowPawnJumpedTilePayload;
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

    public String getBoardPayload() {
        return boardPayload;
    }

    public String getJustNowPawnJumpedTilePayload() {
        return justNowPawnJumpedTilePayload;
    }
}
