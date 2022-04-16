package com.lyj.server.controller;


import com.lyj.server.common.RespBean;
import com.lyj.server.pojo.Joblevel;
import com.lyj.server.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.lang.reflect.Array;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {

    @Autowired
    private IJoblevelService jobService;

    @ApiOperation(value = "获取所有职称")
    @GetMapping("/")
    public List<Joblevel> getAllJobLevels() {
        return jobService.list();
    }

    @ApiOperation(value = "添加职称")
    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody Joblevel joblevel) {
        joblevel.setCreateDate(new Date());
        joblevel.setModifyDate(new Date());
        if (jobService.save(joblevel)) {
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "更新职称")
    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody Joblevel joblevel) {
        joblevel.setModifyDate(new Date());
        if (jobService.updateById(joblevel)) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除职称")
    @DeleteMapping("/{id}")
    public RespBean deleteJobLevel(@PathVariable Integer id) {
        if (jobService.removeById(id)) {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping("/")
    public RespBean deleteJobLevelByIds(Integer[] ids) {
        if (jobService.removeByIds(Arrays.asList(ids))) {
            return RespBean.success("批量删除成功");
        }
        return RespBean.error("批量删除失败");
    }

}
