package co.architecture.consolechess.gamefacory;

import co.architecture.chess.ChessGame;
import co.architecture.chess.rule.ChessRuleType;
import co.architecture.chess.rule.defaultrule.DefaultChessRule;

public class ConsoleChessGameFactory {
    private static ChessGame chessGame;

    public static ChessGame getInstance() {
        if (chessGame == null) {
            chessGame = ChessGame.init(ChessRuleType.ofClass(DefaultChessRule.class));
        }
        return chessGame;
    }

    public static void updateChessGame(ChessGame chessGame) {
        getInstance().updateChessGame(chessGame);
    }
}
