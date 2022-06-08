package by.vsu.hotel.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class WebUtil {
    public static void sendRedirect(HttpServletRequest req, HttpServletResponse resp, String url, Map<String, String> params) throws IOException {
        StringBuilder path = new StringBuilder(req.getContextPath());
        path.append(url);
        if(!params.isEmpty()) {
            path.append('?');
            path.append(
                params.entrySet().stream()
                        .map(entry -> entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                        .collect(Collectors.joining("&"))
            );
        }
        resp.sendRedirect(path.toString());
    }

    public static void sendRedirect(HttpServletRequest req, HttpServletResponse resp, String url) throws IOException {
        sendRedirect(req, resp, url, Map.of());
    }
}
