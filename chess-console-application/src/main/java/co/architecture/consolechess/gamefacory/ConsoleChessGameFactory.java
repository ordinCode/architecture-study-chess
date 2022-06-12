package co.architecture.consolechess.gamefacory;

import co.architecture.chess.ChessGame;
import co.architecture.chess.rule.defaultrule.DefaultChessRule;

public class ConsoleChessGameFactory {
    private static ChessGame chessGame;

    public static ChessGame getInstance() {
        if (chessGame == null) {
            chessGame = ChessGame.init(new DefaultChessRule());
        }
        return chessGame;
    }
}
