package com.grid.salary.verify.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("t_staff")
public class Staff {

    private static final long serialVersionUID = -5615923472533059L;

    @TableId(value = "user_id", type = IdType.UUID)
    private String userId;

    private String staffName;

    private String staffNumber;

    private Integer sex;

    private String position;

    private Integer status;

    private Integer isTrial;

    private Date joinTime;

    private Date onTrialTime;

    private Date quitTime;

    private Integer isSocialSecurity;

    private BigDecimal stationWages;

    private BigDecimal baseWages;

    private BigDecimal performance;

    private Integer proportion;

    private BigDecimal managerAllowance;

    private BigDecimal skillAllowance;

    private BigDecimal secrecyAllowance;

    private BigDecimal projectAllowance;

    private BigDecimal lunchAllowance;

    private BigDecimal workingAllowance;

    private BigDecimal trafficAllowance;

    private BigDecimal hydropowerAllowance;

    private BigDecimal dutyAllowance;

    private Date brithday;

    private String personNo;

    private String nativePlace;

    private String affiliation;

    private Integer education;

    private String major;

    private String school;

    private Date graduationTime;

    private Date workTime;

    private String nation;

    private String registered;

    private String personAddress;

    private String address;

    private String language;

    private Integer isMarry;

    private String email;

    private String phone;

    private String urgentName;

    private String urgentPhone;

    private Date createTime;

    private String creator;

    private Integer isDel;

}