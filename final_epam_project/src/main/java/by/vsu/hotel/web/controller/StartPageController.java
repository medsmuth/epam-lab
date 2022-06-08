package by.vsu.hotel.web.controller;

import by.vsu.hotel.domain.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StartPageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if(session != null) {
            User user = (User) session.getAttribute("sessionUser");
            if(user != null) {
                switch (user.getRole()) {
                    case ADMIN -> {
                        // TODO: заменить стартовую страницу на список текущих заявок
                        WebUtil.sendRedirect(req, resp, "/room/list.html");
                        return;
                    }
                    case CLIENT -> {
                        WebUtil.sendRedirect(req, resp, "/order/search.html");
                        return;
                    }
                }
            }
        }
        WebUtil.sendRedirect(req, resp, "/info.html");
    }
}
