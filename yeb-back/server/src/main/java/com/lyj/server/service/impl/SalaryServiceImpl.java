package com.lyj.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.server.common.RespPageBean;
import com.lyj.server.pojo.Employee;
import com.lyj.server.pojo.Salary;
import com.lyj.server.mapper.SalaryMapper;
import com.lyj.server.service.ISalaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
@Service
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements ISalaryService {

    @Autowired
    private SalaryMapper salaryMapper;
}
