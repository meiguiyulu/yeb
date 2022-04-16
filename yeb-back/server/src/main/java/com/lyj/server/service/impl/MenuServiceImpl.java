package com.lyj.server.service.impl;

import com.lyj.server.pojo.Admin;
import com.lyj.server.pojo.Menu;
import com.lyj.server.mapper.MenuMapper;
import com.lyj.server.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 通过用户Id查询菜单列表
     *
     * @return
     */
    @Override
    public List<Menu> getMenusByAdminId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = (Admin) authentication.getPrincipal();
        Integer adminId = admin.getId();
        /**
         * 因为菜单不是经常需要改动的 但是需要经常查询
         * 所以可以将菜单的相关数据存放在 redis 缓存中
         */
        List<Menu> menus = (List<Menu>) redisTemplate.opsForValue().get("menu_" + adminId);
        if (CollectionUtils.isEmpty(menus)) {
            menus = menuMapper.getMenusByAdminId(adminId);
            redisTemplate.opsForValue().set("menu_" + adminId, menus);
        }

        return menus;
    }


    /**
     * 根据角色获取菜单列表
     *
     * @return
     */
    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }

    /**
     * 查询所有菜单
     * @return
     */
    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }
}
