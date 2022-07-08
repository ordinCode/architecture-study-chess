package co.architecture.consolechess.adapter.out.persistant;

import co.architecture.chess.GameState;
import co.architecture.chess.piece.Team;
import co.architecture.consolechess.adapter.out.persistant.entity.ChessGameJdbcEntity;
import co.architecture.chess.rule.ChessRuleType;
import co.architecture.jdbc.ConnectionManager;
import co.architecture.jdbc.JdbcTemplate;
import co.architecture.jdbc.RowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChessGameDao {
    private static final Logger log = LoggerFactory.getLogger(ChessGameDao.class);

    public static final long TEMP_CONSOLE_GAME_ID = 1L;
    public static final String CHESS_BOARD_TABLE_NAME = "chess_board";

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(ConnectionManager.getDataSource());

    private ChessGameDao() {
        if (jdbcTemplate.existTable(CHESS_BOARD_TABLE_NAME)) {
            return;
        }
        log.info("create table {}", CHESS_BOARD_TABLE_NAME);
        jdbcTemplate.executeUpdate("create table " + CHESS_BOARD_TABLE_NAME + " (\n" +
                "    id bigint(20) not null auto_increment,\n" +
                "    chessRuleType varchar(10) default null,\n" +
                "    gameState varchar(10) default null,\n" +
                "    turn varchar(10) default null,\n" +
                "    boardPayload varchar(255) default null,\n" +
                "    justNowPawnJumpedTilePayload varchar(100) default null,\n" +
                "    primary key (id)\n" +
                ")");
    }

    private static class LazyHolder {
        private static final ChessGameDao INSTANCE = new ChessGameDao();
    }

    public static ChessGameDao getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void insert(ChessGameJdbcEntity entity) {
        String sql = String.format("INSERT INTO %s VALUES (?, ?, ?, ?, ?, ?)", CHESS_BOARD_TABLE_NAME);
        jdbcTemplate.executeUpdate(
                sql,
                TEMP_CONSOLE_GAME_ID,
                entity.getChessRuleType().name(),
                entity.getGameState().name(),
                entity.getTurn().name(),
                entity.getBoardPayload(),
                entity.getJustNowPawnJumpedTilePayload()
        );
    }

    public void update(ChessGameJdbcEntity entity) {
        String sql = String.format("UPDATE %s SET chessRuleType=?,gameState=?,turn=?,boardPayload=?,justNowPawnJumpedTilePayload=? WHERE id=?", CHESS_BOARD_TABLE_NAME);
        jdbcTemplate.executeUpdate(
                sql,
                entity.getChessRuleType().name(),
                entity.getGameState().name(),
                entity.getTurn().name(),
                entity.getBoardPayload(),
                entity.getJustNowPawnJumpedTilePayload(),
                TEMP_CONSOLE_GAME_ID
        );
    }

    public boolean existSavedGame() {
        String sql = String.format("SELECT * from %s WHERE id=?", CHESS_BOARD_TABLE_NAME);
        return !jdbcTemplate.executeQuery(sql, generateRowMapper(), TEMP_CONSOLE_GAME_ID).isEmpty();
    }

    public ChessGameJdbcEntity load() {
        String sql = String.format("SELECT * FROM %s WHERE id=?", CHESS_BOARD_TABLE_NAME);

        RowMapper<ChessGameJdbcEntity> rowMapper = generateRowMapper();
        return jdbcTemplate.executeQueryWithUniqueResult(sql, rowMapper, TEMP_CONSOLE_GAME_ID)
                .orElseThrow(IllegalArgumentException::new);
    }

    private RowMapper<ChessGameJdbcEntity> generateRowMapper() {
        return rs -> {
            Long id = rs.getLong("id");
            String chessRuleType = rs.getString("chessRuleType");
            String gameState = rs.getString("gameState");
            String turn = rs.getString("turn");
            String boardPayload = rs.getString("boardPayload");
            String justNowPawnJumpedTilePayload = rs.getString("justNowPawnJumpedTilePayload");
            return new ChessGameJdbcEntity(id,
                    ChessRuleType.valueOf(chessRuleType),
                    GameState.valueOf(gameState),
                    Team.valueOf(turn),
                    boardPayload,
                    justNowPawnJumpedTilePayload
            );
        };
    }
}
