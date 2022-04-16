package com.lyj.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyj.server.common.RespBean;
import com.lyj.server.pojo.Menu;
import com.lyj.server.pojo.MenuRole;
import com.lyj.server.pojo.Role;
import com.lyj.server.service.IMenuRoleService;
import com.lyj.server.service.IMenuService;
import com.lyj.server.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "角色相关")
@RestController
@RequestMapping("/system/basic/permission")
public class PermissionController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IMenuRoleService menuRoleService;

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.list();
    }

    @ApiOperation(value = "添加角色")
    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role) {
        /* Security要求ROLE_开头 */
        if (!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        if (roleService.save(role)) {
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRole(@PathVariable Integer rid){
        if (roleService.removeById(rid)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/menus")
    public List<Menu> getAllMenu() {
        return menuService.getAllMenus();
    }

    @ApiOperation(value = "根据角色Id查询菜单id")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMidById(@PathVariable("rid") Integer rid) {
        QueryWrapper<MenuRole> wrapper = new QueryWrapper<MenuRole>()
                .eq("rid", rid);
        List<MenuRole> menuRoles = menuRoleService.list(wrapper);
        List<Integer> menuId = new ArrayList<>();
        for (MenuRole menuRole : menuRoles) {
            menuId.add(menuRole.getMid());
        }

        /**
         * List<Integer> menuId = menuRoles.stream().map(MenuRole::getMid).collect(Collectors.toList());
         */

        return menuId;
    }

    @ApiOperation(value = "更新角色菜单")
    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        return menuRoleService.updateMenuRole(rid, mids);
    }

}
