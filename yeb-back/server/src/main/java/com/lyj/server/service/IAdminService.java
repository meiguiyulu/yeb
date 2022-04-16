package com.lyj.server.service;

import com.lyj.server.common.RespBean;
import com.lyj.server.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyj.server.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 登录功能
     *
     * @param username
     * @param password
     * @param code
     * @param request
     * @return token
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * 获取当前登录用户信息
     *
     * @param username
     * @return
     */
    Admin getAdminByUsername(String username);

    /**
     * 根据用户Id查询权限
     *
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);

    /**
     * 获取除当前登录用户所有操作员
     *
     * @return
     */
    List<Admin> getAllAdmin(String keywords);

    /**
     * 更新操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    RespBean updateAdminRole(Integer adminId, Integer[] rids);
}
