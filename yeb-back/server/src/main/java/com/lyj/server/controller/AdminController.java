package com.lyj.server.controller;


import com.lyj.server.common.RespBean;
import com.lyj.server.pojo.Admin;
import com.lyj.server.pojo.Role;
import com.lyj.server.service.IAdminService;
import com.lyj.server.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
@Api(tags = "用户相关")
@RestController
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "获取除当前登录用户所有操作员")
    @GetMapping("/")
    public List<Admin> getAllAdmin(String keywords) {
        return adminService.getAllAdmin(keywords);
    }

    @ApiOperation("更新操作员")
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin) {
        if (adminService.updateById(admin)) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除操作员")
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id) {
        if (adminService.removeById(id)) {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/roles")
    public List<Role> getAllRole() {
        return roleService.list();
    }

    @ApiOperation(value = "更新操作员角色")
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        return adminService.updateAdminRole(adminId, rids);
    }

}
