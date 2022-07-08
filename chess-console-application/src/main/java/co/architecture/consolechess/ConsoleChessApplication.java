package co.architecture.consolechess;

import co.architecture.chess.ChessGame;
import co.architecture.chess.exception.move.MoveException;
import co.architecture.consolechess.adapter.in.ConsoleChessController;
import co.architecture.consolechess.adapter.in.ConsoleInputHandler;
import co.architecture.jdbc.chessgame.ChessGameDao;
import co.architecture.jdbc.chessgame.ChessGamePersistenceAdaptor;
import co.architecture.consolechess.application.port.in.GetChessGameQuery;
import co.architecture.consolechess.application.service.ConsoleChessQueryService;
import co.architecture.consolechess.dto.ChessBoardDto;
import co.architecture.consolechess.gamefacory.ConsoleChessGameFactory;
import co.architecture.consolechess.application.port.in.ChessMoveUseCase;
import co.architecture.consolechess.application.service.ConsoleChessService;
import co.architecture.consolechess.ui.output.OutputView;
import co.architecture.consolechess.ui.input.InputView;
import co.architecture.consolechess.ui.input.UserInputMapper;
import co.architecture.consolechess.ui.input.UserInputType;
import co.architecture.consolechess.ui.input.exception.InputException;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        ChessGameDao chessGameDao = ChessGameDao.getInstance();
        ChessGamePersistenceAdaptor chessGamePersistenceAdaptor = new ChessGamePersistenceAdaptor(chessGameDao);

        ChessMoveUseCase consoleChessService = new ConsoleChessService(chessGamePersistenceAdaptor);
        GetChessGameQuery consoleChessQueryService = new ConsoleChessQueryService(chessGamePersistenceAdaptor);
        ConsoleChessController consoleChessController = new ConsoleChessController(consoleChessService, consoleChessQueryService);

        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler(consoleChessController);

        OutputView.printIntro();
        ChessGame chessGame = ConsoleChessGameFactory.getInstance();
        OutputView.printBoardState(ChessBoardDto.of(chessGame.getChessBoard()));

        OutputView.printMoveGuide();
        while (!chessGame.isEnd()) {
            progressChess(consoleInputHandler);
        }

        OutputView.printGameOver(chessGame.getWinner());
    }

    private static void progressChess(ConsoleInputHandler consoleInputHandler) {
        try {
            ChessGame chessGame = ConsoleChessGameFactory.getInstance();
            if (chessGame.isPromotion()) {
                OutputView.promotionGuide();
            }

            String userInput = InputView.userInput();
            if (isEndCommand(userInput)) {
                OutputView.printGameOver(chessGame.getWinner());
                System.exit(0);
            }

            consoleInputHandler.handleUserInput(userInput);
            OutputView.printBoardState(ChessBoardDto.of(chessGame.getChessBoard()));
        } catch (MoveException | InputException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean isEndCommand(String userInput) {
        UserInputType userInputType = UserInputMapper.findUserInputType(userInput);
        return userInputType == UserInputType.END;
    }
}
