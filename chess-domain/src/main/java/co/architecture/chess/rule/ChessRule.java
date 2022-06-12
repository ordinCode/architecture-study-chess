package co.architecture.chess.rule;

import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.piece.Team;
import co.architecture.chess.piece.config.Piece;

import java.util.Map;

public interface ChessRule {
    Map<Tile, Piece> settingInitChessBoard();

    Team getFirstTurn();

    Team findWinner(Map<Tile, Piece> board);

    boolean isGameOver(Map<Tile, Piece> board);
}
