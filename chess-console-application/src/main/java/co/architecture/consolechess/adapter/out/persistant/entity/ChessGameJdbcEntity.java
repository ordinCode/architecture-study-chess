package co.architecture.consolechess.adapter.out.persistant.entity;

import co.architecture.chess.GameState;
import co.architecture.chess.piece.Team;
import co.architecture.chess.rule.ChessRuleType;

public class ChessGameJdbcEntity {
    private Long id;
    private ChessRuleType chessRuleType;
    private GameState gameState;
    private Team turn;
    private String boardPayload;
    private String justNowPawnJumpedTilePayload;

    public ChessGameJdbcEntity(Long id, ChessRuleType chessRuleType, GameState gameState, Team turn, String boardPayload, String justNowPawnJumpedTilePayload) {
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

    public ChessRuleType getChessRuleType() {
        return chessRuleType;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Team getTurn() {
        return turn;
    }

    public String getBoardPayload() {
        return boardPayload;
    }

    public String getJustNowPawnJumpedTilePayload() {
        return justNowPawnJumpedTilePayload;
    }
}
