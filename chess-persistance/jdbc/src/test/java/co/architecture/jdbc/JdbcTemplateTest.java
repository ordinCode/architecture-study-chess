package co.architecture.jdbc;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JdbcTemplateTest {
    @Disabled
    @DisplayName("테이블 생성 테스트")
    @Test
    void create() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ConnectionManager.getDataSource());
        jdbcTemplate.executeUpdate("create table chess_board (\n" +
                "    id bigint(20) not null auto_increment,\n" +
                "    name varchar(255) default null,\n" +
                "    primary key (id)\n" +
                ")");

        //then
        Assertions.assertThat(jdbcTemplate.existTable("chess_board")).isTrue();
    }
}