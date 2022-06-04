package by.vsu.hotel.dao.mysql;

import by.vsu.hotel.dao.Dao;
import by.vsu.hotel.dao.DaoException;
import by.vsu.hotel.domain.Entity;

import java.sql.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

abstract public class BaseDaoMysqlImpl<T extends Entity> implements Dao<T> {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public final Integer create(T entity) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(createSql(), Statement.RETURN_GENERATED_KEYS);
            substituteCreateSqlParameters(preparedStatement, entity);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return  resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try { Objects.requireNonNull(resultSet).close(); } catch (Exception ignored) {}
            try { Objects.requireNonNull(preparedStatement).close(); } catch (Exception ignored) {}
        }
    }

    @Override
    public final T read(Integer id) throws DaoException {
        AtomicReference<T> result = new AtomicReference<>(null);
        select(readSql(), preparedStatement -> preparedStatement.setInt(1, id), result::set);
        return result.get();
    }

    @Override
    public final void update(T entity) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(updateSql());
            substituteUpdateSqlParameters(preparedStatement, entity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try { Objects.requireNonNull(preparedStatement).close(); } catch (Exception ignored) {}
        }
    }

    @Override
    public final void delete(Integer id) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(String.format("DELETE FROM `%s` WHERE `id` = ?", tableName()));
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try { Objects.requireNonNull(preparedStatement).close(); } catch (Exception ignored) {}
        }
    }

    protected final void select(String sql, SearchCriteriaFilter filter, FoundEntityHandler<T> handler) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            if(filter != null) {
                filter.fillSearchCriteria(preparedStatement);
            }
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                handler.processEntity(buildEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try { Objects.requireNonNull(resultSet).close(); } catch (Exception ignored) {}
            try { Objects.requireNonNull(preparedStatement).close(); } catch (Exception ignored) {}
        }
    }

    abstract protected String tableName();

    abstract protected String readSql();

    abstract protected T buildEntity(ResultSet resultSet) throws SQLException;

    abstract protected String createSql();

    abstract protected void substituteCreateSqlParameters(PreparedStatement preparedStatement, T entity) throws SQLException;

    abstract protected String updateSql();

    abstract protected void substituteUpdateSqlParameters(PreparedStatement preparedStatement, T entity) throws SQLException;

    protected interface SearchCriteriaFilter {
        void fillSearchCriteria(PreparedStatement preparedStatement) throws SQLException;
    }

    protected interface FoundEntityHandler<E extends Entity> {
        void processEntity(E entity);
    }
}
