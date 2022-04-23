package com.lyj.server.controller;

import com.lyj.server.pojo.Admin;
import com.lyj.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "聊天相关")
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "获取所有用户")
    @GetMapping("/admin")
    public List<Admin> getAllAdmin(String keyword) {
        return adminService.getAllAdmin(keyword);
    }

}
