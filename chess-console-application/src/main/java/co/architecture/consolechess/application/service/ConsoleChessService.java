package co.architecture.consolechess.application.service;

import co.architecture.chess.ChessGame;
import co.architecture.chess.chessboard.tile.TileFactory;
import co.architecture.consolechess.application.port.in.ChessMoveUseCase;
import co.architecture.consolechess.application.port.in.MovePieceCommand;
import co.architecture.consolechess.application.port.in.PromotionCommand;
import co.architecture.consolechess.gamefacory.ConsoleChessGameFactory;

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
