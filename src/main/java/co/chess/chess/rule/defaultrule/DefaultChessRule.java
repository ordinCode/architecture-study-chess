package co.chess.chess.rule.defaultrule;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.piece.PieceType;
import co.chess.chess.piece.Team;
import co.chess.chess.piece.config.Piece;
import co.chess.chess.rule.ChessRule;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultChessRule implements ChessRule {
    @Override
    public Team findWinner(Map<Tile, Piece> board) {
        List<Piece> collect = board.values().stream()
                .filter(piece -> piece.getType() == PieceType.KING)
                .collect(Collectors.toList());
        if (collect.size() >= 2) {
            throw new IllegalArgumentException();
        }
        return collect.get(0).getTeam();
    }

    @Override
    public boolean isGameOver(Map<Tile, Piece> board) {
        List<Piece> kings = board.values().stream()
                .filter(piece -> piece.equalsBy(PieceType.KING))
                .collect(Collectors.toList());
        return kings.size() == 1;
    }

    @Override
    public Team getFirstTurn() {
        return Team.WHITE;
    }

    @Override
    public Map<Tile, Piece> settingInitChessBoard() {
        return DefaultChessBoardInitializer.settingInitChessBoard();
    }
}
