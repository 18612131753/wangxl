package com.ray.power.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RayDateUtils {
	
	public static final String YYMMDD_hhmmss="yyyy-MM-dd hh:mm:ss";
	public static final String YYMMDD_HHmmss_24="yyyy-MM-dd HH:mm:ss";
	public static final String YYMMDD="yyyy-MM-dd"; 
	public static final String YYMM="yyyy-MM";
	
	public static String searchDateAddOneDay(String date ){
		if( date == null || date.length() <1 ) return null ;
		return RayDateUtils.dateToStr( YYMMDD , 
			   RayDateUtils.addDays( RayDateUtils.strToDate( RayDateUtils.YYMMDD , date ), 1 )) ;
	}
	public static String dateToStr( String format , Date date ){
		try{
			DateFormat fmt = new SimpleDateFormat(format);
			return fmt.format(date);
		} catch(Exception e){
			//e.printStackTrace();
			return null;
		}
	}
	public static String dateToStr( Date date ){
		try{
			DateFormat fmt = new SimpleDateFormat(YYMMDD_HHmmss_24);
			return fmt.format(date);
		} catch(Exception e){
			//e.printStackTrace();
			return null;
		}
	}
	public static String longToStr( String format , Long time ){
		try{
			Date date = new Date(time);
			return RayDateUtils.dateToStr(format, date);
		} catch(Exception e){
			//e.printStackTrace();
			return null;
		}
	}
	
	public static Date strToDate( String format , String timeStr ){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = sdf.parse(timeStr);
			return date;
		} catch( ParseException e){
			//e.printStackTrace();
			return null;
		}
	}
	
	//日期加月份天
	public static Date addMonths( Date beginDate , int months){
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.add(Calendar.MONTH ,months);
		return date.getTime();
	}
	
	//日期加几天
	public static Date addDays( Date beginDate , int days ){
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.add(Calendar.DAY_OF_MONTH,days);
		return date.getTime();
	}
	
	//日期加几分钟
	public static Date addMinute( Date beginDate , int mins ){
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.add(Calendar.MINUTE , mins );
		return date.getTime();
	}
	
	/**
	 * 获取某年某月的第一天
	 * */
	public static Date getMonthFirstDay( int year , int month ){
		Calendar curCal = Calendar.getInstance();
		curCal.set( Calendar.YEAR , year); 
		curCal.set( Calendar.MONTH,month-1);
		curCal.set(Calendar.DAY_OF_MONTH, 1);
		curCal.set(Calendar.HOUR_OF_DAY , 0);
		curCal.set(Calendar.MINUTE, 0);
		curCal.set(Calendar.SECOND, 0);
		curCal.set(Calendar.MILLISECOND, 0);
		return curCal.getTime();
	}
	 /**
	  * 得到某年某月的最后一天
	  *
	  * @param year
	  * @param month
	  * @return
	  */
	 public Date getMonthLastDay(int year, int month) {
	 
	  Calendar cal = Calendar.getInstance();
	  cal.set(Calendar.YEAR, year);
	  cal.set(Calendar.MONTH, month-1);
	  cal.set(Calendar.DAY_OF_MONTH, 1);
	  
	  int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	  cal.set(Calendar.DAY_OF_MONTH, value);
	  cal.set(Calendar.HOUR_OF_DAY , 0);
	  cal.set(Calendar.MINUTE, 0);
	  cal.set(Calendar.SECOND, 0);
	  cal.set(Calendar.MILLISECOND, 0);
	 
	  return cal.getTime();
	 
	 }
	
	/**
	 * 获取某年某月的下个月的第一天
	 * */
	public static Date getAfterMonthFirstDay( int year , int month ){
		Calendar curCal = Calendar.getInstance();
		curCal.set( Calendar.YEAR , year); 
		curCal.set( Calendar.MONTH,month);
		curCal.set(Calendar.DAY_OF_MONTH, 1);
		curCal.set(Calendar.HOUR_OF_DAY , 0);
		curCal.set(Calendar.MINUTE, 0);
		curCal.set(Calendar.SECOND, 0);
		curCal.set(Calendar.MILLISECOND, 0);
		return curCal.getTime();
	}
	/**
	 * 从Excel中获得时间
	 * @param dateStr excel中表示时间的字符串 ，形式为 2013/5/1 或 41415
	 * @return
	 */
	public static Date getDateFromExcel(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat(YYMMDD);
		Date date = null;
		String[] strs = dateStr.split("/");
		try {
			if(strs.length == 3){
				date = sdf.parse(strs[0]+"-"+strs[1]+"-"+strs[2]);
				return date;
			}
			
			Integer dateValue = Integer.valueOf(dateStr.substring(0, dateStr.lastIndexOf(".")));
			date = sdf.parse("1900-1-1");
			date = addDays(date, dateValue-2);
			return date;
		} catch (ParseException e) {
			//e.printStackTrace();
			return null;
		}
		
	}

	public static int getDAY_OF_MONTH( Date date ){
		if( date == null ) return 0 ;
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return day ;
	}

	public static int getMONTH( Date date ){
		if( date == null ) return 0 ;
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		int month = cal.get(Calendar.MONTH) + 1;
		return month ;
	}
	
	public static int getYEAR( Date date ){
		if( date == null ) return 0 ;
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		int year = cal.get(Calendar.YEAR) ;
		return year ;
	}
	
	/**
	 * 获得两个日期之前相差的月份<br>
	 * 
	 * @param start
	 * @param e
	 * @return
	 */
	public static int getMonth_Num(Date start, Date end) {
		if (start.after(end)) {
			Date t = start;
			start = end;
			end = t;
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(start);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		Calendar temp = Calendar.getInstance();
		temp.setTime(end);
		
		int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

		return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
	}
	/**
	 * 获得当天的0点0分0秒
	 * @param date
	 * @return
	 */
	public static Date getDateStart(Date date){
		String date_str = new SimpleDateFormat("yyyy-MM-dd").format(date);
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date_str+" 0:0:0");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static Date getDateStart(String date){
		Date startDate = new Date();
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date+" 0:0:0");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startDate;
	}
	
	/**
	 * 获得时间之前一个月的第一天
	 * @param date
	 * @return
	 */
	public static Date getBeforeMonthFirstDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date lastDate = calendar.getTime();
		return lastDate;
	}
	
	/**
	 * 获得时间之前一个月的最后一天
	 * @param date
	 * @return
	 */
	public static Date getBeforeMonthLastDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-1);
		int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, lastDay);
		Date lastDate = calendar.getTime();
		return lastDate;
	}
	
	/**
	 * 判断两个日期是否是在同一天
	 * */
	public static boolean isOneDay( Date day1 ,Date day2 ){
		if( day1 == null ||  day2 == null ) return false ;
		String day1_str = RayDateUtils.dateToStr( RayDateUtils.YYMMDD , day1 );
		String day2_str = RayDateUtils.dateToStr( RayDateUtils.YYMMDD , day2 );
		if( day1_str.equals( day2_str )) {
			return true ;
		}
		return false ;
	}
	/**
	 * 获得报表导出月
	 * @param year 导出年
	 * @param type 1=主表 2=附表
	 * @return 如果是去年，返回12，若果是今年，返回上月
	 */
	public static int getReportMonth( int year ,int type){
		Date date = new Date();
		int month = 0;
		if(year<RayDateUtils.getYEAR(date)){
			month=12;
		}else{
			if( type == 1 ){ 
				month = RayDateUtils.getMONTH(date)-1;  //说明是主表，不显示当月
			} else {
				month = RayDateUtils.getMONTH(date)  ;  //显示当月
			}
			
		}
		return month;
	}
	/**
	 * 获得此月的天数
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDAYS_OF_MONTH(int year,int month){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
}
