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
 * @date: 2020/07/20 09:56
 * @version: 1.0
 */
public class StaffReq extends BaseReq{


	private String userId;//  主键ID
	private String staffName;//  员工名称
	private String staffNumber;//  员工编号
	private Integer sex;//  性别(1-男，2-女)
	private String position;//  职位
	private Integer status;//  员工状态(1-在职，2-离职)
	private Boolean isTrial;//  是否转正(1-true-转正，2-false-试用期)
	private Date joinTime;//  入职时间
	private Date onTrialTime;//  转正时间
	private Date quitTime;//  离职时间
	private Boolean isSocialSecurity;//  是否缴纳社保（1：是；2：否）
	private Long stationWages;//  岗位工资
	private Long baseWages;//  基本工资
	private Long performance;//  绩效工资
	private Integer proportion;//  试用期工资比例
	private Long managerAllowance;//  管理津贴
	private Long skillAllowance;//  技术津贴
	private Long secrecyAllowance;//  保密津贴
	private Long projectAllowance;//  项目津贴
	private Long lunchAllowance;//  午餐津贴
	private Long workingAllowance;//  工龄津贴
	private Long trafficAllowance;//  交通津贴
	private Long hydropowerAllowance;//  水电补助
	private Long dutyAllowance;//  值班补助
	private Date brithday;//  出生日期
	private String personNo;//  身份证号码
	private String nativePlace;//  籍贯
	private String affiliation;//  政治面貌
	private Integer education;//  最高学历(1-博士后，2-博士，3-硕士，4-学士，5-大专，6-其它)
	private String major;//  专业
	private String school;//  毕业院校
	private Date graduationTime;//  毕业时间
	private Date workTime;//  参加工作时间
	private String nation;//  民族
	private String registered;//  户口所在地
	private String personAddress;//  身份证地址
	private String address;//  现住址
	private String language;//  外语
	private Boolean isMarry;//  婚否（1：是；2：否）
	private String email;//  邮箱
	private String phone;//  联系电话
	private String urgentName;//  紧急联系人
	private String urgentPhone;//  紧急联系人电话
	private Date createTime;//  创建时间
	private String creator;//  创建人
	private Boolean isDel;//  是否删除（0否 1 是）

	public StaffReq(){
	}

	public StaffReq(String userId){
		this.userId = userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffName() {
		return this.staffName;
	}
	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}

	public String getStaffNumber() {
		return this.staffNumber;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getSex() {
		return this.sex;
	}
	public void setPosition(String position) {
		this.position = position;
	}

	public String getPosition() {
		return this.position;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}
	public void setIsTrial(Boolean isTrial) {
		this.isTrial = isTrial;
	}

	public Boolean getIsTrial() {
		return this.isTrial;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getJoinTime() {
		return this.joinTime;
	}
	public void setOnTrialTime(Date onTrialTime) {
		this.onTrialTime = onTrialTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOnTrialTime() {
		return this.onTrialTime;
	}
	public void setQuitTime(Date quitTime) {
		this.quitTime = quitTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getQuitTime() {
		return this.quitTime;
	}
	public void setIsSocialSecurity(Boolean isSocialSecurity) {
		this.isSocialSecurity = isSocialSecurity;
	}

	public Boolean getIsSocialSecurity() {
		return this.isSocialSecurity;
	}
	public void setStationWages(Long stationWages) {
		this.stationWages = stationWages;
	}

	public Long getStationWages() {
		return this.stationWages;
	}
	public void setBaseWages(Long baseWages) {
		this.baseWages = baseWages;
	}

	public Long getBaseWages() {
		return this.baseWages;
	}
	public void setPerformance(Long performance) {
		this.performance = performance;
	}

	public Long getPerformance() {
		return this.performance;
	}
	public void setProportion(Integer proportion) {
		this.proportion = proportion;
	}

	public Integer getProportion() {
		return this.proportion;
	}
	public void setManagerAllowance(Long managerAllowance) {
		this.managerAllowance = managerAllowance;
	}

	public Long getManagerAllowance() {
		return this.managerAllowance;
	}
	public void setSkillAllowance(Long skillAllowance) {
		this.skillAllowance = skillAllowance;
	}

	public Long getSkillAllowance() {
		return this.skillAllowance;
	}
	public void setSecrecyAllowance(Long secrecyAllowance) {
		this.secrecyAllowance = secrecyAllowance;
	}

	public Long getSecrecyAllowance() {
		return this.secrecyAllowance;
	}
	public void setProjectAllowance(Long projectAllowance) {
		this.projectAllowance = projectAllowance;
	}

	public Long getProjectAllowance() {
		return this.projectAllowance;
	}
	public void setLunchAllowance(Long lunchAllowance) {
		this.lunchAllowance = lunchAllowance;
	}

	public Long getLunchAllowance() {
		return this.lunchAllowance;
	}
	public void setWorkingAllowance(Long workingAllowance) {
		this.workingAllowance = workingAllowance;
	}

	public Long getWorkingAllowance() {
		return this.workingAllowance;
	}
	public void setTrafficAllowance(Long trafficAllowance) {
		this.trafficAllowance = trafficAllowance;
	}

	public Long getTrafficAllowance() {
		return this.trafficAllowance;
	}
	public void setHydropowerAllowance(Long hydropowerAllowance) {
		this.hydropowerAllowance = hydropowerAllowance;
	}

	public Long getHydropowerAllowance() {
		return this.hydropowerAllowance;
	}
	public void setDutyAllowance(Long dutyAllowance) {
		this.dutyAllowance = dutyAllowance;
	}

	public Long getDutyAllowance() {
		return this.dutyAllowance;
	}
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBrithday() {
		return this.brithday;
	}
	public void setPersonNo(String personNo) {
		this.personNo = personNo;
	}

	public String getPersonNo() {
		return this.personNo;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getNativePlace() {
		return this.nativePlace;
	}
	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public String getAffiliation() {
		return this.affiliation;
	}
	public void setEducation(Integer education) {
		this.education = education;
	}

	public Integer getEducation() {
		return this.education;
	}
	public void setMajor(String major) {
		this.major = major;
	}

	public String getMajor() {
		return this.major;
	}
	public void setSchool(String school) {
		this.school = school;
	}

	public String getSchool() {
		return this.school;
	}
	public void setGraduationTime(Date graduationTime) {
		this.graduationTime = graduationTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getGraduationTime() {
		return this.graduationTime;
	}
	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWorkTime() {
		return this.workTime;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNation() {
		return this.nation;
	}
	public void setRegistered(String registered) {
		this.registered = registered;
	}

	public String getRegistered() {
		return this.registered;
	}
	public void setPersonAddress(String personAddress) {
		this.personAddress = personAddress;
	}

	public String getPersonAddress() {
		return this.personAddress;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return this.language;
	}
	public void setIsMarry(Boolean isMarry) {
		this.isMarry = isMarry;
	}

	public Boolean getIsMarry() {
		return this.isMarry;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}
	public void setUrgentName(String urgentName) {
		this.urgentName = urgentName;
	}

	public String getUrgentName() {
		return this.urgentName;
	}
	public void setUrgentPhone(String urgentPhone) {
		this.urgentPhone = urgentPhone;
	}

	public String getUrgentPhone() {
		return this.urgentPhone;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreator() {
		return this.creator;
	}
	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

	public Boolean getIsDel() {
		return this.isDel;
	}


}

