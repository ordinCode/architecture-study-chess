package co.architecture.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcTemplate {
    private final DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public boolean existTable(String tableName) {
        try (Connection con = getConnection()) {
            boolean exist = false;
            DatabaseMetaData metaData = con.getMetaData();
            ResultSet tables = metaData.getTables(null, null, null, new String[] {"TABLE"});
            while (tables.next()) {
                String name = tables.getString("TABLE_NAME");
                if (tableName.equalsIgnoreCase(name)) {
                    exist = true;
                    break;
                }
            }
            return exist;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void executeUpdate(final String sql, Object... values) {
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            setValuesToPreparedStatement(pstmt, values);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public <T> List<T> executeQuery(final String sql, RowMapper<T> rowMapper, Object... values) {
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            setValuesToPreparedStatement(pstmt, values);

            return executeRowMapper(pstmt, rowMapper);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private <T> List<T> executeRowMapper(final PreparedStatement pstmt, final RowMapper<T> rowMapper) throws SQLException {
        try (ResultSet rs = pstmt.executeQuery()) {
            List<T> objects = new ArrayList<>();
            while (rs.next()) {
                objects.add(rowMapper.mapRow(rs));
            }
            return objects;
        }
    }

    private void setValuesToPreparedStatement(final PreparedStatement pstmt, final Object[] values) throws SQLException {
        for (int index = 1; index <= values.length; index++) {
            pstmt.setObject(index, values[index - 1]);
        }
    }

    public <T> Optional<T> executeQueryWithUniqueResult(final String sql, RowMapper<T> rowMapper, Object... values) {
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            setValuesToPreparedStatement(pstmt, values);

            return Optional.ofNullable(executeRowMapperWithUniqueResult(pstmt, rowMapper));
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private <T> T executeRowMapperWithUniqueResult(final PreparedStatement pstmt, final RowMapper<T> rowMapper) throws SQLException {
        try (ResultSet rs = pstmt.executeQuery()) {
            return (rs.next()) ? rowMapper.mapRow(rs) : null;
        }
    }
}
