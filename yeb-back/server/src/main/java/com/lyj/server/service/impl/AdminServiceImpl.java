package com.lyj.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lyj.server.common.RespBean;
import com.lyj.server.config.security.JwtTokenUtil;
import com.lyj.server.mapper.RoleMapper;
import com.lyj.server.pojo.Admin;
import com.lyj.server.mapper.AdminMapper;
import com.lyj.server.pojo.Role;
import com.lyj.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LiuYunJie
 * @since 2022-04-13
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param code
     * @param request
     * @return token
     */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        /*        *//* 从session中读取验证码的数据 *//*
        String captcha = (String) request.getSession().getAttribute("captcha");*/
        // 从redis中获取验证码的数据
        String captcha = (String) redisTemplate.opsForValue().get("kaptcha");

        if (StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)) {
            return RespBean.error("验证码输入错误, 请重新输入!");
        }

        /* 登录 */
        UserDetails user = userDetailsService.loadUserByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            return RespBean.error("用户名或密码错误");
        }
        if (!user.isEnabled()) {
            return RespBean.error("账号被禁用，请联系管理员!");
        }

        /*更新security登录用户对象*/
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user
                , null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        /*生成token*/
        String token = jwtTokenUtil.generateToken(user);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("tokenHead", tokenHead);
        return RespBean.success("登陆成功", map);
    }

    /**
     * 获取当前登录用户信息
     *
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUsername(String username) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<Admin>()
                .eq("username", username)
                .eq("enabled", true);
        Admin admin = adminMapper.selectOne(wrapper);
        return admin;
    }

    /**
     * 根据用户Id查询权限
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }
}
