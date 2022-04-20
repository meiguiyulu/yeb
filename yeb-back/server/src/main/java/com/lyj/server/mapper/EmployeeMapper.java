package com.lyj.server.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.server.common.RespPageBean;
import com.lyj.server.dto.EmployeeDTO;
import com.lyj.server.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 分页获取所有员工
     *
     * @param page
     * @param employee
     * @param dateScope
     * @return
     */
    IPage<Employee> getEmployeeByPage(Page<Employee> page, @Param("employee") Employee employee, @Param("dateScope") LocalDate[] dateScope);

    /**
     * 获取员工数据
     * @param id
     * @return
     */
    List<EmployeeDTO> getEmployee(Integer id);
}
