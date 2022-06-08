package by.vsu.hotel.service.factory;

import by.vsu.hotel.dao.RoomDao;
import by.vsu.hotel.dao.UserDao;
import by.vsu.hotel.dao.mysql.RoomDaoMysqlImpl;
import by.vsu.hotel.dao.mysql.UserDaoMysqlImpl;
import by.vsu.hotel.dao.mysql.connector.Connector;
import by.vsu.hotel.service.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class ServiceFactoryImpl implements ServiceFactory {
    private RoomService roomService;
    @Override
    public RoomService newRoomServiceInstance() throws ServiceException {
        if(roomService == null) {
            try {
                RoomServiceImpl roomServiceImpl = new RoomServiceImpl();
                roomServiceImpl.setRoomDao(newRoomDaoInstance());
                roomService = roomServiceImpl;
            } catch (SQLException e) {
                throw new ServiceException(e);
            }
        }
        return roomService;
    }

    private UserService userService;
    @Override
    public UserService newUserServiceInstance() throws ServiceException {
        if(userService == null) {
            try {
                UserServiceImpl userServiceImpl = new UserServiceImpl();
                userServiceImpl.setUserDao(newUserDaoInstance());
                userService = userServiceImpl;
            } catch (SQLException e) {
                throw new ServiceException(e);
            }
        }
        return userService;
    }

    private RoomDao roomDao;
    private RoomDao newRoomDaoInstance() throws SQLException {
        if(roomDao == null) {
            RoomDaoMysqlImpl roomDaoMysqlImpl = new RoomDaoMysqlImpl();
            roomDaoMysqlImpl.setConnection(newConnectionInstance());
            roomDao = roomDaoMysqlImpl;
        }
        return roomDao;
    }

    private UserDao userDao;
    private UserDao newUserDaoInstance() throws SQLException {
        if(userDao == null) {
            UserDaoMysqlImpl userDaoMysqlImpl = new UserDaoMysqlImpl();
            userDaoMysqlImpl.setConnection(newConnectionInstance());
            userDao = userDaoMysqlImpl;
        }
        return userDao;
    }

    private Connection connection;
    private Connection newConnectionInstance() throws SQLException {
        if(connection == null) {
            connection = Connector.getConnection();
        }
        return connection;
    }

    @Override
    public void close() {
        try {
            Objects.requireNonNull(connection).close();
        } catch (Exception ignored) {}
    }
}
