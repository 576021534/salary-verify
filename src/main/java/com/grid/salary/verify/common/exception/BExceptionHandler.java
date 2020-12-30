package com.grid.salary.verify.common.exception;


import com.grid.salary.verify.common.Res;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;

/**
 * @description: 异常处理
 * @author: zhangyaofang
 * @date: 2020/03/09 08:51
 * @version: 1.0
 */
@RestControllerAdvice
public class BExceptionHandler {


    private static final Logger log = LoggerFactory.getLogger(BExceptionHandler.class);

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BaseException.class)
    public Res handleRRException(BaseException e) {
        return Res.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Res handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return Res.error(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Res handleDuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return Res.error(300, "数据库中已存在该记录");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Res handleAuthorizationException(AccessDeniedException e) {
        log.error(e.getMessage());
        return Res.error(403, "没有权限，请联系管理员授权");
    }

    @ExceptionHandler(AccountExpiredException.class)
    public Res handleAccountExpiredException(AccountExpiredException e) {
        log.error(e.getMessage(), e);
        return Res.error(e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public Res handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.error(e.getMessage(), e);
        return Res.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Res handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Res.error(e.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    public Res handleSQLException(SQLException e) {
        log.error(e.getMessage(), e);
        return Res.error(e.getMessage());
    }

    @ExceptionHandler(ValidateCodeException.class)
    public Res handleValidateCodeException(ValidateCodeException e) {
        log.error(e.getMessage(), e);
        return Res.error(e.getMessage());
    }

}
