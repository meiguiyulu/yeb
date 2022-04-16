package com.lyj.server.controller;


import com.lyj.server.common.RespBean;
import com.lyj.server.pojo.Position;
import com.lyj.server.service.IPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
@Api(tags = "职位相关")
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    private IPositionService positionService;

    @ApiOperation(value = "获取所有职位信息")
    @GetMapping("/")
    public List<Position> getAllPositions() {
        return positionService.list();
    }

    @ApiOperation(value = "添加职位信息")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position) {
        position.setCreateDate(new Date());
        position.setModifyDate(new Date());
        if (positionService.save(position)) {
            return RespBean.success("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @ApiOperation(value = "更新职位信息")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position) {
        position.setModifyDate(new Date());
        if (positionService.updateById(position)) {
            return RespBean.success("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @ApiOperation(value = "删除职位")
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable("id") Integer id) {
        if (positionService.removeById(id)) {
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(value = "批量删除职位")
    @DeleteMapping("/")
    public RespBean deletePositionByIds(Integer[] ids) {
        if (positionService.removeByIds(Arrays.asList(ids))) {
            return RespBean.success("批量删除成功!");
        }
        return RespBean.error("批量删除失败!");
    }

}
