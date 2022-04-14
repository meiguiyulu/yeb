package com.lyj.server.controller;

import com.lyj.server.common.RespBean;
import com.lyj.server.params.AdminLoginParam;
import com.lyj.server.pojo.Admin;
import com.lyj.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Api(tags = "LoginController")
@Controller
public class LoginController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "登录", notes = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(AdminLoginParam adminLoginParam, HttpServletRequest request) {
        return adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword(), request);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal) {
        if (null == principal) {
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUsername(username);
        /*不返回密码信息*/
        admin.setPassword(null);
        return admin;
    }


    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout() {
        return RespBean.success("注销成功");
    }

}
