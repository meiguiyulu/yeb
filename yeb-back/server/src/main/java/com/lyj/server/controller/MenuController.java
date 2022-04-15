package com.lyj.server.controller;


import com.lyj.server.pojo.Menu;
import com.lyj.server.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
@RestController
@RequestMapping("/system/cfg")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation("通过用户Id查询菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenusByAdminId() {
        /**
         * 这里没有传递用户Id的原因在于如果用户成功登录
         * 那我们可以在后端获取用户的相关信息 不需要前端进行传递了
         */
        return menuService.getMenusByAdminId();
    }


}
