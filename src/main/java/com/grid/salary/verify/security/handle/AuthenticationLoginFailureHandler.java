package com.grid.salary.verify.security.handle;


import com.grid.salary.verify.common.Res;
import com.grid.salary.verify.security.util.SecurityUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 登录失败处理器
 * @author: zhangyaofang
 * @date: 2019/12/19 16:22
 * @version: 1.0
 */
@Component
public class AuthenticationLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String message;

        if (exception instanceof AuthenticationException) {
            message = exception.getMessage();
        } else {
            message = "认证失败，请联系网站管理员！";
        }
        response.setContentType("application/json;charset=utf-8");
        SecurityUtil.writeJavaScript(Res.error(message), response);
    }
}
