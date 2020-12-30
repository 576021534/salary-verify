package com.grid.salary.verify.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grid.salary.verify.domain.Staff;

import java.util.Map;

/**
 * @ProjectName: salary-verify
 * @Package: com.grid.salary.verify.service
 * @description: 描述
 * @author: zhangyaofang
 * @date: 2020/07/20 09:56
 * @version: 1.0
 */
public interface IStaffService extends IService<Staff>{

    /**
     *首页
     * @return
     */
    Map<String, Object> getHomePage();
}
