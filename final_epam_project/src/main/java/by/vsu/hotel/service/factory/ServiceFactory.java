package by.vsu.hotel.service.factory;

import by.vsu.hotel.service.RoomService;
import by.vsu.hotel.service.ServiceException;
import by.vsu.hotel.service.UserService;

public interface ServiceFactory extends AutoCloseable {
    RoomService newRoomServiceInstance() throws ServiceException;

    UserService newUserServiceInstance() throws ServiceException;

    @Override
    void close();

    static ServiceFactory newInstance() {
        return new ServiceFactoryImpl();
    }
}
