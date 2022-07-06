package co.architecture.persistance.jpa.chessgame;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChessGameJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String chessRuleType;
    private String gameState;
    private String turn;
    private String boardPayload;
    private String justNowPawnJumpedTilePayload;

    public ChessGameJpaEntity() {
    }

    public ChessGameJpaEntity(Long id, String chessRuleType, String gameState, String turn, String boardPayload, String justNowPawnJumpedTilePayload) {
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
