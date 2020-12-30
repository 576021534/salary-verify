package com.grid.salary.verify.common;


import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @description: 接口统一返回对象
 * @author: zhangyaofang
 * @date: 2020/03/09 08:46
 * @version: 1.0
 */
public class Res implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer code = 200;
    private String msg;
    private Object data;

    public static Res ok() {
        Res r = new Res();
        r.setMsg("操作成功");
        return r;
    }

    public static Res ok(Object data) {
        Res r = new Res();
        r.setMsg("操作成功");
        r.setData(data);
        return r;
    }
    public static Res error(Integer code, String msg) {
        Res r = new Res();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static Res error() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常，请联系管理员");
    }

    public static Res error(String msg) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static Res error(int code, String msg) {
        Res r = new Res();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
