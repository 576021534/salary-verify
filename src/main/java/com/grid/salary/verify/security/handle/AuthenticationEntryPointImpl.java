package com.grid.salary.verify.security.handle;


import com.grid.salary.verify.common.Res;
import com.grid.salary.verify.security.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @description: 认证失败处理类 返回401
 * @author: zhangyaofang
 * @date: 2019/12/19 15:54
 * @version: 1.0
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationEntryPointImpl.class);

    private static final long serialVersionUID = 5621375301930938925L;


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("请求访问: " + request.getRequestURI() + " 接口， 认证失败，无法访问系统资源.");
        SecurityUtil.writeJavaScript(Res.error(401,"请求访问:" + request.getRequestURI() + "接口,认证失败,无法访问系统资源"),response);
    }
}
