package com.grid.salary.verify.logging;

import com.grid.salary.verify.constant.Const;
import com.grid.salary.verify.domain.SysLog;
import com.grid.salary.verify.mapper.SysLogMapper;
import com.grid.salary.verify.security.util.JsonUtils;
import com.grid.salary.verify.security.util.SecurityUtil;
import com.grid.salary.verify.util.Utils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 所属模块：系统监控-日志查询
 * 日志的插入
 */
@Component
@Aspect
public class AopLogAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Environment env;

    public AopLogAspect(Environment env) {
        this.env = env;
    }

    @Autowired private SysLogMapper sysLogMapper;

    /**
     * 通过自定义注解拦截DDL操作的方法进行日志记录
     *
     * @param joinPoint join point for advice.
     */
    @After("execution(* com.grid.salary.verify.controller..*.save*(..)) " +
        "|| execution(* com.grid.salary.verify.controller..*.update*(..)) " +
        "|| execution(* com.grid.salary.verify.controller..*.delete*(..)) " +
        "|| execution(* com.grid.salary.verify.controller..*.registry*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("Enter AopLogAspect logAfter method.");
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("进入到日志审计，操作中的参数：");
        for (int i = 0; i < args.length; i++) {
            log.info("***********PointAfter:***********" + args[i].toString());
        }
        String params = JsonUtils.beanToJson(args);
        this.save(Const.ONE, methodName, params);
    }

    @AfterThrowing(value = "execution(* com.grid.salary.verify.controller..*.save*(..)) " +
        "|| execution(* com.grid.salary.verify.controller..*.update*(..)) " +
        "|| execution(* com.grid.salary.verify.controller..*.delete*(..)) " +
        "|| execution(* com.grid.salary.verify.controller..*.registry*(..))", throwing = "e")
    public void logAfterException(JoinPoint joinPoint, Exception e) {
        String methodName = joinPoint.getSignature().getName();
        log.info("添加日志审计异常信息:" + e.getMessage());
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            log.info("***********PointAfterThrowing:***********" + args[i].toString());
        }
        String params = JsonUtils.beanToJson(args);
        this.save(Const.TWO, methodName, params);
    }

    public void save(int num, String methodName, String params) {
        SysLog sysLog = new SysLog();
        sysLog.setCreateTime(Utils.getNow());
        sysLog.setReqParam(params);
        sysLog.setType(getOperationType(methodName));
        sysLog.setUserId(SecurityUtil.getUser().getUserId() + Const.EMPTY);
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();
        sysLog.setIpAddress(getIpAddr(request));
        sysLog.setMenuCode(request.getServletPath());
        sysLog.setMenuStatus("操作成功");
        if (Const.TWO == num) {
            sysLog.setMenuStatus("操作失败");
        }
        sysLogMapper.insert(sysLog);
    }


    /**
     * 获取IP地址
     *
     * @param request 请求
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0
            || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
            || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
            || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        log.info("====== request ip is ======" + ipAddress);
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        //或者这样也行,对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        return Utils.isNotEmpty(ipAddress) ? ipAddress.split(",")[0] : null;
        //return ipAddress;
    }

    /**
     * 根据方法名获取操作类型
     *
     * @param methodName 操作的方法名
     * @return
     */
    public String getOperationType(String methodName) {
        if (methodName.contains("update") || methodName.contains("put")) {
            return "修改";
        }
        if (methodName.contains("add") || methodName.contains("save")
            || methodName.contains("create") || methodName.contains("registry")) {
            return "新增";
        }
        if (methodName.contains("delete") || methodName.contains("remove")) {
            return "删除";
        }
        if (methodName.contains("get") || methodName.contains("all")
            || methodName.contains("find") || methodName.contains("query")) {
            return "查询";
        }
        return "";
    }

}
