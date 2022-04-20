package com.lyj.server.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;
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
@TableName("t_employee")
@ExcelTarget(value = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工姓名
     */
    @Excel(name = "员工姓名")
    private String name;

    /**
     * 性别
     */
    @Excel(name = "性别")
    private String gender;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/shanghai")
    @Excel(name = "出生日期", width = 20, format = "yyyy-MM-dd")
    private LocalDate birthday;

    /**
     * 身份证号
     */
    @TableField("idCard")
    @Excel(name = "身份证号", width = 30.0)
    private String idCard;

    /**
     * 婚姻状况
     */
    @Excel(name = "婚姻状况")
    private String wedlock;

    /**
     * 民族
     */
    @TableField("nationId")
    private Integer nationId;

    /**
     * 籍贯
     */
    @Excel(name = "籍贯")
    @TableField("nativePlace")
    private String nativePlace;

    /**
     * 政治面貌
     */
    @TableField("politicId")
    private Integer politicId;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱", width = 30.0)
    private String email;

    /**
     * 电话号码
     */
    @Excel(name = "电话号码", width = 30.0)
    private String phone;

    /**
     * 联系地址
     */
    @Excel(name = "联系地址", width = 40.0)
    private String address;

    /**
     * 所属部门
     */
    @TableField("departmentId")
    private Integer departmentId;

    /**
     * 职称ID
     */
    @TableField("jobLevelId")
    private Integer jobLevelId;

    /**
     * 职位ID
     */
    @TableField("posId")
    private Integer posId;

    /**
     * 聘用形式
     */
    @TableField("engageForm")
    @Excel(name = "聘用形式")
    private String engageForm;

    /**
     * 最高学历
     */
    @TableField("tiptopDegree")
    @Excel(name = "最高学历")
    private String tiptopDegree;

    /**
     * 所属专业
     */
    @Excel(name = "所属转专业")
    private String specialty;

    /**
     * 毕业院校
     */
    @Excel(name = "毕业院校")
    private String school;

    /**
     * 入职日期
     */
    @TableField("beginDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/shanghai")
    @Excel(name = "入职日期", format = "yyyy-MM-dd", width = 20.0)
    private LocalDate beginDate;

    /**
     * 在职状态
     */
    @TableField("workState")
    @Excel(name = "在职状态")
    private String workState;

    /**
     * 工号
     */
    @TableField("workID")
    private String workID;

    /**
     * 合同期限
     */
    @TableField("contractTerm")
    @Excel(name = "合同期限", suffix = "年")
    private Double contractTerm;

    /**
     * 转正日期
     */
    @TableField("conversionTime")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/shanghai")
    @Excel(name = "转正日期", width = 20.0, format = "yyyy-MM-dd")
    private LocalDate conversionTime;

    /**
     * 离职日期
     */
    @TableField("notWorkDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/shanghai")
    private LocalDate notWorkDate;

    /**
     * 合同起始日期
     */
    @TableField("beginContract")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/shanghai")
    @Excel(name = "合同起始日期", width = 20.0, format = "yyyy-MM-dd")
    private LocalDate beginContract;

    /**
     * 合同终止日期
     */
    @TableField("endContract")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/shanghai")
    @Excel(name = "合同终止日期", width = 20.0, format = "yyyy-MM-dd")
    private LocalDate endContract;

    /**
     * 工龄
     */
    @TableField("workAge")
    @Excel(name = "工龄")
    private Integer workAge;

    /**
     * 工资账套ID
     */
    @TableField("salaryId")
    private Integer salaryId;

    /**
     * 民族
     */
    @TableField(exist = false)
    @ExcelEntity(name = "民族")
    private Nation nation;

    /**
     * 政治面貌
     */
    @TableField(exist = false)
    @ExcelEntity(name = "政治面貌")
    private PoliticsStatus politicsStatus;

    /**
     * 部门
     */
    @TableField(exist = false)
    @ExcelEntity(name = "部门")
    private Department department;

    /**
     * 职称
     */
    @TableField(exist = false)
    @ExcelEntity(name = "职称")
    private Joblevel joblevel;

    /**
     * 职位
     */
    @TableField(exist = false)
    @ExcelEntity(name = "职位")
    private Position position;


}
