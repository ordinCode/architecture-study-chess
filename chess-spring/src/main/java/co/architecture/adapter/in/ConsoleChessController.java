package co.architecture.adapter.in;

import co.architecture.application.dto.ChessGameResponse;
import co.architecture.application.port.in.ChessMoveUseCase;
import co.architecture.application.port.in.GetChessGameQuery;
import co.architecture.application.port.in.MovePieceCommand;
import co.architecture.application.port.in.PromotionCommand;
import co.architecture.application.port.out.dto.ChessGameDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsoleChessController {
    private final ChessMoveUseCase chessMoveUseCase;
    private final GetChessGameQuery getChessGameQuery;

    public ConsoleChessController(ChessMoveUseCase chessMoveUseCase, GetChessGameQuery getChessGameQuery) {
        this.chessMoveUseCase = chessMoveUseCase;
        this.getChessGameQuery = getChessGameQuery;
    }

    @PutMapping("/chess-games/{chessGameId}/move")
    public ResponseEntity<Void> moveChess(
            @PathVariable Long chessGameId,
            @RequestBody MovePieceCommand movePieceCommand
    ) {
        chessMoveUseCase.movePiece(chessGameId, movePieceCommand);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/chess-games/{chessGameId}/promotion")
    public ResponseEntity<Void> promotion(
            @PathVariable Long chessGameId,
            @RequestBody PromotionCommand promotionCommand) {
        chessMoveUseCase.promotion(chessGameId, promotionCommand);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/chess-games/{chessGameId}")
    public ResponseEntity<ChessGameResponse> load(
            @PathVariable Long chessGameId
    ) {
        ChessGameDto chessGame = getChessGameQuery.getChessGame(chessGameId);
        ChessGameResponse chessGameResponse = new ChessGameResponse(
                chessGame.getId(),
                chessGame.getChessRuleType(),
                chessGame.getGameState(),
                chessGame.getTurn(),
                chessGame.getBoard(),
                chessGame.getJustNowPawnJumpedTile()
        );
        return ResponseEntity.ok(chessGameResponse);
    }
}
