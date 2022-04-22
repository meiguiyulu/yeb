package com.lyj.server.service;

import com.lyj.server.common.RespBean;
import com.lyj.server.common.RespPageBean;
import com.lyj.server.dto.EmployeeDTO;
import com.lyj.server.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 分页获取所有员工
     *
     * @param currentPage
     * @param pageSize
     * @param employee
     * @param dateScope
     * @return
     */
    RespPageBean getEmployeeByPage(Integer currentPage, Integer pageSize, Employee employee, LocalDate[] dateScope);

    /**
     * 获取工号
     *
     * @return
     */
    RespBean maxWorkId();

    /**
     * 添加员工
     *
     * @param employee
     * @return
     */
    RespBean addEmp(Employee employee);

    /**
     * 获取员工数据
     *
     * @return
     */
    List<EmployeeDTO> getEmployee(Integer id);

    /**
     * 获取所有员工账套
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    RespPageBean getEmployeeWithSalary(Integer currentPage, Integer pageSize);
}
