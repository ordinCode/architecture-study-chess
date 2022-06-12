package co.architecture.chess;

import co.architecture.chess.chessboard.ChessBoard;
import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.exception.move.IllegalMoveTurnException;
import co.architecture.chess.exception.move.MoveException;
import co.architecture.chess.piece.PieceType;
import co.architecture.chess.piece.Team;
import co.architecture.chess.rule.ChessRule;

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
        validate(source);
        chessBoard.move(source, target);

        if (chessRule.isGameOver(chessBoard.getBoard())) {
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
        return chessRule.findWinner(chessBoard.getBoard()).name();
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
