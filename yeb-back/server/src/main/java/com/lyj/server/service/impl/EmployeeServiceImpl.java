package com.lyj.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyj.server.common.MailConstants;
import com.lyj.server.common.RespBean;
import com.lyj.server.common.RespPageBean;
import com.lyj.server.dto.EmployeeDTO;
import com.lyj.server.mapper.MailLogMapper;
import com.lyj.server.pojo.Employee;
import com.lyj.server.mapper.EmployeeMapper;
import com.lyj.server.pojo.MailLog;
import com.lyj.server.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MailLogMapper mailLogMapper;


    /**
     * 分页获取所有员工
     *
     * @param currentPage
     * @param pageSize
     * @param employee
     * @param dateScope
     * @return
     */
    @Override
    public RespPageBean getEmployeeByPage(Integer currentPage, Integer pageSize, Employee employee, LocalDate[] dateScope) {
        Page<Employee> page = new Page<>(currentPage, pageSize);
        IPage<Employee> employeeIPage = employeeMapper.getEmployeeByPage(page, employee, dateScope);
        return new RespPageBean(employeeIPage.getTotal(), employeeIPage.getRecords());
    }

    /**
     * 获取工号
     *
     * @return
     */
    @Override
    public RespBean maxWorkId() {
        QueryWrapper<Employee> wrapper = new QueryWrapper<Employee>()
                .select("max(workID)");
        List<Map<String, Object>> list = employeeMapper.selectMaps(wrapper);
        Object o = list.get(0).get("max(workID)");

        return RespBean.success(null, String.format("%08d", Integer.parseInt(o.toString()) + 1));
    }

    /**
     * 添加员工
     *
     * @param employee
     * @return
     */
    @Override
    public RespBean addEmp(Employee employee) {
        // 处理合同期限 保留两位小数 年份
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days / 365.00)));

        if (employee.getWorkID() == null) {
            System.out.println("工号为空");
            Object obj = maxWorkId().getObj();
            employee.setWorkID(obj.toString());
        }

        if (employeeMapper.insert(employee) == 1) {

            // 数据库记录RabbitMQ发送的消息
            String msgId = UUID.randomUUID().toString();
            MailLog mailLog = new MailLog();
            mailLog.setMsgId(msgId);
            mailLog.setEid(employee.getId());
            mailLog.setStatus(0);
            mailLog.setRouteKey(MailConstants.MAIL_ROUTING_KEY);
            mailLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            mailLog.setCount(0);
            mailLog.setTryTime(LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT));
            mailLog.setCreateTime(new Date());
            mailLog.setUpdateTime(new Date());
            mailLogMapper.insert(mailLog);

            Employee emp = employeeMapper.selectById(employee.getId());

            /* 发送邮件功能 */
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,
                    MailConstants.MAIL_ROUTING_KEY, emp,
                    new CorrelationData(msgId));

            return RespBean.success("添加成功");
        }
        return RespBean.success("添加失败");
    }

    /**
     * 获取员工数据
     *
     * @param id
     * @return
     */
    @Override
    public List<EmployeeDTO> getEmployee(Integer id) {

        return employeeMapper.getEmployee(id);
    }

    /**
     * 获取所有员工账套
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public RespPageBean getEmployeeWithSalary(Integer currentPage, Integer pageSize) {
        Page<Employee> page = new Page<>(currentPage, pageSize);
        IPage<Employee> employeeIPage = employeeMapper.getEmployeeWithSalary(page);
        RespPageBean respPageBean = new RespPageBean(employeeIPage.getTotal(), employeeIPage.getRecords());
        return respPageBean;
    }
}
