package com.lyj.server.mapper;

import com.lyj.server.pojo.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
@Mapper
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * 更新角色菜单
     *
     * @param rid
     * @param mids
     * @return
     */
    Integer insertRecords(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
