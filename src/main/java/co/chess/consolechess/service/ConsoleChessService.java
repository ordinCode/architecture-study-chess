package co.chess.consolechess.service;

import co.chess.chess.chessboard.tile.TileFactory;
import co.chess.consolechess.gamefacory.ConsoleChessGameFactory;
import co.chess.consolechess.port.in.ChessMoveUseCase;
import co.chess.consolechess.port.in.MovePieceCommand;
import co.chess.chess.ChessGame;
import co.chess.consolechess.port.in.PromotionCommand;

public class ConsoleChessService implements ChessMoveUseCase {
    @Override
    public void movePiece(MovePieceCommand movePieceCommand) {
        ChessGame chessGame = ConsoleChessGameFactory.getInstance();
        chessGame.move(TileFactory.from(movePieceCommand.getSource()), TileFactory.from(movePieceCommand.getTarget()));
    }

    @Override
    public void promotion(PromotionCommand promotionCommand) {
        ChessGame chessGame = ConsoleChessGameFactory.getInstance();
        chessGame.promotion(promotionCommand.getPieceType());
    }
}
