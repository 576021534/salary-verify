package com.grid.salary.verify.service.impl;

import com.grid.salary.verify.mapper.StaffMapper;
import com.grid.salary.verify.service.ISalaryAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SalaryAccountServiceImpl implements ISalaryAccountService {

    private static final Logger log = LoggerFactory.getLogger(SalaryAccountServiceImpl.class);

    @Autowired private StaffMapper staffMapper;

    /**
     * 1: 查询员工表,考勤，绩效，社保关联查询获取所要计算的字段。
     * 2: 根据薪资计算的场景按照分支计算
     */
    @Override
    public List<Map<String, Object>> saveSalaryAccount(String queryTime) {
        List<Map<String, Object>> resList = new ArrayList<>();
        List<Map<String, Object>> list = staffMapper.querySalaryAccount(queryTime);
        for (Map<String, Object> map : list) {

        }

        return resList;
    }

}


































