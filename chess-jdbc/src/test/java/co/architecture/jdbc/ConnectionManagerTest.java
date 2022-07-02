package co.architecture.jdbc;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class ConnectionManagerTest {
    @Test
    void getDataSource() throws SQLException {
        Connection dataSource = ConnectionManager.getDataSource();

        assertThat(dataSource.getMetaData().getURL()).isEqualTo("jdbc:h2:mem:chess");
    }
}