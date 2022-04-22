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
@TableName("t_salary")
public class Salary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 基本工资
     */
    @TableField("basicSalary")
    private Integer basicSalary;

    /**
     * 奖金
     */
    private Integer bonus;

    /**
     * 午餐补助
     */
    @TableField("lunchSalary")
    private Integer lunchSalary;

    /**
     * 交通补助
     */
    @TableField("trafficSalary")
    private Integer trafficSalary;

    /**
     * 应发工资
     */
    @TableField("allSalary")
    private Integer allSalary;

    /**
     * 养老金基数
     */
    @TableField("pensionBase")
    private Integer pensionBase;

    /**
     * 养老金比率
     */
    @TableField("pensionPer")
    private Float pensionPer;

    /**
     * 启用时间
     */
    @TableField("createDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/shanghai")
    private Date createDate;

    /**
     * 医疗基数
     */
    @TableField("medicalBase")
    private Integer medicalBase;

    /**
     * 医疗保险比率
     */
    @TableField("medicalPer")
    private Float medicalPer;

    /**
     * 公积金基数
     */
    @TableField("accumulationFundBase")
    private Integer accumulationFundBase;

    /**
     * 公积金比率
     */
    @TableField("accumulationFundPer")
    private Float accumulationFundPer;

    /**
     * 名称
     */
    private String name;


}
