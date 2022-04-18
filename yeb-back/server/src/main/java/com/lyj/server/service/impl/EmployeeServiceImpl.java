package com.lyj.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.server.common.RespPageBean;
import com.lyj.server.pojo.Employee;
import com.lyj.server.mapper.EmployeeMapper;
import com.lyj.server.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 分页获取所有员工
     *
     * @param currentPage
     * @param pageSize
     * @param employee
     * @param dateScope
     * @return
     */
    @Override
    public RespPageBean getEmployeeByPage(Integer currentPage, Integer pageSize, Employee employee, LocalDate[] dateScope) {
        Page<Employee> page = new Page<>(currentPage, pageSize);
        IPage<Employee> employeeIPage = employeeMapper.getEmployeeByPage(page, employee, dateScope);
        return new RespPageBean(employeeIPage.getTotal(), employeeIPage.getRecords());
    }
}
