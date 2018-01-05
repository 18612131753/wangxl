package com.ray.base.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateUtils {

	/**
	 * 获取SimpleDateFormat
	 * @param parttern 日期格式
	 * @return SimpleDateFormat对象
	 * @throws RuntimeException 异常：非法日期格式
	 */
	private static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {
		return new SimpleDateFormat(parttern);
	}

	/**
	 * 获取日期中的某数值。如获取月份
	 * @param date 日期
	 * @param dateType 日期格式
	 * @return 数值
	 */
	private static int getInteger(Date date, int dateType) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(dateType);
	}
	
	/**
	 * 增加日期中某类型的某数值。如增加日期
	 * @param date 日期字符串
	 * @param dateType 类型
	 * @param amount 数值
	 * @return 计算后日期字符串
	 */
	private static String addInteger(String date, int dateType, int amount) {
		String dateString = null;
		DatePart datePart = getDatePart(date);
		if (datePart != null) {
			Date myDate = StringToDate(date, datePart);
			myDate = addInteger(myDate, dateType, amount);
			dateString = DateToString(myDate, datePart);
		}
		return dateString;
	}
	
	/**
	 * 增加日期中某类型的某数值。如增加日期
	 * @param date 日期
	 * @param dateType 类型
	 * @param amount 数值
	 * @return 计算后日期
	 */
	private static Date addInteger(Date date, int dateType, int amount) {
		Date myDate = null;
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(dateType, amount);
			myDate = calendar.getTime();
		}
		return myDate;
	}

	/**
	 * 获取精确的日期
	 * @param timestamps 时间long集合
	 * @return 日期
	 */
	private static Date getAccurateDate(List<Long> timestamps) {
		Date date = null;
		long timestamp = 0;
		Map<Long, long[]> map = new HashMap<Long, long[]>();
		List<Long> absoluteValues = new ArrayList<Long>();

		if (timestamps != null && timestamps.size() > 0) {
			if (timestamps.size() > 1) {
				for (int i = 0; i < timestamps.size(); i++) {
					for (int j = i + 1; j < timestamps.size(); j++) {
						long absoluteValue = Math.abs(timestamps.get(i) - timestamps.get(j));
						absoluteValues.add(absoluteValue);
						long[] timestampTmp = { timestamps.get(i), timestamps.get(j) };
						map.put(absoluteValue, timestampTmp);
					}
				}

				// 有可能有相等的情况。如2012-11和2012-11-01。时间戳是相等的
				long minAbsoluteValue = -1;
				if (!absoluteValues.isEmpty()) {
					// 如果timestamps的size为2，这是差值只有一个，因此要给默认值
					minAbsoluteValue = absoluteValues.get(0);
				}
				for (int i = 0; i < absoluteValues.size(); i++) {
					for (int j = i + 1; j < absoluteValues.size(); j++) {
						if (absoluteValues.get(i) > absoluteValues.get(j)) {
							minAbsoluteValue = absoluteValues.get(j);
						} else {
							minAbsoluteValue = absoluteValues.get(i);
						}
					}
				}

				if (minAbsoluteValue != -1) {
					long[] timestampsLastTmp = map.get(minAbsoluteValue);
					if (absoluteValues.size() > 1) {
						timestamp = Math.max(timestampsLastTmp[0], timestampsLastTmp[1]);
					} else if (absoluteValues.size() == 1) {
						// 当timestamps的size为2，需要与当前时间作为参照
						long dateOne = timestampsLastTmp[0];
						long dateTwo = timestampsLastTmp[1];
						if ((Math.abs(dateOne - dateTwo)) < 100000000000L) {
							timestamp = Math.max(timestampsLastTmp[0], timestampsLastTmp[1]);
						} else {
							long now = new Date().getTime();
							if (Math.abs(dateOne - now) <= Math.abs(dateTwo - now)) {
								timestamp = dateOne;
							} else {
								timestamp = dateTwo;
							}
						}
					}
				}
			} else {
				timestamp = timestamps.get(0);
			}
		}

		if (timestamp != 0) {
			date = new Date(timestamp);
		}
		return date;
	}

	/**
	 * 判断字符串是否为日期字符串
	 * @param date 日期字符串
	 * @return true or false
	 */
	public static boolean isDate(String date) {
		boolean isDate = false;
		if (date != null) {
			if (StringToDate(date) != null) {
				isDate = true;
			}
		}
		return isDate;
	}

	/**
	 * 获取日期字符串的日期风格。失敗返回null。
	 * @param date 日期字符串
	 * @return 日期风格
	 */
	public static DatePart getDatePart(String date) {
		DatePart datePart = null;
		Map<Long, DatePart> map = new HashMap<Long, DatePart>();
		List<Long> timestamps = new ArrayList<Long>();
		for (DatePart part : DatePart.values()) {
			Date dateTmp = StringToDate(date, part.getValue());
			if (dateTmp != null) {
				timestamps.add(dateTmp.getTime());
				map.put(dateTmp.getTime(), part);
			}
		}
		datePart = map.get(getAccurateDate(timestamps).getTime());
		return datePart;
	}

	/**
	 * 将日期字符串转化为日期。失败返回null。
	 * @param date 日期字符串
	 * @return 日期
	 */
	public static Date StringToDate(String date) {
		DatePart datePart = null;
		return StringToDate(date, datePart);
	}

	/**
	 * 将日期字符串转化为日期。失败返回null。
	 * @param date 日期字符串
	 * @param parttern 日期格式
	 * @return 日期
	 */
	public static Date StringToDate(String date, String parttern) {
		Date myDate = null;
		if (date != null) {
			try {
				myDate = getDateFormat(parttern).parse(date);
			} catch (Exception e) {
			}
		}
		return myDate;
	}

	/**
	 * 将日期字符串转化为日期。失败返回null。
	 * @param date 日期字符串
	 * @param datePart 日期风格
	 * @return 日期
	 */
	public static Date StringToDate(String date, DatePart datePart) {
		Date myDate = null;
		if (datePart == null) {
			List<Long> timestamps = new ArrayList<Long>();
			for (DatePart part : DatePart.values()) {
				Date dateTmp = StringToDate(date, part.getValue());
				if (dateTmp != null) {
					timestamps.add(dateTmp.getTime());
				}
			}
			myDate = getAccurateDate(timestamps);
		} else {
			myDate = StringToDate(date, datePart.getValue());
		}
		return myDate;
	}

	/**
	 * 将日期转化为日期字符串。失败返回null。
	 * @param date 日期
	 * @param parttern 日期格式
	 * @return 日期字符串
	 */
	public static String DateToString(Date date, String parttern) {
		String dateString = null;
		if (date != null) {
			try {
				dateString = getDateFormat(parttern).format(date);
			} catch (Exception e) {
			}
		}
		return dateString;
	}

	/**
	 * 将日期转化为日期字符串。失败返回null。
	 * @param date 日期
	 * @param datePart 日期风格
	 * @return 日期字符串
	 */
	public static String DateToString(Date date, DatePart datePart) {
		String dateString = null;
		if (datePart != null) {
			dateString = DateToString(date, datePart.getValue());
		}
		return dateString;
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * @param date 旧日期字符串
	 * @param parttern 新日期格式
	 * @return 新日期字符串
	 */
	public static String StringToString(String date, String parttern) {
		return StringToString(date, null, parttern);
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * @param date 旧日期字符串
	 * @param datePart 新日期风格
	 * @return 新日期字符串
	 */
	public static String StringToString(String date, DatePart datePart) {
		return StringToString(date, null, datePart);
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * @param date 旧日期字符串
	 * @param olddParttern 旧日期格式
	 * @param newParttern 新日期格式
	 * @return 新日期字符串
	 */
	public static String StringToString(String date, String olddParttern, String newParttern) {
		String dateString = null;
		if (olddParttern == null) {
			DatePart part = getDatePart(date);
			if (part != null) {
				Date myDate = StringToDate(date, part.getValue());
				dateString = DateToString(myDate, newParttern);
			}
		} else {
			Date myDate = StringToDate(date, olddParttern);
			dateString = DateToString(myDate, newParttern);
		}
		return dateString;
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * @param date 旧日期字符串
	 * @param olddDtePart 旧日期风格
	 * @param newdatePart 新日期风格
	 * @return 新日期字符串
	 */
	public static String StringToString(String date, DatePart olddDtePart, DatePart newdatePart) {
		String dateString = null;
		if (olddDtePart == null) {
			DatePart part = getDatePart(date);
			dateString = StringToString(date, part.getValue(), newdatePart.getValue());
		} else {
			dateString = StringToString(date, olddDtePart.getValue(), newdatePart.getValue());
		}
		return dateString;
	}

	/**
	 * 增加日期的年份。失败返回null。
	 * @param date 日期
	 * @param yearAmount 增加数量。可为负数
	 * @return 增加年份后的日期字符串
	 */
	public static String addYear(String date, int yearAmount) {
		return addInteger(date, Calendar.YEAR, yearAmount);
	}
	
	/**
	 * 增加日期的年份。失败返回null。
	 * @param date 日期
	 * @param yearAmount 增加数量。可为负数
	 * @return 增加年份后的日期
	 */
	public static Date addYear(Date date, int yearAmount) {
		return addInteger(date, Calendar.YEAR, yearAmount);
	}
	
	/**
	 * 增加日期的月份。失败返回null。
	 * @param date 日期
	 * @param yearAmount 增加数量。可为负数
	 * @return 增加月份后的日期字符串
	 */
	public static String addMonth(String date, int yearAmount) {
		return addInteger(date, Calendar.MONTH, yearAmount);
	}
	
	/**
	 * 增加日期的月份。失败返回null。
	 * @param date 日期
	 * @param yearAmount 增加数量。可为负数
	 * @return 增加月份后的日期
	 */
	public static Date addMonth(Date date, int yearAmount) {
		return addInteger(date, Calendar.MONTH, yearAmount);
	}
	
	/**
	 * 增加日期的天数。失败返回null。
	 * @param date 日期字符串
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加天数后的日期字符串
	 */
	public static String addDay(String date, int dayAmount) {
		return addInteger(date, Calendar.DATE, dayAmount);
	}

	/**
	 * 增加日期的天数。失败返回null。
	 * @param date 日期
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加天数后的日期
	 */
	public static Date addDay(Date date, int dayAmount) {
		return addInteger(date, Calendar.DATE, dayAmount);
	}
	
	/**
	 * 增加日期的小时。失败返回null。
	 * @param date 日期字符串
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加小时后的日期字符串
	 */
	public static String addHour(String date, int hourAmount) {
		return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
	}

	/**
	 * 增加日期的小时。失败返回null。
	 * @param date 日期
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加小时后的日期
	 */
	public static Date addHour(Date date, int hourAmount) {
		return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
	}
	
	/**
	 * 增加日期的分钟。失败返回null。
	 * @param date 日期字符串
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加分钟后的日期字符串
	 */
	public static String addMinute(String date, int hourAmount) {
		return addInteger(date, Calendar.MINUTE, hourAmount);
	}

	/**
	 * 增加日期的分钟。失败返回null。
	 * @param date 日期
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加分钟后的日期
	 */
	public static Date addMinute(Date date, int hourAmount) {
		return addInteger(date, Calendar.MINUTE, hourAmount);
	}
	
	/**
	 * 增加日期的秒钟。失败返回null。
	 * @param date 日期字符串
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加秒钟后的日期字符串
	 */
	public static String addSecond(String date, int hourAmount) {
		return addInteger(date, Calendar.SECOND, hourAmount);
	}

	/**
	 * 增加日期的秒钟。失败返回null。
	 * @param date 日期
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加秒钟后的日期
	 */
	public static Date addSecond(Date date, int hourAmount) {
		return addInteger(date, Calendar.SECOND, hourAmount);
	}

	/**
	 * 获取日期的年份。失败返回0。
	 * @param date 日期字符串
	 * @return 年份
	 */
	public static int getYear(String date) {
		return getYear(StringToDate(date));
	}

	/**
	 * 获取日期的年份。失败返回0。
	 * @param date 日期
	 * @return 年份
	 */
	public static int getYear(Date date) {
		return getInteger(date, Calendar.YEAR);
	}

	/**
	 * 获取日期的月份。失败返回0。
	 * @param date 日期字符串
	 * @return 月份
	 */
	public static int getMonth(String date) {
		return getMonth(StringToDate(date));
	}

	/**
	 * 获取日期的月份。失败返回0。
	 * @param date 日期
	 * @return 月份
	 */
	public static int getMonth(Date date) {
		return getInteger(date, Calendar.MONTH);
	}

	/**
	 * 获取日期的天数。失败返回0。
	 * @param date 日期字符串
	 * @return 天
	 */
	public static int getDay(String date) {
		return getDay(StringToDate(date));
	}

	/**
	 * 获取日期的天数。失败返回0。
	 * @param date 日期
	 * @return 天
	 */
	public static int getDay(Date date) {
		return getInteger(date, Calendar.DATE);
	}
	
	/**
	 * 获取日期的小时。失败返回0。
	 * @param date 日期字符串
	 * @return 小时
	 */
	public static int getHour(String date) {
		return getHour(StringToDate(date));
	}

	/**
	 * 获取日期的小时。失败返回0。
	 * @param date 日期
	 * @return 小时
	 */
	public static int getHour(Date date) {
		return getInteger(date, Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 获取日期的分钟。失败返回0。
	 * @param date 日期字符串
	 * @return 分钟
	 */
	public static int getMinute(String date) {
		return getMinute(StringToDate(date));
	}

	/**
	 * 获取日期的分钟。失败返回0。
	 * @param date 日期
	 * @return 分钟
	 */
	public static int getMinute(Date date) {
		return getInteger(date, Calendar.MINUTE);
	}
	
	/**
	 * 获取日期的秒钟。失败返回0。
	 * @param date 日期字符串
	 * @return 秒钟
	 */
	public static int getSecond(String date) {
		return getSecond(StringToDate(date));
	}

	/**
	 * 获取日期的秒钟。失败返回0。
	 * @param date 日期
	 * @return 秒钟
	 */
	public static int getSecond(Date date) {
		return getInteger(date, Calendar.SECOND);
	}

	/**
	 * 获取日期 。默认yyyy-MM-dd格式。失败返回null。
	 * @param date 日期字符串
	 * @return 日期
	 */
	public static String getDate(String date) {
		return StringToString(date, DatePart.YYYY_MM_DD);
	}

	/**
	 * 获取日期。默认yyyy-MM-dd格式。失败返回null。
	 * @param date 日期
	 * @return 日期
	 */
	public static String getDate(Date date) {
		return DateToString(date, DatePart.YYYY_MM_DD);
	}

	/**
	 * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
	 * @param date 日期字符串
	 * @return 时间
	 */
	public static String getTime(String date) {
		return StringToString(date, DatePart.HH_MM_SS);
	}

	/**
	 * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
	 * @param date 日期
	 * @return 时间
	 */
	public static String getTime(Date date) {
		return DateToString(date, DatePart.HH_MM_SS);
	}

	/**
	 * 获取日期的星期。失败返回null。
	 * @param date 日期字符串
	 * @return 星期
	 */
	public static DateWeek getDateWeek(String date) {
		DateWeek DateWeek = null;
		DatePart datePart = getDatePart(date);
		if (datePart != null) {
			Date myDate = StringToDate(date, datePart);
			DateWeek = getDateWeek(myDate);
		}
		return DateWeek;
	}

	/**
	 * 获取日期的星期。失败返回null。
	 * @param date 日期
	 * @return 星期
	 */
	public static DateWeek getDateWeek(Date date) {
		DateWeek dateWeek = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int DateWeekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		switch (DateWeekNumber) {
		case 0:
			dateWeek = DateWeek.SUNDAY;
			break;
		case 1:
			dateWeek = DateWeek.MONDAY;
			break;
		case 2:
			dateWeek = DateWeek.TUESDAY;
			break;
		case 3:
			dateWeek = DateWeek.WEDNESDAY;
			break;
		case 4:
			dateWeek = DateWeek.THURSDAY;
			break;
		case 5:
			dateWeek = DateWeek.FRIDAY;
			break;
		case 6:
			dateWeek = DateWeek.SATURDAY;
			break;
		}
		return dateWeek;
	}
	
	/**
	 * 获取两个日期相差的天数
	 * @param date 日期字符串
	 * @param otherDate 另一个日期字符串
	 * @return 相差天数
	 */
	public static int getIntervalDays(String date, String otherDate) {
		return getIntervalDays(StringToDate(date), StringToDate(otherDate));
	}
	
	/**
	 * @param date 日期
	 * @param otherDate 另一个日期
	 * @return 相差天数
	 */
	public static int getIntervalDays(Date date, Date otherDate) {
		date = DateUtils.StringToDate(DateUtils.getDate(date));
		long time = Math.abs(date.getTime() - otherDate.getTime());
		return (int)time/(24 * 60 * 60 * 1000);
	}
	
	
	//java.sql.Date转为java.util.Date
	public static java.util.Date convertUtilDate(java.sql.Date sqlDate){
		if(sqlDate==null) return null;
		java.util.Date date = new java.util.Date(sqlDate.getTime());
		return date;
	}
	
	//java.util.Date转为java.sql.Date
	public static java.sql.Date convertSqlDate(java.util.Date utilDate){
		if(utilDate==null) return null;
		 java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}
	
	//java.util.Date转为java.sql.Time
	public static java.sql.Time convertSqlTime(java.util.Date utilDate){
		if(utilDate==null) return null;
		java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());
		return sqlTime;
	}
	
	//java.util.Date转为java.sql.Timestamp
	public static java.sql.Timestamp convertSqlTimestamp(java.util.Date utilDate){
		if(utilDate==null) return null;
		java.sql.Timestamp sqlTime = new java.sql.Timestamp(utilDate.getTime());
		return sqlTime;
	}
}