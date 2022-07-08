package co.architecture.jdbc;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class ConnectionManagerTest {
    @Test
    void getDataSource() throws SQLException {
        DataSource dataSource = ConnectionManager.getDataSource();

        assertThat(dataSource.getConnection()).isNotNull();
    }
}