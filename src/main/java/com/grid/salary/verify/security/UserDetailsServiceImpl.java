package com.grid.salary.verify.security;

import cn.hutool.core.util.ObjectUtil;
import com.grid.salary.verify.domain.User;
import com.grid.salary.verify.domain.req.SysUserReq;
import com.grid.salary.verify.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * @description: 用户身份验证
 * @author: zhangyaofang
 * @date: 2019/12/19 16:26
 * @version: 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUserReq sysUser = new SysUserReq();
        sysUser.setLoginName(username);
        User user = userService.findSecurityUserByUser(sysUser);
        if (ObjectUtil.isNull(user)) {
            log.info("登录用户：" + username + " 不存在.");
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
//        Collection<? extends GrantedAuthority> authorities = getUserAuthorities(user.getId(),systemType);
        return new SecurityUser(user.getId(), username, user.getPassword(), null, LoginType.normal);

    }

    /**
     * 封装 根据用户Id获取权限
     *
     * @param userId
     * @return
     */
//    private Collection<? extends GrantedAuthority> getUserAuthorities(int userId, String type) {
//        // 获取用户拥有的角色
//        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
//        // 权限集合
//        Set<String> permissions = userService.findPermsByUserId(userId);
//        // 角色集合
//        Set<String> roleIds = userService.findRoleIdByUserId(userId);
//        permissions.addAll(roleIds);
//        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(permissions.toArray(new String[0]));
//        return authorities;
//    }
}
