package co.architecture.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    public static Connection getDataSource() throws SQLException {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.h2.Driver");
        basicDataSource.setUrl("jdbc:h2:mem:chess");
        basicDataSource.setUsername("sa");
        basicDataSource.setPassword("");
        return basicDataSource.getConnection();
    }
}
