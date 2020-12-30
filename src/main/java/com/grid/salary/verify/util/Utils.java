package com.grid.salary.verify.util;


import com.grid.salary.verify.constant.Const;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: huangzhongjie
 * @time: 2019-11-08 20:05
 */
public class Utils {

    public static boolean isNotEmpty(String str){
        return null != str && (!"".equals(str.trim()));
    }

    public static boolean isNotEmpty(List list){
        return null != list && list.size() > 0;
    }

    public static boolean isNotEmpty(Map map){
        return null != map && map.size() > 0;
    }

    public static boolean isNotEmpty(Object obj){
        return null != obj;
    }

    public static boolean isNotEmpty(Object[] obj){
        return null != obj && obj.length > 0;
    }

    public static String formatStr(Object obj) {
        if (!isNotEmpty(obj)) {
            return "";
        }
        return String.valueOf(obj);
    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String formatDate(Date date, String def) {
        if (!isNotEmpty(def)) {
            def = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(def);
        return sdf.format(date);
    }

    public static Date parseDate(String date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getNow();
    }

    public static Date parseDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getNow();
    }

    public static Date getNow() {
        return new Date();
    }

    public static Date getBeforeNow() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getNow());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    public static Date getDayByDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getNow());
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    public static Date getDayByDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    public static String today(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(getNow());
    }

    public static final String createLabelCode() {
       return new StringBuffer(Utils.today("yyyyMMddHHmmssSSS"))
               .append(Utils.random(3)).toString();
    }

    public static final String createOrderId() {
        return new StringBuffer(Utils.today("yyyyMMddHHmmssSSS"))
                .append(Utils.random(5)).toString();
    }

    public static final int intRandom() {
        Random random = new Random();
        return random.nextInt(9);
    }

    public static final String random(int num) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < num; i++) {
            buffer.append(intRandom());
        }
        return buffer.toString();
    }

    public static final int getNumber(String number) {

        int res = 0;
        switch (number) {
            case "one":
                res = 1;
                break;
            case "five":
                res = 5;
                break;
            case "two":
                res = 2;
                break;
            case "three":
                res = 3;
                break;
            case "nine":
                res = 9;
                break;
            case "four":
                res = 4;
                break;
            case "six":
                res = 6;
                break;
            case "seven":
                res = 7;
                break;
            case "eight":
                res = 8;
                break;
            default:
                break;
        }
        return res;
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n;
    }

    public static int formatInt(String str, int def) {
        if (!isNotEmpty(str)) {
            return def;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return def;
        }
    }

    public static String formatStr(String para, String defalut) {
        if (null != para) {
            para = para.trim();
            if ("".equals(para)) {
                return defalut;
            }
            return para;
        } else {
            return defalut;
        }
    }

    // 生成UUID 32位字符串
    public static String createUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }


    public static void main(String[] args) {

        AtomicInteger number = new AtomicInteger(0);
        int stock = number.decrementAndGet();
        System.out.println("::::::::::::" + stock);
    }

    // 基本工资 转正后
    public static BigDecimal getBaseSalaryAfter(Double salary) {
        BigDecimal baseSalary = new BigDecimal(salary);
        return baseSalary;
    }

    // 基本工资 转正前
    public static BigDecimal getBaseSalaryBefore(Double salary) {
        BigDecimal baseSalary = new BigDecimal(salary);
        return baseSalary.multiply(new BigDecimal(0.8));
    }

    // 绩效工资 转正后
    public static BigDecimal getPerformanceSalaryAfter(Double salary,
                                                       Double num, int day, int beforeDay) {
        // 绩效工资 * 绩效数字
        Double d = salary * num;
        BigDecimal performanceSalary = new BigDecimal(d);
        return performanceSalary;
    }

    // 绩效工资 转正前
    public static BigDecimal getPerformanceSalaryBefore(Double salary,
                                                        Double num, int day, int beforeDay) {
        // 绩效工资 * 绩效数字
        Double d = salary * num;
        BigDecimal performanceSalary = new BigDecimal(d);
        return performanceSalary.multiply(new BigDecimal(0.8))
                .divide(new BigDecimal(day))
                .multiply(new BigDecimal(beforeDay))
                .multiply(new BigDecimal(0.8));
    }

    // 本月工作天数*转正前出勤天数 step1
    public static BigDecimal getWorkDayBefore(Double salary, int day, int beforeDay) {
        return getBaseSalaryBefore(salary).multiply(new BigDecimal(day))
                .multiply(new BigDecimal(beforeDay)) ;
    }
    // 本月工作天数*转正后出勤天数 step1
    public static BigDecimal getWorkDayAfter(Double salary, int day, int afterDay) {
        return getBaseSalaryAfter(salary).multiply(new BigDecimal(day))
                .multiply(new BigDecimal(afterDay));
    }



}
