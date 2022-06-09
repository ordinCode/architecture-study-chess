package co.chess;

import co.chess.consolechess.UserInputMapper;
import co.chess.consolechess.UserInputType;
import co.chess.consolechess.controller.ConsoleChessController;
import co.chess.consolechess.dto.ChessBoardDto;
import co.chess.consolechess.ui.InputView;
import co.chess.consolechess.ui.OutputView;
import co.chess.domain.ChessGame;
import co.chess.domain.rule.defaultrule.DefaultChessRule;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        OutputView.printIntro();
        ChessGame chessGame = gameInitOrOver();

        OutputView.printBoardState(ChessBoardDto.of(chessGame.getChessBoard()));
        startGame(chessGame);

        OutputView.printGameOver(chessGame.findWinner());
    }

    private static ChessGame gameInitOrOver() {
        UserInputType userInputType = UserInputMapper.findUserInputType(InputView.userInput());
        if (userInputType == UserInputType.END) {
            OutputView.printGameOver(null);
            System.exit(0);
        }
        return ChessGame.init(new DefaultChessRule());
    }

    private static void startGame(ChessGame chessGame) {
        OutputView.printMoveGuide();
        while (!chessGame.isEnd()) {
            if (chessGame.isPromotion()) {
                doPromotion(chessGame);
                continue;
            }
            String userInput = InputView.userInput();
            ConsoleChessController.handleUserInput(chessGame, userInput);
            OutputView.printBoardState(ChessBoardDto.of(chessGame.getChessBoard()));
        }
    }

    private static void doPromotion(ChessGame chessGame) {
        OutputView.promotionGuide();
        String userInput = InputView.userInput();
        ConsoleChessController.handleUserInput(chessGame, userInput);
        OutputView.printBoardState(ChessBoardDto.of(chessGame.getChessBoard()));
    }
}
