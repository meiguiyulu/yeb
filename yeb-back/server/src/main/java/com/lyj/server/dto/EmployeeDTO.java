package com.lyj.server.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@ExcelTarget(value = "EmployeeDTO")
public class EmployeeDTO implements Serializable {

    @Excel(name = "员工姓名")
    private String name;

    @Excel(name = "性别")
    private String gender;

    @Excel(name = "出生日期", width = 20, format = "yyyy-MM-dd")
    private LocalDate birthday;

    @Excel(name = "身份证号", width = 30.0)
    private String idCard;

    @Excel(name = "籍贯")
    private String nativePlace;

    @Excel(name = "婚姻状况")
    private String wedlock;

    @Excel(name = "邮箱", width = 30.0)
    private String email;

    @Excel(name = "电话号码", width = 30.0)
    private String phone;

    @Excel(name = "联系地址", width = 40.0)
    private String address;

    @Excel(name = "聘用形式")
    private String engageForm;

    @Excel(name = "所属转专业")
    private String specialty;

    @Excel(name = "毕业院校")
    private String school;

    @Excel(name = "入职日期", format = "yyyy-MM-dd", width = 20.0)
    private LocalDate beginDate;

    @Excel(name = "在职状态")
    private String workState;

    @Excel(name = "合同期限", suffix = "年")
    private Double contractTerm;

    @Excel(name = "转正日期", width = 20.0, format = "yyyy-MM-dd")
    private LocalDate conversionTime;

    @Excel(name = "合同起始日期", width = 20.0, format = "yyyy-MM-dd")
    private LocalDate beginContract;

    @Excel(name = "合同终止日期", width = 20.0, format = "yyyy-MM-dd")
    private LocalDate endContract;

    @Excel(name = "工龄")
    private Integer workAge;

    @Excel(name = "员工民族")
    private String nation;

    @Excel(name = "政治面貌")
    private String politicsStatus;

    @Excel(name = "部门")
    private String department;

    @Excel(name = "职称")
    private String jobLevel;

    @Excel(name = "职位")
    private String position;

}
