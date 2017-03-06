package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.enums.StockTimeEnum;

public class DateFormatUtil {
	
	public static final String DATE_DEFAULT_FORMAT="yyyy-MM-dd HH:mm:ss";
	public static final String DATE_YEAR_MOUTH_DAY_FORMAT="yyyy-MM-dd";
	public static final String DATE_HOUR_MINUTE_SECOND="HH:mm:ss";

	public static Date stringToDate(String date, String format) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat(format);
            return sf.parse(date);
        } catch (ParseException e) {
        	
        }
        return null;
    }
	
	public static String dateToString(Date date, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(date);
    }

	/**
	 * 自定义时间（时 分 秒）
	 * @param date
	 * @param time
	 * @return
	 */
	public static Date getDateChangeHourMinuteSecond(Date date, Object... time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY,time.length>=1 ? (Integer)time[0] : 00);
		calendar.set(Calendar.MINUTE, time.length>=2 ? (Integer)time[1] : 00);
		calendar.set(Calendar.SECOND, time.length>=3 ? (Integer)time[2] : 00);
		return calendar.getTime();
	}
	
	public static int daysBetween(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return -1;
		}
		long intervalMilli = endDate.getTime() - startDate.getTime();
		return (int) (intervalMilli / (24 * 60 * 60 * 1000)) + 1;
	}
	
	
	/*public static Date getDateAfter(Date d, int day){
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE)+day);
		return now.getTime();
	}*/
	
	public static String getHour(Date date) {
		return dateToString(date, "HH");
	}
	
	/**
     * 得到当前的15分钟，取整，例如12:03 ->12:00, 12:59 ->12:45
     * 
     * @return
     */
    public static Date getCurrentQuarter() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) / 15 * 15);
        return calendar.getTime();
    }
    
    /**
     * 获取当前 (年-月-日)
     * @return
     */
    public static String getNowYearMouthDayStr() {
    	Date now = new Date();
    	return dateToString(now, DATE_YEAR_MOUTH_DAY_FORMAT);
    }
    
    /**
     * 获取 (年-月-日 时:分:秒)
     * @return
     */
    public static String getNowTimeStr() {
    	Date now = new Date();
    	return dateToString(now, DATE_DEFAULT_FORMAT);
    }
    
    /**
     * 获取 (年-月-日 时:分:秒)
     * @return
     */
    public static Date getNowTimeDate() {
    	return stringToDate(getNowTimeStr(), DATE_DEFAULT_FORMAT);
    }
    
    /**
     * 更新(年月日)
     * @param nowYearMouthDayStr
     * @param i
     * @return
     */
    public static String updateCurrentYearMouthDayStr(
			String nowYearMouthDayStr, int year, int mouth, int day){
    	return dateToString(updateCurrentYearMouthDayDate(nowYearMouthDayStr,year,mouth,day), DATE_YEAR_MOUTH_DAY_FORMAT);
    }
    
    /**
     * 更新(年月日)
     * @param nowYearMouthDayStr
     * @param i
     * @return
     */
    public static Date updateCurrentYearMouthDayDate(
			String nowYearMouthDayStr, int year, int mouth, int day) {
		// TODO Auto-generated method stub
    	SimpleDateFormat sdf=new SimpleDateFormat(DATE_YEAR_MOUTH_DAY_FORMAT);
			Date dt;
			try {
				dt = sdf.parse(nowYearMouthDayStr);
				Calendar currentDayBefore = Calendar.getInstance();
				currentDayBefore.setTime(dt);
				currentDayBefore.add(Calendar.YEAR, year);//年
				currentDayBefore.add(Calendar.MONTH, mouth);//月
				currentDayBefore.add(Calendar.DAY_OF_MONTH, day);//天
				return currentDayBefore.getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}
    
    /**
     * 更新(年月日 时-分-秒)
     * @param currentDate
     * @return Date
     */
    public static String updateCurrentTimeStr(String currentDate,int day,int minute){
    	return dateToString(updateCurrentTimeDate(currentDate,day,minute),DATE_DEFAULT_FORMAT);
    }
    
    /**
     * 更新(年月日 时-分-秒)
     * @param currentDate
     * @return Date
     */
    public static Date updateCurrentTimeDate(String currentDate,int day,int minute){
    	SimpleDateFormat sdf=new SimpleDateFormat(DATE_DEFAULT_FORMAT);
		try {
			Date dt = sdf.parse(currentDate);
			Calendar currentDayBefore = Calendar.getInstance();
			currentDayBefore.setTime(dt);
			currentDayBefore.add(Calendar.DAY_OF_MONTH,day);//天
			currentDayBefore.add(Calendar.MINUTE,minute);//分钟
			return currentDayBefore.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    /**
     * 上午开市时间(年-月-日 09:00:00)
     * @param yearMouthDay
     * @return
     */
    public static String getCurrTimeOfMorningStart(String yearMouthDay) {
		return yearMouthDay+" 09:00:00";
	}
    /**
     * 上午关市时间(年-月-日 11:30:00)
     * @param yearMouthDay
     * @return
     */
	public static String getCurrTimeOfMorningEnd(String yearMouthDay) {
		return yearMouthDay+" 11:30:00";
	}
	/**
     * 下午开市时间(年-月-日 13:00:00)
     * @param yearMouthDay
     * @return
     */
	public static String getCurrTimeOfAfternoonStart(String yearMouthDay) {
		return yearMouthDay+" 13:00:00";
	}
	/**
     * 下午关市时间(年-月-日 15:00:00)
     * @param yearMouthDay
     * @return
     */
	public static String getCurrTimeOfAfternoonEnd(String yearMouthDay) {
		return yearMouthDay+" 15:00:00";
	}
    
    public static void main(String[] args) {
    	//早上开市之前9:00算昨天的数据
    	if(stringToDate("2015-07-28 08:49:59", DATE_DEFAULT_FORMAT).before(
    			DateFormatUtil.updateCurrentTimeDate(DateFormatUtil.getCurrTimeOfMorningStart(DateFormatUtil.getNowYearMouthDayStr()),0,-10))){
    		System.out.println(DateFormatUtil.getNowTimeDate());
    		System.out.println(
    				DateFormatUtil.updateCurrentTimeStr(DateFormatUtil.getCurrTimeOfMorningStart(DateFormatUtil.getNowYearMouthDayStr()),0,-10));

    		System.out.println("早上开市之前，昨天数据");
    		
    		System.out.println("======================");
    	}
    	
    	//早上开市之后9:00－13:00前算今天上午数据
    	if(stringToDate("2015-07-28 12:49:59", DATE_DEFAULT_FORMAT).before(
    			DateFormatUtil.updateCurrentTimeDate(DateFormatUtil.getCurrTimeOfAfternoonStart(DateFormatUtil.getNowYearMouthDayStr()),0,-10))){
    		System.out.println(DateFormatUtil.getNowTimeDate());
    		System.out.println(
    				DateFormatUtil.updateCurrentTimeDate(DateFormatUtil.getCurrTimeOfAfternoonStart(DateFormatUtil.getNowYearMouthDayStr()),0,-10));
    		System.out.println("上午数据");
    		System.out.println("*********************");
    	}
    	//下午开市之后13:00－24:00前算今天下午数据
    	if(stringToDate("2015-07-28 12:50:01", DATE_DEFAULT_FORMAT).after(
    			DateFormatUtil.updateCurrentTimeDate(DateFormatUtil.getCurrTimeOfAfternoonStart(DateFormatUtil.getNowYearMouthDayStr()),0,-10))){
    		System.out.println(DateFormatUtil.getNowTimeDate());
    		System.out.println(
    				DateFormatUtil.updateCurrentTimeDate(DateFormatUtil.getCurrTimeOfAfternoonStart(DateFormatUtil.getNowYearMouthDayStr()),0,-10));
    		System.out.println("下午数据");
    		System.out.println("======================");
    	}
    	
    	System.out.println(StockTimeEnum.AFTERNOONEND);
    	
    	if(StockTimeEnum.AFTERNOONEND.toString().equals("AFTERNOONEND")){
    		System.out.println("xx");
    	}else{
    		System.out.println("haha");
    	}
    }

	
    
}
