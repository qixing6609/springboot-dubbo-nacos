package com.qx.core.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {
    private static Date date;
    private static Calendar CALENDAR = Calendar.getInstance();
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    /**  取得当前时间
     * @return 当前日期（Date）
     */
    public static Date getCurrentDate() {
        return new Date();
    }
    /** 取得昨天此时的时间
     * @return 昨天日期（Date）
     */
    public static Date getYesterdayDate() {
        return new Date(getCurTimeMillis() - 0x5265c00L);
    }
    /** 取得去过i天的时间
     * @param i 过去时间天数
     * @return 昨天日期（Date）
     */
    public static Date getPastdayDate(int i) {
        return new Date(getCurTimeMillis() - 0x5265c00L*i);
    }
    /** 取得当前时间的长整型表示
     * @return 当前时间（long）
     */
    public static long getCurTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取后n天
     *
     * @param date
     * @return Date
     */
    public static Date getNextNDate(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + n);
        return calendar.getTime();
    }

    /**
     * 获得秒单位的时间戳
     * @return
     */
    public static long getCurTimeSencond() {return getCurTimeMillis() / 1000;}

    /** 取得当前时间的特定表示格式的字符串
     * @param formatDate 时间格式（如：yyyy/MM/dd hh:mm:ss）
     * @return 当前时间
     */
    public static String getCurFormatDate(String formatDate) {
        date = getCurrentDate();
        simpleDateFormat.applyPattern(formatDate);
        return simpleDateFormat.format(date);
    }
    /** 取得某日期时间的特定表示格式的字符串
     * @param format 时间格式
     * @param date 某日期（Date）
     * @return 某日期的字符串
     */
    public static String getDate2Str(String format, Date date) {
        simpleDateFormat.applyPattern(format);
        return simpleDateFormat.format(date);
    }
    /** 将日期转换为长字符串（包含：年-月-日 时:分:秒）
     * @param date 日期
     * @return 返回型如：yyyy-MM-dd HH:mm:ss 的字符串
     */
    public static String getDate2LStr(Date date) {
        return getDate2Str("yyyy-MM-dd HH:mm:ss", date);
    }

    /** 将日期转换为长字符串（包含：年/月/日 时:分:秒）
     * @param date 日期
     * @return 返回型如：yyyy/MM/dd HH:mm:ss 的字符串
     */
    public static String getDate2LStr2(Date date) {
        return getDate2Str("yyyy/MM/dd HH:mm:ss", date);
    }

    /** 将日期转换为中长字符串（包含：年-月-日 时:分）
     * @param date 日期
     * @return 返回型如：yyyy-MM-dd HH:mm 的字符串
     */
    public static String getDate2MStr(Date date) {
        return getDate2Str("yyyy-MM-dd HH:mm", date);
    }

    /** 将日期转换为中长字符串（包含：年/月/日 时:分）
     * @param date 日期
     * @return 返回型如：yyyy/MM/dd HH:mm 的字符串
     */
    public static String getDate2MStr2(Date date) {
        return getDate2Str("yyyy/MM/dd HH:mm", date);
    }

    /** 将日期转换为短字符串（包含：年-月-日）
     * @param date 日期
     * @return 返回型如：yyyy-MM-dd 的字符串
     */
    public static String getDate2SStr(Date date) {
        return getDate2Str("yyyy-MM-dd", date);
    }

    /** 将日期转换为短字符串（包含：年/月/日）
     * @param date 日期
     * @return 返回型如：yyyy/MM/dd 的字符串
     */
    public static String getDate2SStr2(Date date) {
        return getDate2Str("yyyy/MM/dd", date);
    }
    /** 取得型如：yyyyMMddhhmmss的字符串
     * @param date
     * @return 返回型如：yyyyMMddhhmmss 的字符串
     */
    public static String getDate2All(Date date) {
        return getDate2Str("yyyyMMddhhmmss", date);
    }
    /** 将长整型数据转换为Date后，再转换为型如yyyy-MM-dd HH:mm:ss的长字符串
     * @param l 表示某日期的长整型数据
     * @return 日期型的字符串
     */
    public static String getLong2LStr(long l) {
        date = getLongToDate(l);
        return getDate2LStr(date);
    }
    /** 将长整型数据转换为Date后，再转换为型如yyyy-MM-dd的长字符串
     * @param l 表示某日期的长整型数据
     * @return 日期型的字符串
     */
    public static String getLong2SStr(long l) {
        date = getLongToDate(l);
        return getDate2SStr(date);
    }

    /** 将长整型数据转换为Date后，再转换指定格式的字符串
     * @param l 表示某日期的长整型数据
     * @param formatDate 指定的日期格式
     * @return 日期型的字符串
     */
    public static String getLong2SStr(long l, String formatDate) {
        date = getLongToDate(l);
        simpleDateFormat.applyPattern(formatDate);
        return simpleDateFormat.format(date);
    }
    private static Date getStrToDate(String format, String str) {
        simpleDateFormat.applyPattern(format);
//        ParsePosition parseposition = new ParsePosition(0);
        try {
            return simpleDateFormat.parse(str);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /** 将某指定的字符串转换为某类型的字符串
     * @param format 转换格式
     * @param str 需要转换的字符串f
     * @return 转换后的字符串
     */
    public static String getStr2Str(String format, String str){
        Date date = getStrToDate(format,str);
        return getDate2Str(format,date);
    }

    /** 将某指定的字符串转换为型如：yyyy-MM-dd HH:mm:ss的时间
     * @param str 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2LDate(String str) {
        return getStrToDate("yyyy-MM-dd HH:mm:ss", str);
    }

    /** 将某指定的字符串转换为型如：yyyy/MM/dd HH:mm:ss的时间
     * @param str 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2LDate2(String str) {
        return getStrToDate("yyyy/MM/dd HH:mm:ss", str);
    }

    /** 将某指定的字符串转换为型如：yyyy-MM-dd HH:mm的时间
     * @param str 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2MDate(String str) {
        return getStrToDate("yyyy-MM-dd HH:mm", str);
    }

    /** 将某指定的字符串转换为型如：yyyy/MM/dd HH:mm的时间
     * @param str 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2MDate2(String str) {
        return getStrToDate("yyyy/MM/dd HH:mm", str);
    }

    /** 将某指定的字符串转换为型如：yyyy-MM-dd的时间
     * @param str 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2SDate(String str) {
        return getStrToDate("yyyy-MM-dd", str);
    }

    /** 将某指定的字符串转换为型如：yyyy-MM-dd的时间
     * @param str 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2SDate2(String str) {
        return getStrToDate("yyyy/MM/dd", str);
    }
    /** 将某长整型数据转换为日期
     * @param l 长整型数据
     * @return 转换后的日期
     */
    public static Date getLongToDate(long l) {
        return new Date(l);
    }
    /** 以分钟的形式表示某长整型数据表示的时间到当前时间的间隔
     * @param l 长整型数据
     * @return 相隔的分钟数
     */
    public static int getOffMinutes(long l) {
        return getOffMinutes(l, getCurTimeMillis());
    }
    /** 以分钟的形式表示两个长整型数表示的时间间隔
     * @param from 开始的长整型数据
     * @param to 结束的长整型数据
     * @return 相隔的分钟数
     */
    public static int getOffMinutes(long from, long to) {
        return (int) ((to - from) / 60000L);
    }
    /** 以微秒的形式赋值给一个Calendar实例
     * @param l 用来表示时间的长整型数据
     */
    public static void setCalendar(long l) {
        CALENDAR.clear();
        CALENDAR.setTimeInMillis(l);
    }
    /** 以日期的形式赋值给某Calendar
     * @param date 指定日期
     */
    public static void setCalendar(Date date) {
        CALENDAR.clear();
        CALENDAR.setTime(date);
    }
    /** 在此之前要由一个Calendar实例的存在
     * @return 返回某年
     */
    public static int getYear() {
        return CALENDAR.get(1);
    }
    /** 在此之前要由一个Calendar实例的存在
     * @return 返回某月
     */
    public static int getMonth() {
        return CALENDAR.get(2) + 1;
    }

    /** 在此之前要由一个Calendar实例的存在
     * @return 返回某天
     */
    public static int getDay() {
        return CALENDAR.get(5);
    }
    /** 在此之前要由一个Calendar实例的存在
     * @return 返回某小时
     */
    public static int getHour() {
        return CALENDAR.get(11);
    }
    /** 在此之前要由一个Calendar实例的存在
     * @return 返回某分钟
     */
    public static int getMinute() {
        return CALENDAR.get(12);
    }

    /** 在此之前要由一个Calendar实例的存在
     * @return 返回某秒
     */
    public static int getSecond() {
        return CALENDAR.get(13);
    }


    /**
     * 获取本月第一天的秒值
     * @return
     */
    public static String getCurrMonthFirstDaySecond(){
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return "" + c.getTimeInMillis() /1000;
    }


    /**
     * 获取当月最后一天的秒值
     * @return
     */
    public static String getCurrMonthLastDaySecond(){
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        ca.set(Calendar.HOUR_OF_DAY, 23);
        ca.set(Calendar.MINUTE, 59);
        ca.set(Calendar.SECOND, 59);
        return "" + ca.getTimeInMillis() / 1000;
    }

    /**
     * 获取本周第一天的秒值
     * @return
     */
    public static String getCurrWeekFirstDaySecond(int firstDayOfWeek){
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(firstDayOfWeek);
        c.set(Calendar.DAY_OF_WEEK,firstDayOfWeek);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return "" + c.getTimeInMillis() /1000;
    }


    /**
     * 获取本周最后一天的秒值
     * @return
     */
    public static String getCurrWeekLastDaySecond(int firstDayOfWeek, int lastDayOfWeek){
        //获取当前月最后一天
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(firstDayOfWeek);
        c.set(Calendar.DAY_OF_WEEK,lastDayOfWeek);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return "" + c.getTimeInMillis() /1000;
    }

    /**
     * 获取当天的第一秒
     * @return
     */
    public static long getCurrDayStartSecond(){
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        return ca.getTimeInMillis() / 1000;
    }

    /**
     * 获取当天的最后一秒
     * @return
     */
    public static long getCurrDayEndSecond(){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR, 23);
        ca.set(Calendar.MINUTE, 59);
        ca.set(Calendar.SECOND, 59);
        return ca.getTimeInMillis() / 1000;
    }

    /**
     * 获取date类型当天的第一秒
     * @return
     */
    public static Date getCurrDayStartDate(){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        return ca.getTime();
    }

    /**
     * 获取date类型当天的最后一秒
     * @return
     */
    public static Date getCurrDayEndDate(){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY, 23);
        ca.set(Calendar.MINUTE, 59);
        ca.set(Calendar.SECOND, 59);
        return ca.getTime();
    }

    public static Date getRelativeStartDate(int relativeDay) {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DAY_OF_MONTH, relativeDay);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        return ca.getTime();
    }


    public static String getPhpTime(String dateStr) {

        Date date = getStrToDate("yyyy-MM-dd", dateStr);
        String phpTime = date.getTime() / 1000 + "";
        return phpTime;
    }

    /**
     * 按照unit获取baseTime的时间加上add的结果
     * 如获取当前
     * @param unit
     * @param add
     * @param baseTime
     * @return
     */
    public static long getAddSecondTime(int unit, int add, long baseTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(baseTime);
        calendar.set(unit, calendar.get(unit) + add);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获得所给日期的0点时间的秒数
     * @param date
     * @return
     */
    public static long get0PointSecondTime(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTimeInMillis()/1000;
    }



    /**
     * 获得所给日期的0点时间的秒数
     * @return
     */
    public static Date secondToDate(String second){

        return new Date(Long.parseLong(second)*1000);
    }


    /**
     * 格式化好的时间转成时间戳
     * @author zhangteng
     * @param date
     * @return
     */
    public  static long getDateStr2long(String date) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c.getTimeInMillis();
    }

    /**
     * 比较两个 时间的大小 （DATE1<DATE2 true）
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static Boolean compareDate(String DATE1, String DATE2) {


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() < dt2.getTime()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    //日期差值 Yuanfei 2015-12-21
    public static int getDateDifference(Date d1, Date d2){
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(d1);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(d2);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        return day2 - day1;
    }

    //中文年月日 Yuanfei 2015-12-21
    public static String formatSDate_CN(String str){
        Date dateTime = getStr2LDate(str);
        return formatSDate_CN(dateTime);
    }

    public static String formatSDate_CN(Date dateTime){
        setCalendar(dateTime);
        int year = getYear();
        int month = getMonth();
        int day = getDay();
        return year + "年" + month + "月" + day + "日";
    }
    
    /**
	 * 将日期的时分秒设置为当天第一秒，格式为 yyyy-MM-dd 00:00:00
	 * @param date
	 * @return
	 */
	public static Date getBeginSecondOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	
	/**
	 * 将日期的时分秒设置为当天最后一秒，格式为 yyyy-MM-dd 23:59:59
	 * @param date
	 * @return
	 */
	public static Date getEndSecondOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
		return c.getTime();
	}
	
	/**
     * 
     * 计算两个日期相差的月份数
     * 
     * @param date1 日期1
     * @param date2 日期2
     * @param pattern  日期1和日期2的日期格式
     * @return  相差的月份数
     * @throws ParseException
     */
    public static int countMonths(String date1,String date2,String pattern) throws ParseException{
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        Calendar c1=Calendar.getInstance();
        Calendar c2=Calendar.getInstance();
        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));
        int year =c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
        //开始日期若小月结束日期
        if(year<0){
            year=-year;
            return year*12+c1.get(Calendar.MONTH)-c2.get(Calendar.MONTH);
        }
        return year*12+c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH);
    }
    
}
