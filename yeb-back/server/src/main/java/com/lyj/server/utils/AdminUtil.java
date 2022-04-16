package com.lyj.server.utils;

import com.lyj.server.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


public class AdminUtil {

    /* 当前登录用户 */
    public static Admin getCurrentAdmin() {
        return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
