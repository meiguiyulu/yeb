package com.lyj.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyj.server.common.RespBean;
import com.lyj.server.config.security.JwtTokenUtil;
import com.lyj.server.pojo.Admin;
import com.lyj.server.mapper.AdminMapper;
import com.lyj.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param request
     * @return token
     */
    @Override
    public RespBean login(String username, String password, HttpServletRequest request) {
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
        map.put("tokenHead", tokenHeader);
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
}
