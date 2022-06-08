package by.vsu.hotel.dao.mysql;

import by.vsu.hotel.dao.DaoException;
import by.vsu.hotel.dao.RoomDao;
import by.vsu.hotel.dao.UserDao;
import by.vsu.hotel.domain.ApartmentType;
import by.vsu.hotel.domain.Role;
import by.vsu.hotel.domain.Room;
import by.vsu.hotel.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class UserDaoMysqlImpl extends BaseDaoMysqlImpl<User> implements UserDao {
    @Override
    protected String tableName() {
        return "user";
    }

    @Override
    public User readByLoginAndPassword(String login, String password) throws DaoException {
        AtomicReference<User> result = new AtomicReference<>(null);
        select(
                "SELECT `id`, `login`, `password`, `role` FROM `user` WHERE `login` = ? AND `password` = ?",
                preparedStatement -> {
                    preparedStatement.setString(1, login);
                    preparedStatement.setString(2, password);
                },
                result::set
        );
        return result.get();
    }

    @Override
    protected String readSql() {
        return "SELECT `id`, `login`, `password`, `role` FROM `user` WHERE `id` = ?";
    }

    @Override
    protected User buildEntity(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(Role.values()[resultSet.getInt("role")]);
        return user;
    }

    @Override
    protected String createSql() {
        return "INSERT INTO `user`(`login`, `password`, `role`) VALUES (?, ?, ?)";
    }

    @Override
    protected void substituteCreateSqlParameters(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3, user.getRole().getId());
    }

    @Override
    protected String updateSql() {
        return "UPDATE `user` SET `login` = ?, `password` = ?, `role` = ? WHERE `id` = ?";
    }

    @Override
    protected void substituteUpdateSqlParameters(PreparedStatement preparedStatement, User user) throws SQLException {
        substituteCreateSqlParameters(preparedStatement, user);
        preparedStatement.setInt(4, user.getId());
    }
}
