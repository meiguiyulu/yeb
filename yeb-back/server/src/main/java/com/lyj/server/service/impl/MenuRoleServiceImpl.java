package com.lyj.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyj.server.common.RespBean;
import com.lyj.server.pojo.MenuRole;
import com.lyj.server.mapper.MenuRoleMapper;
import com.lyj.server.service.IMenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    /**
     * 更新角色菜单
     *
     * @param rid
     * @param mids
     * @return
     */
    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        /**
         * 因为此操作可能包含 插入、删除、更新操作，很难进行判断
         * 简便方法：清空角色id的菜单；插入菜单 (两个操作：需要开启事务)
         */
        QueryWrapper<MenuRole> wrapper = new QueryWrapper<MenuRole>()
                .eq("rid", rid);
        menuRoleMapper.delete(wrapper);
        if (null == mids || mids.length == 0) {
            return RespBean.success("更新成功");
        }
        if (menuRoleMapper.insertRecords(rid, mids) == mids.length) {
            return RespBean.success("更新成功");
        }
        return RespBean.success("更新失败");
    }
}
