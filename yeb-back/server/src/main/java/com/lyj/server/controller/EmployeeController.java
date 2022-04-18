package com.lyj.server.controller;


import com.lyj.server.common.RespPageBean;
import com.lyj.server.pojo.Employee;
import com.lyj.server.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
@Api(tags = "员工相关")
@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @ApiOperation(value = "分页获取所有员工")
    @GetMapping("/")
    public RespPageBean getAllEmployee(@RequestParam(defaultValue = "1") Integer currentPage,
                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                       Employee employee,
                                       LocalDate[] dateScope) {
        return employeeService.getEmployeeByPage(currentPage, pageSize, employee, dateScope);
    }

/*    @ApiOperation(value = "获取所有政治面貌")*/

}
