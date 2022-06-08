package by.vsu.hotel.web;

import by.vsu.hotel.domain.Role;
import by.vsu.hotel.domain.User;
import by.vsu.hotel.web.controller.WebUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class SecurityFilter extends HttpFilter {
    private static final Set<String> adminUrls = Set.of(
            "/room/list.html",
            "/room/edit.html",
            "/room/save.html",
            "/room/delete.html",
            "/logout.html"
    );
    private static final Set<String> clientUrls = Set.of(
            "/order/search.html",
            "/logout.html"
    );

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String uri = req.getRequestURI();
        uri = uri.substring(req.getContextPath().length());
        boolean isAdminUrl = adminUrls.contains(uri);
        boolean isClientUrl = clientUrls.contains(uri);
        if(isAdminUrl || isClientUrl) {
            HttpSession session = req.getSession(false);
            if(session != null) {
                User user = (User) session.getAttribute("sessionUser");
                if(user != null) {
                    if((isAdminUrl && user.getRole() == Role.ADMIN) || (isClientUrl && user.getRole() == Role.CLIENT)) {
                        chain.doFilter(req, resp);
                    } else {
                        WebUtil.sendRedirect(req, resp, "/login.html", Map.of("error", "Доступ запрещён"));
                    }
                    return;
                }
            }
            WebUtil.sendRedirect(req, resp, "/login.html", Map.of("error", "Необходима авторизация"));
        } else {
            chain.doFilter(req, resp);
        }
    }
}
