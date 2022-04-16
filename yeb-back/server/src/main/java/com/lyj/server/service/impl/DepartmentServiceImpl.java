package com.lyj.server.service.impl;

import com.lyj.server.common.RespBean;
import com.lyj.server.pojo.Department;
import com.lyj.server.mapper.DepartmentMapper;
import com.lyj.server.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    /**
     * 获取所有部门
     * @return
     */
    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartments(-1);
    }

    /**
     * 添加部门
     * @param dep
     * @return
     */
    @Override
    public RespBean addDepartment(Department dep) {
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
        if (1==dep.getResult()){
            return RespBean.success("添加成功！",dep);
        }
        return RespBean.error("添加失败!");
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @Override
    public RespBean deleteDep(Integer id) {
        Department dep = new Department();
        dep.setId(id);
        departmentMapper.deleteDep(dep);
        if (-2==dep.getResult()){
            return RespBean.error("该部门下还有子部门，删除失败!");
        }
        if (-1==dep.getResult()){
            return RespBean.error("该部门下还有员工，删除失败!");
        }
        if (1==dep.getResult()){
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
