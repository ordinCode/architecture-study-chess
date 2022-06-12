package co.chess.consolechess.service;

import co.chess.chess.chessboard.tile.TileFactory;
import co.chess.consolechess.exception.InputException;
import co.chess.consolechess.gamefacory.ConsoleChessGameFactory;
import co.chess.consolechess.port.in.ChessMoveUseCase;
import co.chess.consolechess.port.in.MovePieceCommand;
import co.chess.consolechess.ui.InputView;
import co.chess.consolechess.ui.OutputView;
import co.chess.consolechess.utils.CommandValidator;
import co.chess.consolechess.utils.PieceSymbolConverter;
import co.chess.chess.ChessGame;
import co.chess.chess.dto.MoveRequest;
import co.chess.consolechess.port.in.PromotionCommand;
import co.chess.chess.exception.move.MoveException;
import co.chess.chess.piece.PieceType;

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
