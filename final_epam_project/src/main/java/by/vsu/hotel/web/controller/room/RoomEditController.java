package by.vsu.hotel.web.controller.room;

import by.vsu.hotel.domain.ApartmentType;
import by.vsu.hotel.domain.Room;
import by.vsu.hotel.service.RoomService;
import by.vsu.hotel.service.ServiceException;
import by.vsu.hotel.service.factory.ServiceFactory;
import by.vsu.hotel.web.controller.BaseController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RoomEditController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id != null) {
            try(ServiceFactory factory = ServiceFactory.newInstance()) {
                RoomService service = factory.newRoomServiceInstance();
                Room room = service.findById(Integer.valueOf(id));
                if(room == null) {
                    throw new IllegalArgumentException();
                }
                req.setAttribute("room", room);
            } catch (IllegalArgumentException e) {
                resp.sendError(404);
                return;
            } catch (ServiceException e) {
                throw new ServletException(e);
            }
        }
        req.setAttribute("types", List.of(ApartmentType.values()));
        req.getRequestDispatcher("/WEB-INF/jsp/room/edit.jsp").forward(req, resp);
    }
}
