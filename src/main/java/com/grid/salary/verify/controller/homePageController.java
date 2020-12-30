package com.grid.salary.verify.controller;

import com.grid.salary.verify.common.Res;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.grid.salary.verify.service.IStaffService;

import java.util.Map;

/**
 * @ProjectName: salary-verify
 * @Package: com.grid.salary.verify.controller
 * @description: 描述
 * @author: zpl
 * @date: 2020/07/20 09:56
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/homePage")
public class homePageController {

	private static final Logger log = LoggerFactory.getLogger(homePageController.class);
	@Autowired
	private IStaffService staffService;

	/**
	 * 薪资系统首页（人员管理、核算流程）
	 * @return
	 */
	@GetMapping("/getHomePage")
	public Res getHomePage(){
		Map<String,Object> result = staffService.getHomePage();
		return Res.ok(result);
	}
}
