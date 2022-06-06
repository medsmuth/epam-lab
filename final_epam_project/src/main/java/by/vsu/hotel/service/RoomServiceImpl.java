package by.vsu.hotel.service;

import by.vsu.hotel.dao.DaoException;
import by.vsu.hotel.dao.RoomDao;
import by.vsu.hotel.domain.Room;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    private RoomDao roomDao;

    public void setRoomDao(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public List<Room> findAll() throws ServiceException {
        try {
            return roomDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Room findById(Integer id) throws ServiceException {
        try {
            return roomDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Room room) throws ServiceException {
        try {
            if(room.getId() != null) {
                roomDao.update(room);
            } else {
                roomDao.create(room);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            roomDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
