package by.vsu.hotel.web.controller.room;

import by.vsu.hotel.service.RoomService;
import by.vsu.hotel.service.ServiceException;
import by.vsu.hotel.service.factory.ServiceFactory;
import by.vsu.hotel.web.controller.BaseController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class RoomDeleteController extends BaseController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try(ServiceFactory factory = ServiceFactory.newInstance()) {
            RoomService service = factory.newRoomServiceInstance();
            service.delete(Integer.valueOf(id));
            sendRedirect(req, resp, "/room/list.html", Map.of("message", "Комната удалена успешно"));
        } catch (NumberFormatException e) {
            resp.sendError(400);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }
}
