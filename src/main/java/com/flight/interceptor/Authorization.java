package com.flight.interceptor;

import com.flight.util.UserUtil;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 权限拦截器
 * @author sunlongfei
 */
@Component
public class Authorization implements HandlerInterceptor {

    private static Map<String, HttpSession> sMap = new HashMap<>();

    public static void putSession(String id, HttpSession session) {
        sMap.put(id, session);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new Exception("缺少cookie");
        }
        HttpSession session = sMap.get(cookies[0].getValue());
        UserUtil.setUsers((String) session.getAttribute("users"));

        return true;
    }
}
