package com.grid.salary.verify.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.grid.salary.verify.domain.User;
import com.grid.salary.verify.domain.req.SysUserPassWordReq;
import com.grid.salary.verify.domain.req.SysUserReq;
import com.grid.salary.verify.domain.res.SysUserCurrentRes;


/**
 * @ProjectName: salary-verify
 * @Package: com.grid.salary.verify.service
 * @description: 描述
 * @author: zhangyaofang
 * @date: 2020/03/09 08:51
 * @version: 1.0
 */
public interface IUserService extends IService<User>{

    public int addUser(SysUserReq sysUserReq);

    public IPage<User> selectUserSearchPage(SysUserReq sysUserReq);

    SysUserCurrentRes selectCurrentUserInfo();

    /**
     * 账户密码登录
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);



    /**
     * 通过用户去查找用户(id/用户名/手机号)
     * @param sysUserReq
     * @return
     */
    User findSecurityUserByUser(SysUserReq sysUserReq);

    /**
     * 修改密码
     * @param userPassWord
     * @return
     */
    String updatePassword(SysUserPassWordReq userPassWord);

}
