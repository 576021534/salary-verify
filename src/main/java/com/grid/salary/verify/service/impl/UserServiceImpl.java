package com.grid.salary.verify.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grid.salary.verify.common.exception.BaseException;
import com.grid.salary.verify.constant.Const;
import com.grid.salary.verify.domain.User;
import com.grid.salary.verify.domain.req.SysUserPassWordReq;
import com.grid.salary.verify.domain.req.SysUserReq;
import com.grid.salary.verify.domain.res.SysUserCurrentRes;
import com.grid.salary.verify.mapper.UserMapper;
import com.grid.salary.verify.security.SecurityUser;
import com.grid.salary.verify.security.util.JwtUtil;
import com.grid.salary.verify.security.util.SecurityUtil;
import com.grid.salary.verify.service.IUserService;
import com.grid.salary.verify.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountExpiredException;
import java.util.Date;

/**
 * @ProjectName: salary-verify
 * @Package: com.grid.salary.verify.service.impl
 * @description: 描述
 * @author: zhangyaofang
 * @date: 2020/03/09 08:51
 * @version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper sysUserMapper;

    @Autowired
    private IUserService sysUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public int addUser(SysUserReq sysUserReq) {
        Date now = Utils.getNow();
        sysUserReq.setCreateTime(now);
        sysUserReq.setUpdateTime(now);
        User sysUser = new User();
        BeanUtils.copyProperties(sysUserReq, sysUser);
        return sysUserMapper.insert(sysUser);

    }

    @Override
    public IPage<User> selectUserSearchPage(SysUserReq sysUserReq) {
        Page<User> page = new Page<>(sysUserReq.getPageNum(), sysUserReq.getPageSize());
        IPage<User> result = sysUserMapper.selectPage(page, null);//第二个参数可以传wrapper条件查询
        return result;
    }

    @Override
    public SysUserCurrentRes selectCurrentUserInfo() {
        User sysUser = sysUserMapper.selectById(SecurityUtil.getUser().getUserId());
        SysUserCurrentRes res = new SysUserCurrentRes();
        if (sysUser != null) {
            res.setId(sysUser.getId());
            res.setUserName(sysUser.getUserName());
        }
        return res;
    }

    @Override
    public String login(String username, String password) {
        //用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername()去验证用户名和密码，
            // 如果正确，则存储该用户名密码到security 的 context中
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new BaseException("用户名或密码错误", 402);
            } else if (e instanceof DisabledException) {
                throw new BaseException("账户被禁用", 402);
            } else if (e instanceof AccountExpiredException) {
                throw new BaseException("账户过期无法验证", 402);
            } else {
                e.printStackTrace();
                throw new BaseException("账户被锁定,无法登录", 402);
            }
        }
        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        SecurityUser userDetail = (SecurityUser) authentication.getPrincipal();
        return jwtUtil.generateToken(userDetail);
    }



    @Override
    public User findSecurityUserByUser(SysUserReq sysUserReq) {
        LambdaQueryWrapper<User> select = Wrappers.<User>lambdaQuery()
                .select(User::getId, User::getLoginName, User::getPassword);
        if (StrUtil.isNotEmpty(sysUserReq.getLoginName())) {
            select.eq(User::getLoginName, sysUserReq.getLoginName());
        } /*else if (StrUtil.isNotEmpty(sysUserReq.getPhone())) {
            select.eq(User::getPhone, sysUserReq.getPhone());
        }*/ else if (ObjectUtil.isNotNull(sysUserReq.getId()) &&
                !Const.STRING_ZERO.equals(sysUserReq.getId())) {
            select.eq(User::getId, sysUserReq.getId());
        }
        return baseMapper.selectOne(select);
    }


    /**
     * 修改密码
     *
     * @param userPassWord
     * @return
     */
    @Override
    public String updatePassword(SysUserPassWordReq userPassWord) {
        User sysUser = this.getById(SecurityUtil.getUser().getUserId());
        boolean matches = passwordEncoder.matches(userPassWord.getOldPassword(), sysUser.getPassword());
        if (matches) {
            String encode = passwordEncoder.encode(userPassWord.getNewPassword());
            sysUser.setPassword(encode);
            sysUserMapper.updateById(sysUser);
            return "操作成功";
        } else {
            return "旧密码错误";
        }
    }


}