package com.grid.salary.verify.controller;


import com.grid.salary.verify.common.Res;
import com.grid.salary.verify.constant.Const;
import com.grid.salary.verify.domain.req.SalaryReq;
import com.grid.salary.verify.domain.req.SysUserReq;
import com.grid.salary.verify.service.ISalaryAccountService;
import com.grid.salary.verify.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/salary/")
public class SalaryAccountController {

    private static final Logger log = LoggerFactory.getLogger(SalaryAccountController.class);

    @Autowired private ISalaryAccountService salaryAccountService;

    /**
     * 薪资核算
     * @param req
     * @return
     */
    @PostMapping("saveSalaryAccount")
    public Res saveSalaryAccount(@RequestBody SalaryReq req) {

        String queryTime = req.getQueryTime();
        if (!Utils.isNotEmpty(queryTime)) {
            return Res.error(HttpStatus.BAD_REQUEST.value(),
                    "参数queryTime不能为空!");
        }
        Map<String, Object> resMap = new HashMap<>();
        List<Map<String, Object>> resList = salaryAccountService.saveSalaryAccount(queryTime);
        resMap.put("step", Const.STRING_THREE);
        resMap.put("dataList", resList);
        return Res.ok(resMap);
    }


}
