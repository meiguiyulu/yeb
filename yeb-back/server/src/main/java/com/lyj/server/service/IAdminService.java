package com.lyj.server.service;

import com.lyj.server.common.RespBean;
import com.lyj.server.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 登录功能
     * @param username
     * @param password
     * @param request
     * @return token
     */
    RespBean login(String username, String password, HttpServletRequest request);

    /**
     * 获取当前登录用户信息
     * @param username
     * @return
     */
    Admin getAdminByUsername(String username);
}
