package by.vsu.hotel.dao.mysql;

import by.vsu.hotel.dao.DaoException;
import by.vsu.hotel.dao.RoomDao;
import by.vsu.hotel.domain.ApartmentType;
import by.vsu.hotel.domain.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoMysqlImpl extends BaseDaoMysqlImpl<Room> implements RoomDao {
    @Override
    protected String tableName() {
        return "room";
    }

    @Override
    public List<Room> readAll() throws DaoException {
        List<Room> rooms = new ArrayList<>();
        select(
                "SELECT `id`, `number`, `number_of_seats`, `apartment_type`, `price` FROM `room`",
                null,
                rooms::add
        );
        return rooms;
    }

    @Override
    protected String readSql() {
        return "SELECT `id`, `number`, `number_of_seats`, `apartment_type`, `price` FROM `room` WHERE `id` = ?";
    }

    @Override
    protected Room buildEntity(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setId(resultSet.getInt("id"));
        room.setNumber(resultSet.getInt("number"));
        room.setNumberOfSeats(resultSet.getInt("number_of_seats"));
        room.setApartmentType(ApartmentType.values()[resultSet.getInt("apartment_type")]);
        room.setPrice(resultSet.getLong("price"));
        return room;
    }

    @Override
    protected String createSql() {
        return "INSERT INTO `room`(`number`, `number_of_seats`, `apartment_type`, `price`) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected void substituteCreateSqlParameters(PreparedStatement preparedStatement, Room room) throws SQLException {
        preparedStatement.setInt(1, room.getNumber());
        preparedStatement.setInt(2, room.getNumberOfSeats());
        preparedStatement.setInt(3, room.getApartmentType().getId());
        preparedStatement.setLong(4, room.getPrice());
    }

    @Override
    protected String updateSql() {
        return "UPDATE `room` SET `number` = ?, `number_of_seats` = ?, `apartment_type` = ?, `price` = ? WHERE `id` = ?";
    }

    @Override
    protected void substituteUpdateSqlParameters(PreparedStatement preparedStatement, Room room) throws SQLException {
        substituteCreateSqlParameters(preparedStatement, room);
        preparedStatement.setInt(5, room.getId());
    }
}
