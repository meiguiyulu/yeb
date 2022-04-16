package com.lyj.server.mapper;

import com.lyj.server.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 获取除当前登录用户所有操作员
     * @param id
     * @return
     */
    List<Admin> getAllAdmin(@Param("id") Integer id, @Param("keywords") String keywords);
}
