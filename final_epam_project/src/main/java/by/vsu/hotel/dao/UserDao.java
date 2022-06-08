package by.vsu.hotel.dao;

import by.vsu.hotel.domain.User;

public interface UserDao extends Dao<User> {
    User readByLoginAndPassword(String login, String password) throws DaoException;
}
