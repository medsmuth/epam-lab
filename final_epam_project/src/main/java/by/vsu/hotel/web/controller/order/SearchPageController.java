package by.vsu.hotel.web.controller.order;

import by.vsu.hotel.domain.ApartmentType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchPageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("types", List.of(ApartmentType.values()));
        req.getRequestDispatcher("/WEB-INF/jsp/order/search.jsp").forward(req, resp);
    }
}
