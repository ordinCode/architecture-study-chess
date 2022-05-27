package co.chess.domain.rule;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.piece.Team;
import co.chess.domain.piece.config.Piece;

import java.util.Map;

public interface ChessRule {
    Map<Tile, Piece> settingInitChessBoard();

    Team getFirstTurn();

    Team findWinner(Map<Tile, Piece> board);

    boolean isGameOver(Map<Tile, Piece> board);
}
