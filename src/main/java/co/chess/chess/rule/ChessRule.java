package co.chess.chess.rule;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.piece.Team;
import co.chess.chess.piece.config.Piece;

import java.util.Map;

public interface ChessRule {
    Map<Tile, Piece> settingInitChessBoard();

    Team getFirstTurn();

    Team findWinner(Map<Tile, Piece> board);

    boolean isGameOver(Map<Tile, Piece> board);
}
