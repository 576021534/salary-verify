package com.grid.salary.verify.domain.req;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.*;
/**
 * @ProjectName: salary-verify
 * @Package: com.grid.salary.verify.domain.req
 * @description: 描述
 * @author: zhangyaofang
 * @date: 2020/07/16 15:07
 * @version: 1.0
 */
public class UserReq extends BaseReq{


	private String userId;//  主键ID
	private String userName;//  用户名称
	private String loginName;//  登录名
	private String password;//  登录密码
	private Date lastLoginTime;//  最后登录时间
	private Date lastResetTime;//  最后重置时间

	public UserReq(){
	}

	public UserReq(String userId){
		this.userId = userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginName() {
		return this.loginName;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}
	public void setLastResetTime(Date lastResetTime) {
		this.lastResetTime = lastResetTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastResetTime() {
		return this.lastResetTime;
	}


}

