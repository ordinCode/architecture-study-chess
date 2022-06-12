package co.chess.consolechess;

import co.chess.chess.ChessGame;
import co.chess.consolechess.adapter.in.ConsoleChessController;
import co.chess.consolechess.adapter.in.ConsoleInputHandler;
import co.chess.consolechess.dto.ChessBoardDto;
import co.chess.consolechess.gamefacory.ConsoleChessGameFactory;
import co.chess.consolechess.port.in.ChessMoveUseCase;
import co.chess.consolechess.service.ConsoleChessService;
import co.chess.consolechess.ui.InputView;
import co.chess.consolechess.ui.OutputView;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        ChessMoveUseCase consoleChessService = new ConsoleChessService();
        ConsoleChessController consoleChessController = new ConsoleChessController(consoleChessService);
        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler(consoleChessController);

        OutputView.printIntro();
        ChessGame chessGame = ConsoleChessGameFactory.getInstance();
        OutputView.printBoardState(ChessBoardDto.of(chessGame.getChessBoard()));

        OutputView.printMoveGuide();
        while (!chessGame.isEnd()) {
            if (chessGame.isPromotion()) {
                OutputView.promotionGuide();
            }
            String userInput = InputView.userInput();
            consoleInputHandler.handleUserInput(userInput);
            OutputView.printBoardState(ChessBoardDto.of(chessGame.getChessBoard()));
        }

        OutputView.printGameOver();
    }
}
