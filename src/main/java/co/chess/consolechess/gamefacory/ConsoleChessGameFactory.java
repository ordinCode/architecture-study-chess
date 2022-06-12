package co.chess.consolechess.gamefacory;

import co.chess.chess.ChessGame;
import co.chess.chess.rule.defaultrule.DefaultChessRule;

public class ConsoleChessGameFactory {
    private static ChessGame chessGame;

    public static ChessGame getInstance() {
        if (chessGame == null) {
            chessGame = ChessGame.init(new DefaultChessRule());
        }
        return chessGame;
    }
}
