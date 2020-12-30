package com.grid.salary.verify.security.util;

import com.alibaba.fastjson.JSON;
import com.grid.salary.verify.common.Res;
import com.grid.salary.verify.common.exception.BaseException;
import com.grid.salary.verify.security.SecurityUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description: 描述
 * @author: zhangyaofang
 * @date: 2019/12/19 16:04
 * @version: 1.0
 */
public class SecurityUtil {

    public static void writeJavaScript(Res r, HttpServletResponse response) throws IOException {
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(r));
        printWriter.flush();
    }

    /**
     * 获取Authentication
     */
    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    /**
     * 获取用户信息
     * @return
     */
    public static SecurityUser getUser(){
        try {
            return (SecurityUser) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new BaseException("登录状态过期", HttpStatus.UNAUTHORIZED.value());
        }
    }
}
