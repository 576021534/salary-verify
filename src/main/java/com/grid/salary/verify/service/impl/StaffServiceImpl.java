package com.grid.salary.verify.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.grid.salary.verify.mapper.StaffMapper;
import com.grid.salary.verify.domain.Staff;
import com.grid.salary.verify.service.IStaffService;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: salary-verify
 * @Package: com.grid.salary.verify.service.impl
 * @description: 描述
 * @author: zhangyaofang
 * @date: 2020/07/20 09:56
 * @version: 1.0
 */
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements IStaffService {

    private static final Logger log = LoggerFactory.getLogger(StaffServiceImpl.class);
	@Autowired
	private StaffMapper staffMapper;

	/**
	 * 薪资系统首页（人员管理、核算流程）
	 * @return
	 */
	@Override
	public Map<String, Object> getHomePage() {
		Map<String,Object> result = new HashMap<>();
		Map<String,Object> staffManage = new HashMap<>();
//		在职员工数
		staffManage.put("staffNumber",staffMapper.getStaffManage().get("staffNumber"));
//		本月入职
		staffManage.put("joinMonth",staffMapper.getJoinMonth().get("joinMonth"));
//		本月离职
		staffManage.put("quitMonth",staffMapper.getQuitMonth().get("quitMonth"));
		result.put("staffManage",staffManage);
		return result;
	}
}