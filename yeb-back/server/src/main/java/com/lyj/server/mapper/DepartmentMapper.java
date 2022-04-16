package com.lyj.server.mapper;

import com.lyj.server.pojo.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllDepartments(Integer parentId);


    /**
     * 添加部门
     * @param dep
     */
    void addDep(Department dep);

    void deleteDep(Department dep);
}
