package co.architecture.consolechess.application.service;

import co.architecture.application.port.out.dto.ChessGameDto;
import co.architecture.chess.ChessGame;
import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.chessboard.tile.TileFactory;
import co.architecture.chess.piece.config.Piece;
import co.architecture.consolechess.application.port.in.ChessMoveUseCase;
import co.architecture.consolechess.application.port.in.MovePieceCommand;
import co.architecture.consolechess.application.port.in.PromotionCommand;
import co.architecture.application.port.out.SaveChessGamePort;
import co.architecture.consolechess.gamefacory.ConsoleChessGameFactory;
import co.architecture.consolechess.utils.PieceSymbolConverter;

import java.util.HashMap;
import java.util.Map;

public class ConsoleChessService implements ChessMoveUseCase {
    private final SaveChessGamePort saveChessGamePort;

    public ConsoleChessService(SaveChessGamePort saveChessGamePort) {
        this.saveChessGamePort = saveChessGamePort;
    }

    @Override
    public void movePiece(MovePieceCommand movePieceCommand) {
        String source = movePieceCommand.getSource();
        String target = movePieceCommand.getTarget();

        ChessGame chessGame = ConsoleChessGameFactory.getInstance();
        chessGame.move(TileFactory.from(source), TileFactory.from(target));

        saveChessGamePort.saveChessGame(toChessGameDto(chessGame));
    }

    private ChessGameDto toChessGameDto(ChessGame chessGame) {
        Map<String, String> board = new HashMap<>();
        for (Map.Entry<Tile, Piece> tilePieceEntry : chessGame.getChessBoard().getBoard().entrySet()) {
            board.put(tilePieceEntry.getKey().toString(), PieceSymbolConverter.toSymbol(tilePieceEntry.getValue()));
        }
        return new ChessGameDto(
                chessGame.getId(),
                chessGame.getChessRuleType().name(),
                chessGame.getGameState().name(),
                chessGame.getTurn().name(),
                board,
                chessGame.getChessBoard().getJustNowPawnJumpedTile().toString()
        );
    }

    @Override
    public void promotion(PromotionCommand promotionCommand) {
        ChessGame chessGame = ConsoleChessGameFactory.getInstance();
        chessGame.promotion(promotionCommand.getPieceType());
    }
}
