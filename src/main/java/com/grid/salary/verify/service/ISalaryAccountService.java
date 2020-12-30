package com.grid.salary.verify.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.grid.salary.verify.domain.User;
import com.grid.salary.verify.domain.req.SysUserPassWordReq;
import com.grid.salary.verify.domain.req.SysUserReq;
import com.grid.salary.verify.domain.res.SysUserCurrentRes;

import java.util.List;
import java.util.Map;


public interface ISalaryAccountService {

    List<Map<String, Object>> saveSalaryAccount(String queryTime);

}
