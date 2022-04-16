package com.lyj.server.mapper;

import com.lyj.server.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户Id查询权限
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);
}
