package com.grid.salary.verify.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @description: 描述
 * @author: zhangyaofang
 * @date: 2020/03/09 08:51
 * @version: 1.0
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String message) {
        super(message);
    }
}
