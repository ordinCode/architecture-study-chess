package co.chess.consolechess.controller;

import co.chess.consolechess.UserInputMapper;
import co.chess.consolechess.UserInputType;
import co.chess.consolechess.exception.InputException;
import co.chess.consolechess.service.ConsoleChessService;
import co.chess.domain.ChessGame;
import co.chess.domain.exception.move.MoveException;

import java.util.Map;
import java.util.function.BiConsumer;

public class ConsoleChessController {
    private static final Map<UserInputType, BiConsumer<ChessGame, String>> consoleChessServiceMapper = Map.of(
            UserInputType.END, ConsoleChessService::end,
            UserInputType.MOVE, ConsoleChessService::move,
            UserInputType.PROMOTION, ConsoleChessService::promotion
    );

    public static void handleUserInput(ChessGame chessGame, String userInput) {
        try {
            UserInputType userInputType = UserInputMapper.findUserInputType(userInput);
            BiConsumer<ChessGame, String> commandConsumer = consoleChessServiceMapper.get(userInputType);
            commandConsumer.accept(chessGame, userInput);
        } catch (MoveException | InputException e) {
            System.out.println(e.getMessage());
        }
    }
}
