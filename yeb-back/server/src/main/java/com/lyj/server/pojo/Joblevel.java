package com.lyj.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_joblevel")
public class Joblevel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 职称名称
     */
    private String name;

    /**
     * 职称等级
     */
    @TableField("titleLevel")
    private String titleLevel;

    /**
     * 创建时间
     */
    @TableField("createDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/shanghai")
    private Date createDate;

    /**
     * 修改时间
     */
    @TableField("modifyDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/shanghai")
    private Date modifyDate;

    /**
     * 是否启用
     */
    private Boolean enabled;


}
