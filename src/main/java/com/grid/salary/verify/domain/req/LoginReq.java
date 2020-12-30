package com.grid.salary.verify.domain.req;

/**
 * @description: 描述
 * @author: zhangyaofang
 * @date: 2020/03/09 08:51
 * @version: 1.0
 */
public class LoginReq {

    private String username;
    private String password;
    private String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
