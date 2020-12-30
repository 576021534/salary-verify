package com.grid.salary.verify.domain.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ProjectName: construction-control
 * @Package: com.grid.construction.control.domain
 * @description: 描述
 * @author: zhangyaofang
 * @date: 2019/12/18 11:33
 * @version: 1.0
 */
@Data
public class SysUserReq extends BaseReq {

	private String id;//  主键ID
	private String userName;//  用户名
	private String loginName;//  登录帐号
	private String password;//  密码
	private Date createTime;//  创建时间
	private Date updateTime;//  修改时间
}

