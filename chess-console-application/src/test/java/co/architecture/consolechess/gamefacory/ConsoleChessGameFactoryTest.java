package co.architecture.consolechess.gamefacory;

import co.architecture.chess.ChessGame;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleChessGameFactoryTest {

    @Test
    void updateChessGame() {
        //given
        ChessGame init = ConsoleChessGameFactory.getInstance();

        //when
        ConsoleChessGameFactory.updateChessGame(new ChessGame(10L, null, null, null, null));

        //then
        ChessGame updated = ConsoleChessGameFactory.getInstance();
        Assertions.assertThat(updated.getId()).isEqualTo(10);
    }
}