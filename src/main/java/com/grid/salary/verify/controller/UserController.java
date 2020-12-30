package com.grid.salary.verify.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.grid.salary.verify.common.Res;
import com.grid.salary.verify.common.exception.BaseException;
import com.grid.salary.verify.domain.User;
import com.grid.salary.verify.domain.req.SysUserPassWordReq;
import com.grid.salary.verify.domain.req.SysUserReq;
import com.grid.salary.verify.domain.res.SysUserCurrentRes;
import com.grid.salary.verify.service.IUserService;
import com.grid.salary.verify.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * @ProjectName: salary-verify
 * @Package: com.grid.salary.verify.controller
 * @description: 描述
 * @author: zhangyaofang
 * @date: 2020/03/09 08:51
 * @version: 1.09
 */
@RestController
@RequestMapping("/api/sysUser")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService sysUserService;

    @PostMapping("/insert")
    public Res save(@RequestBody SysUserReq req) {
        sysUserService.addUser(req);
        return Res.ok();
    }

    @GetMapping("/getUserById")
    public Res selectUser(@RequestBody SysUserReq req) {
        User sysUser = sysUserService.getById(req.getId());
        return Res.ok(sysUser);
    }

    @PostMapping("/getList")
    public Res selectUserList(@RequestBody SysUserReq req) {
        IPage<User> result = sysUserService.selectUserSearchPage(req);
        return Res.ok(result);
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    @GetMapping("/getCurrentUserInfo")
    public Res getCurrentUserInfo() {
        SysUserCurrentRes res = sysUserService.selectCurrentUserInfo();
        return Res.ok(res);
    }


    /**
     * 修改密码
     *
     * @param userPassWord
     * @return
     */
    @PostMapping("/updatePassword")
    public Res updatePassword(@RequestBody SysUserPassWordReq userPassWord) {
        try {
            String data = "操作失败";
            if (userPassWord.getNewPassword().equals(userPassWord.getConfirmPassword())) {
                data = sysUserService.updatePassword(userPassWord);
                if ("操作成功".equals(data)) {
                    return Res.ok(data);
                }
            } else {
                data = "两次密码输入不一致,请重新输入";
            }
            return Res.error(101, data);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("服务错误", -500);
        }
    }


}
