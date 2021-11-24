package com.flight.controller;

import com.flight.interceptor.Authorization;
import com.flight.result.ApiResult;
import com.flight.util.UserUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunlongfei
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public ApiResult<?> login(HttpServletRequest request,
        @NotEmpty(message = "登录用户不能为空") @RequestParam("users") String usersString) {

        HttpSession session = request.getSession();
        session.setAttribute("users", usersString);
        session.setMaxInactiveInterval(24 * 60 * 60);
        Authorization.putSession(session.getId(), session);

        return ApiResult.success();
    }
}
