package co.architecture.chess;

import co.architecture.chess.chessboard.ChessBoard;
import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.exception.move.IllegalMoveTurnException;
import co.architecture.chess.exception.move.MoveException;
import co.architecture.chess.piece.PieceType;
import co.architecture.chess.piece.Team;
import co.architecture.chess.rule.ChessRule;
import co.architecture.chess.rule.ChessRuleType;

public class ChessGame {
    private Long id;
    private ChessBoard chessBoard;
    private ChessRuleType chessRuleType;
    private GameState gameState;
    private Team turn;

    public ChessGame(Long id, GameState gameState, Team turn, ChessBoard chessBoard, ChessRuleType chessRuleType) {
        this.id = id;
        this.gameState = gameState;
        this.turn = turn;
        this.chessBoard = chessBoard;
        this.chessRuleType = chessRuleType;
    }

    public static ChessGame init(ChessRuleType chessRule) {
        return new ChessGame(
                null,
                GameState.RUNNING,
                chessRule.getChessRule().getFirstTurn(),
                ChessBoard.init(chessRule.getChessRule().settingInitChessBoard()),
                chessRule
        );
    }

    public void move(Tile source, Tile target) {
        validate(source);
        chessBoard.move(source, target);

        if (chessRuleType.getChessRule().isGameOver(chessBoard.getBoard())) {
            gameState = GameState.END;
            return;
        }

        changeTurn();
    }

    private void validate(Tile source) {
        chessBoard.getPieceByTile(source).ifPresent(piece -> {
            if (piece.getTeam() != turn) {
                throw new IllegalMoveTurnException(turn);
            }
        });

        if (chessBoard.isPromotion()) {
            throw new MoveException("프로모션 먼저 진행해주세요.");
        }
    }

    public boolean isEnd() {
        return this.gameState == GameState.END;
    }

    public String getWinner() {
        if (gameState != GameState.END) {
            return null;
        }
        return chessRuleType.getChessRule().findWinner(chessBoard.getBoard()).name();
    }

    public void changeTurn() {
        if (turn == Team.BLACK) {
            turn = Team.WHITE;
            return;
        }
        turn = Team.BLACK;
    }

    public void updateChessGame(ChessGame chessGame) {
        this.id = chessGame.getId();
        this.chessBoard = chessGame.getChessBoard();
        this.chessRuleType = chessGame.getChessRuleType();
        this.gameState = chessGame.getGameState();
        this.turn = chessGame.getTurn();
    }

    public void promotion(PieceType pieceType) {
        chessBoard.promotion(pieceType);
    }

    public boolean isPromotion() {
        return chessBoard.isPromotion();
    }

    public Long getId() {
        return id;
    }

    public ChessRuleType getChessRuleType() {
        return this.chessRuleType;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Team getTurn() {
        return turn;
    }
}
