package com.grid.salary.verify.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.grid.salary.verify.domain.Staff;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.management.openmbean.CompositeData;
import java.util.List;
import java.util.Map;

@Repository
public interface StaffMapper extends BaseMapper<Staff> {

    List<Map<String, Object>> querySalaryAccount(String queryTime);

    @Select("SELECT COUNT(user_id) AS staffNumber FROM t_staff WHERE `status` = 1")
    Map<String, Object> getStaffManage();

    @Select("SELECT COUNT(user_id) AS joinMonth FROM t_staff\n" +
            "WHERE `status` = 1 AND DATE_FORMAT(join_time, '%Y%m') = DATE_FORMAT(CURDATE(), '%Y%m')\n")
    Map<String, Object> getJoinMonth();

    @Select("SELECT COUNT(user_id)  AS quitMonth FROM t_staff\n" +
            "WHERE `status` = 2 AND DATE_FORMAT(quit_time, '%Y%m') = DATE_FORMAT(CURDATE(), '%Y%m')")
    Map<String, Object> getQuitMonth();
}