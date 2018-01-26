package com.admin.luntan.util;

import com.admin.luntan.entity.TabUserEntity;

/**
 * Created by zhanghaichao on 2018/1/25.
 */
public class Global {
    private static final String USER_SESSION_NAME = "USER_SESSION";

    /**
     * 保存用户session信息
     *
     * @param user
     */
    public static void setUserSession(TabUserEntity user) {
        SessionUtil.setRequestAttribute(USER_SESSION_NAME, user);
    }

    /**
     * 获取用户session
     *
     * @return
     */
    public static TabUserEntity getUserSession() {
        TabUserEntity user = (TabUserEntity) SessionUtil.getRequestAttribute(USER_SESSION_NAME);
        return user;
    }

}
