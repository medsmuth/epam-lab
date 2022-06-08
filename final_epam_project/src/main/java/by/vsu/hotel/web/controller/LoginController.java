package by.vsu.hotel.web.controller;

import by.vsu.hotel.domain.User;
import by.vsu.hotel.service.ServiceException;
import by.vsu.hotel.service.UserService;
import by.vsu.hotel.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try(ServiceFactory factory = ServiceFactory.newInstance()) {
            UserService userService = factory.newUserServiceInstance();
            User user = userService.findByLoginAndPassword(login, password);
            if(user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("sessionUser", user);
                WebUtil.sendRedirect(req, resp, "/index.html");
            } else {
                WebUtil.sendRedirect(req, resp, "/login.html", Map.of("error", "Введены неверные имя пользователя или пароль"));
            }
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }
}
