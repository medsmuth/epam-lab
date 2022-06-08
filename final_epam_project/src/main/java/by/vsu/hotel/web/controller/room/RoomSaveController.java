package by.vsu.hotel.web.controller.room;

import by.vsu.hotel.domain.ApartmentType;
import by.vsu.hotel.domain.Room;
import by.vsu.hotel.service.RoomService;
import by.vsu.hotel.service.ServiceException;
import by.vsu.hotel.service.factory.ServiceFactory;
import by.vsu.hotel.web.controller.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RoomSaveController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String number = req.getParameter("number");
        String type = req.getParameter("type");
        String numberOfSeats = req.getParameter("numberOfSeats");
        String priceRubStr = req.getParameter("priceRub");
        String priceKopStr = req.getParameter("priceKop");
        Room room = new Room();
        if(number == null || number.isBlank()) {
            Map<String, String> params = new HashMap<>(Map.of("error", "Номер комнаты не может быть пустым"));
            if(id != null) {
                params.put("id", id);
            }
            WebUtil.sendRedirect(req, resp, "/room/edit.html", params);
            return;
        }
        try {
            room.setNumber(Integer.valueOf(number));
        } catch (NumberFormatException e) {
            Map<String, String> params = new HashMap<>(Map.of("error", "Номер комнаты должен быть числом"));
            if(id != null) {
                params.put("id", id);
            }
            WebUtil.sendRedirect(req, resp, "/room/edit.html", params);
            return;
        }
        if(room.getNumber() <= 0) {
            Map<String, String> params = new HashMap<>(Map.of("error", "Номер комнаты должен быть положительным"));
            if(id != null) {
                params.put("id", id);
            }
            WebUtil.sendRedirect(req, resp, "/room/edit.html", params);
            return;
        }
        if(type == null) {
            Map<String, String> params = new HashMap<>(Map.of("error", "Тип комнаты не выбран"));
            if(id != null) {
                params.put("id", id);
            }
            WebUtil.sendRedirect(req, resp, "/room/edit.html", params);
            return;
        }
        try {
            room.setApartmentType(ApartmentType.values()[Integer.parseInt(type)]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            resp.sendError(400);
            return;
        }
        if(numberOfSeats == null || numberOfSeats.isBlank()) {
            Map<String, String> params = new HashMap<>(Map.of("error", "Количество мест в комнате не может быть пустым"));
            if(id != null) {
                params.put("id", id);
            }
            WebUtil.sendRedirect(req, resp, "/room/edit.html", params);
            return;
        }
        try {
            room.setNumberOfSeats(Integer.valueOf(numberOfSeats));
        } catch (NumberFormatException e) {
            Map<String, String> params = new HashMap<>(Map.of("error", "Количество мест в комнате должно быть числом"));
            if(id != null) {
                params.put("id", id);
            }
            WebUtil.sendRedirect(req, resp, "/room/edit.html", params);
            return;
        }
        if(room.getNumberOfSeats() <= 0) {
            Map<String, String> params = new HashMap<>(Map.of("error", "Количество мест в комнате должно быть положительным"));
            if(id != null) {
                params.put("id", id);
            }
            WebUtil.sendRedirect(req, resp, "/room/edit.html", params);
            return;
        }
        long priceRub;
        if(priceRubStr == null || priceRubStr.isBlank()) {
            Map<String, String> params = new HashMap<>(Map.of("error", "Цена (количество рублей) не может быть пустой"));
            if(id != null) {
                params.put("id", id);
            }
            WebUtil.sendRedirect(req, resp, "/room/edit.html", params);
            return;
        }
        try {
            priceRub = Long.parseLong(priceRubStr);
        } catch (NumberFormatException e) {
            Map<String, String> params = new HashMap<>(Map.of("error", "Цена (количество рублей) должна быть числом"));
            if(id != null) {
                params.put("id", id);
            }
            WebUtil.sendRedirect(req, resp, "/room/edit.html", params);
            return;
        }
        if(priceRub < 0) {
            Map<String, String> params = new HashMap<>(Map.of("error", "Цена (количество рублей) должна быть не отрицательной"));
            if(id != null) {
                params.put("id", id);
            }
            WebUtil.sendRedirect(req, resp, "/room/edit.html", params);
            return;
        }
        if(priceKopStr == null || priceKopStr.isBlank()) {
            Map<String, String> params = new HashMap<>(Map.of("error", "Цена (количество копеек) не может быть пустой"));
            if(id != null) {
                params.put("id", id);
            }
            WebUtil.sendRedirect(req, resp, "/room/edit.html", params);
            return;
        }
        long priceKop;
        try {
            priceKop = Long.parseLong(priceKopStr);
        } catch (NumberFormatException e) {
            Map<String, String> params = new HashMap<>(Map.of("error", "Цена (количество копеек) должна быть числом"));
            if(id != null) {
                params.put("id", id);
            }
            WebUtil.sendRedirect(req, resp, "/room/edit.html", params);
            return;
        }
        if(priceKop < 0) {
            Map<String, String> params = new HashMap<>(Map.of("error", "Цена (количество копеек) должна быть не отрицательной"));
            if(id != null) {
                params.put("id", id);
            }
            WebUtil.sendRedirect(req, resp, "/room/edit.html", params);
            return;
        }
        if(priceKop >= 100) {
            Map<String, String> params = new HashMap<>(Map.of("error", "Цена (количество копеек) должна быть меньше 100"));
            if(id != null) {
                params.put("id", id);
            }
            WebUtil.sendRedirect(req, resp, "/room/edit.html", params);
            return;
        }
        room.setPrice(priceRub * 100 + priceKop);
        try(ServiceFactory factory = ServiceFactory.newInstance()) {
            RoomService service = factory.newRoomServiceInstance();
            service.save(room);
            WebUtil.sendRedirect(req, resp, "/room/list.html", Map.of("message", "Комната сохранена успешно"));
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }
}
