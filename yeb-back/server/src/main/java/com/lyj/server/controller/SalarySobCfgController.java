package com.lyj.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lyj.server.common.RespBean;
import com.lyj.server.common.RespPageBean;
import com.lyj.server.pojo.Employee;
import com.lyj.server.pojo.Salary;
import com.lyj.server.service.IEmployeeService;
import com.lyj.server.service.ISalaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "员工工资相关")
@RestController
@RequestMapping("/salary/sobcfg")
public class SalarySobCfgController {

    @Autowired
    private ISalaryService salaryService;

    @Autowired
    private IEmployeeService employeeService;

    @ApiOperation("获取所有工资账套")
    @GetMapping("/salaries")
    public List<Salary> getAllSalaries() {
        return salaryService.list();
    }

    @ApiOperation("获取所有员工账套")
    @GetMapping("/")
    public RespPageBean getEmployeeWithSalary(@RequestParam(defaultValue = "1") Integer currentPage,
                                              @RequestParam(defaultValue = "10") Integer pageSize) {
        return employeeService.getEmployeeWithSalary(currentPage, pageSize);
    }

    @ApiOperation(value = "更新员工账套")
    @PutMapping("/")
    public RespBean updateEmployeeSalary(Integer eid, Integer sid) {
        UpdateWrapper<Employee> wrapper = new UpdateWrapper<Employee>()
                .set("salaryId", sid)
                .eq("id", eid);
        if (employeeService.update(wrapper)) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

}
