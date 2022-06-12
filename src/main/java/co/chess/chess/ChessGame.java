package co.chess.chess;

import co.chess.chess.chessboard.ChessBoard;
import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.piece.PieceType;
import co.chess.chess.exception.move.IllegalMoveTurnException;
import co.chess.chess.piece.Team;
import co.chess.chess.rule.ChessRule;

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
                ChessBoard.init(chessRule.settingInitChessBoard()),
                chessRule
        );
    }

    public void move(Tile source, Tile target) {
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

    public void promotion(PieceType pieceType) {
        chessBoard.promotion(pieceType);
    }

    public boolean isPromotion() {
        return chessBoard.isPromotion();
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
