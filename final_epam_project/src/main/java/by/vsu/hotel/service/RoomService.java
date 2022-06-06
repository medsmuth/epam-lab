package by.vsu.hotel.service;

import by.vsu.hotel.domain.Room;

import java.util.List;

public interface RoomService {
    List<Room> findAll() throws ServiceException;

    Room findById(Integer id) throws ServiceException;

    void save(Room room) throws ServiceException;

    void delete(Integer id) throws ServiceException;
}
