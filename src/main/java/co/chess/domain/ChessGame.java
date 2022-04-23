package co.chess.domain;

import co.chess.domain.chessboard.ChessBoard;
import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.chessboard.tile.TileFactory;
import co.chess.domain.dto.MoveRequest;
import co.chess.domain.dto.PromotionRequest;
import co.chess.domain.exception.move.IllegalMoveTurnException;
import co.chess.domain.piece.Team;
import co.chess.domain.rule.ChessRule;

public class ChessGame {
    private final ChessBoard chessBoard;
    private final ChessRule chessRule;
    private GameState gameState;
    private Team turn;

    private ChessGame(GameState gameState, Team turn, ChessBoard chessBoard, ChessRule chessRule) {
        this.gameState = gameState;
        this.turn = turn;
        this.chessBoard = chessBoard;
        this.chessRule = chessRule;
    }

    public static ChessGame init(ChessRule chessRule) {
        return new ChessGame(
                GameState.RUNNING,
                chessRule.getFirstTurn(),
                new ChessBoard(chessRule.settingInitChessBoard()),
                chessRule
        );
    }

    public void move(MoveRequest moveRequest) {
        Tile source = TileFactory.from(moveRequest.getSource());
        Tile target = TileFactory.from(moveRequest.getTarget());

        validateTurn(source);
        chessBoard.move(source, target);

        if (chessRule.isGameOver(chessBoard.getBoard())) {
            gameState = GameState.END;
            return;
        }

        changeTurn();
    }

    private void validateTurn(Tile source) {
        chessBoard.getPieceByTile(source).ifPresent(piece -> {
            if (piece.getTeam() != turn) {
                throw new IllegalMoveTurnException(turn);
            }
        });
    }

    public boolean isEnd() {
        return this.gameState == GameState.END;
    }

    public Team findWinner() {
        if (gameState != GameState.END) {
            return null;
        }
        return chessRule.findWinner(chessBoard.getBoard());
    }

    public void changeTurn() {
        if (turn == Team.BLACK) {
            turn = Team.WHITE;
            return;
        }
        turn = Team.BLACK;
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

    public boolean isPromotion() {
        return chessBoard.isPromotion();
    }

    public void promotion(PromotionRequest promotionRequest) {
        chessBoard.promotion(promotionRequest.getPieceType());
    }
}
