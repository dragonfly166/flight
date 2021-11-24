package com.flight.util;

import com.flight.domain.dao.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 * @author sunlongfei
 */
public class UserUtil {

    private static ThreadLocal<List<User>> users = new ThreadLocal<>();

    /**
     * 设置登录用户
     * @param users
     *          所有登录用户信息
     */
    public static void setUsers(String users) {
        Gson gson = new Gson();
        UserUtil.users.set(gson.fromJson(users, new TypeToken<List<User>>() {}.getType()));
    }

    /**
     * 获取登录用户
     * @return 登录用户
     */
    public static List<User> getUsers() {
        return users.get();
    }
}
