package by.vsu.hotel.service;

import by.vsu.hotel.dao.DaoException;
import by.vsu.hotel.dao.UserDao;
import by.vsu.hotel.domain.User;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws ServiceException {
        try {
            return userDao.readByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
