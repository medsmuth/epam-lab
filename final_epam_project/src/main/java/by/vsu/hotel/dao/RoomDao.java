package by.vsu.hotel.dao;

import by.vsu.hotel.domain.Room;

import java.util.List;

public interface RoomDao extends Dao<Room> {
    List<Room> readAll() throws DaoException;
}
