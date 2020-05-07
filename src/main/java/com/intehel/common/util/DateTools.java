package com.intehel.common.util;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * @Author: 石文静
 * @Description: 日期时间工具类
 * @CreateDate: 2019/9/27  15:55
 * @UpdateDate: 2019/9/27  15:55
 * @Version: V1
 */
public class DateTools {

    /**
     * @Author 石文静
     * @Description 获取系统时间"yyyy-MM-dd"
     * @CreateDate 2019/9/27 16:13
     * @Version V1
     */
    public static String getSystemDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(
                System.currentTimeMillis()));
    }

    /**
     * @Author 石文静
     * @Description 获取系统时间"yyyy-MM-dd HH:mm:ss"
     * @CreateDate 2019/11/7 15:03
     * @Version V1
    */
    public static String getSystemTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(
                System.currentTimeMillis()));
    }

    /**
     * @Author 石文静
     * @Description 获取系统时间yyyyMMddHHmmss
     * @CreateDate 2019/11/7 15:03
     * @Version V1
    */
    public static String getSystemTimeString(){
        return new SimpleDateFormat("yyyyMMddHHmmss").format((System.currentTimeMillis()));
    }

    /**
     * @Author 石文静
     * @Description Date转String  yyyyMMddHHmmss
     * @CreateDate 2019/11/7 15:02
     * @Version V1
    */
    public static String dateToStringTimeString(Date datetime){
        return new SimpleDateFormat("yyyyMMddHHmmss").format(datetime);
    }
    /**
     * @Author 石文静
     * @Description Date转String   yyyyMMdd格式
     * @CreateDate 2019/11/7 15:02
     * @Version V1
    */
    public static String dateToStringDateString(Date date) throws Exception{
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    /**
     * @Author 石文静
     * @Description Date转String   yyyy-MM-dd格式
     * @CreateDate 2019/11/7 15:02
     * @Version V1
    */
    public static String dateToStringDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * @Author 石文静
     * @Description Date转String   yyyy-MM-dd HH:mm:ss格式
     * @CreateDate 2019/11/7 15:02
     * @Version V1
    */
    public static String dateToStringTime(Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * @Author 石文静
     * @Description String类型转Date  返回2018-12-17 17:00:00
     * @CreateDate 2019/11/7 15:00
     * @Version V1
    */
    public static Date stringToTime(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date =null;
        try {
            date = sdf.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @Author 石文静
     * @Description String 类型转Date  返回2018-12-17
     * @CreateDate 2019/11/7 15:02
     * @Version V1
    */
    public static Date stringToDate(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @Author 石文静
     * @Description 时间+天
     * @CreateDate 2019/11/7 15:02
     * @Version V1
    */
    public static Date datePlusDay(Date date,int day) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.DATE, day);
        date = cl.getTime();
        return date;
    }
    /**
     * @Author 石文静
     * @Description 时间+月
     * @CreateDate 2019/11/7 15:02
     * @Version V1
    */
    public static Date datePlusMonth(Date date,int month) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.MONTH, month);
        date = cl.getTime();
        return date;
    }
    /**
     * @Author 石文静
     * @Description 时间+年
     * @CreateDate 2019/11/7 15:02
     * @Version V1
    */
    public static Date datePlusYear(Date date,int year) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.YEAR, year);
        date = cl.getTime();
        return date;
    }

    /**
     * @Author 石文静
     * @Description 计算两个日期之间相差的天数      返回int
     * @CreateDate 2019/11/7 15:02
     * @Version V1
    */
    public static int daysBetween(Date smdate,Date bdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long betweenDays=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(betweenDays));
    }

    /**
     * @Author 石文静
     * @Description 计算两个日期之间相差的月数
     * @CreateDate 2019/11/7 15:01
     * @Version V1
    */
    public static int monthsBetween(Date date1,Date date2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(sdf.parse(sdf.format(date1)));
        c2.setTime(sdf.parse(sdf.format(date2)));
        int month=c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        int result = yearsBetween(sdf.parse(sdf.format(date1)), sdf.parse(sdf.format(date2))) * 12 + month;
        return result;
    }
    /**
     * @Author 石文静
     * @Description 计算两个日期之间相差的年数
     * @CreateDate 2019/11/7 14:50
     * @Version V1
    */
    public static int yearsBetween(Date start, Date end) throws ParseException {
        Calendar cstart = Calendar.getInstance();
        Calendar cend = Calendar.getInstance();
        cstart.setTime(start);
        cend.setTime(end);
        return (cend.get(Calendar.YEAR) - cstart.get(Calendar.YEAR));
    }

    /**
     * @Author 石文静
     * @Description 判断是否是日期格式
     * @CreateDate 2019/9/27 16:20
     * @Version V1
     */
    public static boolean isTimeDormat(String data,String format){
        //判断是否是空值
        if(data==null||"".equals(data)){
            return false;
        }
        SimpleDateFormat simple=new SimpleDateFormat(format);
        try{
            //时间成功转换  说明格式正确
            simple.parse(data);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * 商户订单随机字符串
     */
    public static String timeCharacter(String type,String userInfo){
        String l = System.currentTimeMillis()+type+userInfo;
        l=l.substring(1, l.length());
        return l;
    }


}
