package by.vsu.hotel.web.controller.room;

import by.vsu.hotel.service.RoomService;
import by.vsu.hotel.service.ServiceException;
import by.vsu.hotel.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoomListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(ServiceFactory factory = ServiceFactory.newInstance()) {
            RoomService service = factory.newRoomServiceInstance();
            req.setAttribute("rooms", service.findAll());
            req.getRequestDispatcher("/WEB-INF/jsp/room/list.jsp").forward(req, resp);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }
}
