package com.lyj.server.service;

import com.lyj.server.common.RespBean;
import com.lyj.server.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
public interface IDepartmentService extends IService<Department> {

    /**
     * 获取所有部门
     *
     * @return
     */
    List<Department> getAllDepartments();

    /**
     * 添加部门
     *
     * @param department
     * @return
     */
    RespBean addDepartment(Department department);

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    RespBean deleteDep(Integer id);
}
