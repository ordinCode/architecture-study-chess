package co.architecture.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class ConnectionManager {
//    public static final String DRIVER_CLASS_NAME = "org.h2.Driver";
//    public static final String URL = "jdbc:h2:mem:chess";
//    public static final String USER_NAME = "sa";
//    public static final String PASSWORD = "";

    public static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/chess";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "dldntjs";

    public static DataSource getDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(DRIVER_CLASS_NAME);
        basicDataSource.setUrl(URL);
        basicDataSource.setUsername(USER_NAME);
        basicDataSource.setPassword(PASSWORD);
        return basicDataSource;
    }
}
