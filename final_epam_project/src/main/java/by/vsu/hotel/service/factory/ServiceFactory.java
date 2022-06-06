package by.vsu.hotel.service.factory;

import by.vsu.hotel.service.RoomService;
import by.vsu.hotel.service.ServiceException;

public interface ServiceFactory extends AutoCloseable {
    RoomService newRoomServiceInstance() throws ServiceException;

    @Override
    void close();

    static ServiceFactory newInstance() {
        return new ServiceFactoryImpl();
    }
}
