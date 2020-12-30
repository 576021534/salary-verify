package com.grid.salary.verify.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
@TableName("t_sys_log")
public class SysLog extends Model<SysLog> {

	private static final long serialVersionUID = -5615495923472533059L;

	@TableId(value = "id", type = IdType.UUID)
	private String id;
	private String userId;
	private Date createTime;
	private String ipAddress;
	private String reqParam;
	private String type;
	private String menuCode;
	private String menuStatus;

}

