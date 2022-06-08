package by.vsu.hotel.service;

import by.vsu.hotel.domain.User;

public interface UserService {
    User findByLoginAndPassword(String login, String password) throws ServiceException;
}
