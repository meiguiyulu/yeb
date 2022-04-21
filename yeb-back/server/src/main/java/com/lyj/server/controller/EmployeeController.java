package com.lyj.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.lyj.server.common.RespBean;
import com.lyj.server.common.RespPageBean;
import com.lyj.server.dto.EmployeeDTO;
import com.lyj.server.pojo.*;
import com.lyj.server.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
@Api(tags = "员工相关")
@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPoliticsStatusService politicsStatusService;

    @Autowired
    private IJoblevelService joblevelService;

    @Autowired
    private INationService nationService;

    @Autowired
    private IPositionService positionService;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation(value = "分页获取所有员工")
    @GetMapping("/")
    public RespPageBean getAllEmployee(@RequestParam(defaultValue = "1") Integer currentPage,
                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                       Employee employee,
                                       LocalDate[] dateScope) {
        return employeeService.getEmployeeByPage(currentPage, pageSize, employee, dateScope);
    }

    @ApiOperation(value = "获取所有政治面貌")
    @GetMapping("/politicsStatus")
    public List<PoliticsStatus> getAllPoliticsStatus() {

        List<PoliticsStatus> politicsStatuses;

        if (redisTemplate.opsForValue().get("politicsStatus") != null) {
            politicsStatuses = (List<PoliticsStatus>) redisTemplate.opsForValue().get("politicsStatus");
        } else {
            politicsStatuses = politicsStatusService.list();
            redisTemplate.opsForValue().set("politicsStatus", politicsStatuses);
        }

        /* 这块可以用一个多线程进行优化 */
        if (redisTemplate.opsForHash().entries("map_politicsStatus") == null) {
            Map<String, Integer> map = new HashMap<>();
            for (PoliticsStatus status : politicsStatuses) {
                map.put(status.getName(), status.getId());
            }
            redisTemplate.opsForHash().putAll("map_politicsStatus", map);
        }
        return politicsStatuses;
    }

    @ApiOperation(value = "获取所有职称")
    @GetMapping("/joblevels")
    public List<Joblevel> getAllJoblevels() {
        List<Joblevel> jobLevels;
        if (redisTemplate.opsForValue().get("jobLevels") != null) {
            jobLevels = (List<Joblevel>) redisTemplate.opsForValue().get("jobLevels");
        } else {
            jobLevels = joblevelService.list();
            redisTemplate.opsForValue().set("jobLevels", jobLevels);
        }

        /* 这块可以用一个多线程进行优化 */
        if (redisTemplate.opsForHash().entries("map_jobLevels") == null) {
            Map<String, Integer> map = new HashMap<>();
            for (Joblevel jobLevel : jobLevels) {
                map.put(jobLevel.getName(), jobLevel.getId());
            }
            redisTemplate.opsForHash().putAll("map_jobLevels", map);
        }

        return jobLevels;
    }

    @ApiOperation(value = "获取所有民族")
    @GetMapping("/nations")
    public List<Nation> getAllNation() {
        List<Nation> nations;
        if (redisTemplate.opsForValue().get("nations") != null) {
            nations = (List<Nation>) redisTemplate.opsForValue().get("nations");
        } else {
            nations = nationService.list();
            redisTemplate.opsForValue().set("nations", nations);
        }

        if (redisTemplate.opsForHash().entries("map_nations") == null) {
            Map<String, Integer> map = new HashMap<>();
            for (Nation nation : nations) {
                map.put(nation.getName(), nation.getId());
            }
            redisTemplate.opsForHash().putAll("map_nations", map);
        }
        return nations;
    }

    @ApiOperation(value = "获取所有职位")
    @GetMapping("/Positions")
    public List<Position> getAllPosition() {
        List<Position> positions;
        if (redisTemplate.opsForValue().get("positions") != null) {
            positions = (List<Position>) redisTemplate.opsForValue().get("positions");
        } else {
            positions = positionService.list();
            redisTemplate.opsForValue().set("positions", positions);
        }
        if (redisTemplate.opsForHash().entries("map_positions") == null) {
            Map<String, Integer> map = new HashMap<>();
            for (Position position : positions) {
                map.put(position.getName(), position.getId());
            }
            redisTemplate.opsForHash().putAll("map_positions", map);
        }
        return positions;
    }

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/deps")
    public List<Department> getAllDepartment() {

        List<Department> departments;
        if (redisTemplate.opsForValue().get("departments") != null) {
            departments = (List<Department>) redisTemplate.opsForValue().get("departments");
        } else {
            departments = departmentService.list();
            redisTemplate.opsForValue().set("departments", departments);
        }

        if (redisTemplate.opsForHash().entries("map_departments") == null) {
            Map<String, Integer> map = new HashMap<>();
            for (Department department : departments) {
                map.put(department.getName(), department.getId());
            }
            redisTemplate.opsForHash().putAll("map_departments", map);
        }

        return departmentService.getAllDepartments();
    }

    @ApiOperation(value = "获取工号")
    @GetMapping("/maxWorkID")
    public RespBean maxWorkID() {
        return employeeService.maxWorkId();
    }

    @ApiOperation(value = "添加员工")
    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee) {
        return employeeService.addEmp(employee);
    }

    @ApiOperation(value = "更新员工")
    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee) {
        if (employeeService.updateById(employee)) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除员工")
    @DeleteMapping("/{id}")
    public RespBean deleteEmp(@PathVariable Integer id) {
        if (employeeService.removeById(id)) {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "导出员工数据")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    public void exportDate(HttpServletResponse response) throws Exception {
        List<EmployeeDTO> employees = employeeService.getEmployee(null);
        ExportParams params = new ExportParams(null, "员工信息", ExcelType.HSSF); // 第一个参数设置为null就没有标题行了
        Workbook workbook = ExcelExportUtil.exportExcel(params, EmployeeDTO.class, employees);
        response.setHeader("content-type", "application/octet-stream");
        //防止中文乱码
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("员工表.xls", "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
    }

    @ApiOperation(value = "导入员工数据")
    @PostMapping(value = "/import")
    public RespBean importEmployee(MultipartFile file) throws Exception {

        System.out.println("文件名称：" + file.getOriginalFilename());

        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(0); // 标题列占几行
        importParams.setHeadRows(1); // header占几行

        List<EmployeeDTO> employeeDTOs = ExcelImportUtil.importExcel(file.getInputStream(), EmployeeDTO.class, importParams);
        List<Employee> employees = new ArrayList<>();

        Map<Object, Object> nations = redisTemplate.opsForHash().entries("map_nations");
        Map<Object, Object> politicsStatus = redisTemplate.opsForHash().entries("map_politicsStatus");
        Map<Object, Object> jobLevels = redisTemplate.opsForHash().entries("map_jobLevels");
        Map<Object, Object> positions = redisTemplate.opsForHash().entries("map_positions");
        Map<Object, Object> departments = redisTemplate.opsForHash().entries("map_departments");

        for (EmployeeDTO employeeDTO : employeeDTOs) {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDTO, employee);
            employee.setNationId((Integer) nations.get(employeeDTO.getNation()));
            employee.setPoliticId((Integer) politicsStatus.get(employeeDTO.getPoliticsStatus()));
            employee.setJobLevelId((Integer) jobLevels.get(employeeDTO.getJobLevel()));
            employee.setPosId((Integer) positions.get(employeeDTO.getPosition()));
            employee.setDepartmentId((Integer) departments.get(employeeDTO.getDepartment()));

            /* 工号 */
            Object obj = maxWorkID().getObj();
            employee.setWorkID(obj.toString());

            System.out.println("===========================================");
            System.out.println(employee);
            employees.add(employee);
        }
        if (employeeService.saveBatch(employees)) {
            return RespBean.success("导入成功");
        }
        return RespBean.error("导入失败");
    }
}
