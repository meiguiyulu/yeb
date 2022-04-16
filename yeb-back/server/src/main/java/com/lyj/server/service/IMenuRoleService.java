package com.lyj.server.service;

import com.lyj.server.common.RespBean;
import com.lyj.server.pojo.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 更新角色菜单
     *
     * @param rid
     * @param mids
     * @return
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
