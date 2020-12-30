package com.grid.salary.verify.controller;

import cn.hutool.core.util.StrUtil;
import com.grid.salary.verify.common.Res;
import com.grid.salary.verify.domain.req.LoginReq;
import com.grid.salary.verify.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 描述
 * @author: zhangyaofang
 * @date: 2020/03/09 08:51
 * @version: 1.0
 */
@RestController
public class IndexController {

    @Autowired
    private IUserService sysUserService;


    /**
     * 登录
     * @param req
     * @param request
     * @return
     */
    @PostMapping(value = "/login")
    public Res login(@RequestBody LoginReq req, HttpServletRequest request) {
        String token = req.getToken();
        if (StrUtil.isNotEmpty(token)) {
            return Res.ok(token);
        }
        return Res.ok(sysUserService.login(req.getUsername(), req.getPassword()));
    }
}
