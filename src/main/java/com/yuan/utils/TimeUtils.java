package com.yuan.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.ParsePosition;

public class TimeUtils {
	
	/**
	 * 将dete类型格式化
	 * @param date
	 * @param type   yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String dateToStr(Date date ,String type) {
		SimpleDateFormat formatter = new SimpleDateFormat(type);
		String dateString = formatter.format(date);
		return dateString;
	}
	
	/**
     * 获取两个日期之间的所有日期
     * @param startTime 开始日期
     * @param endTime 结束日期
     * @return
     */
	public static List<String> getDays(String startTime, String endTime) {
        // 返回的日期集合
        List<String> days = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);
            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }
	
	/**
	 * 每五分钟
	 * @return
	 */
	public static String getTime() {
		try {
			// 得到long类型当前时间
			String l = System.currentTimeMillis() + "";
			String date = timeStampToTime(l);
			String date_1 = date.substring(0, 15);
			String date_2 = date.substring(15, 16);
			int min = Integer.parseInt(date_2);
			if (min >= 0 && min < 5) {
				min = 0;
			} else {
				min = 5;
			}
			String newDate = date_1 + min + ":00";
			String thisTime = timeTotimeStamp(newDate);
			return thisTime;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
		}

	}

	/**
	 * 时间转换成时间戳
	 * 
	 * @param s
	 *            传入的时间
	 * @return 返回时间戳
	 * @throws ParseException
	 */
	public static String timeTotimeStamp(String s) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(s);
		long ts = date.getTime();
		String res = String.valueOf(ts);
		return res;
	}

	/**
	 * 时间戳转换成时间
	 * 
	 * @param s
	 *            传入的时间戳
	 * @return 返回格式化时间
	 */
	public static String timeStampToTime(String s) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		String res = simpleDateFormat.format(date);
		return res;
	}

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 *
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 *
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将短时间格式时间转换为字符串 yyyy-MM-dd
	 *
	 * @param dateDate
	 * @param k
	 * @return
	 */
	public static String dateToStr(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}


	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 *
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	
	//特殊时间格式处理     		2019-08-13T07:35:00.000+0000
    public static String dealDateFormat(String oldDate) {
    	try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = df.parse(oldDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);//date 换成已经已知的Date对象
            cal.add(Calendar.HOUR_OF_DAY, +8);// before 8 hour
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(cal.getTime());
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return null;
    }
    
  //yyyyMMdd -> yyyy-MM-dd
    public static String strToDateStr(String str) {
    	Date parse = null;
		String dateString = "";
    	try {
    		parse = new SimpleDateFormat("yyyyMMdd").parse(str);
    		dateString = new SimpleDateFormat("yyyy-MM-dd").format(parse);
            return dateString;
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 获取昨天的日期  ymd
     * @return
     */
    public static String getYesterDay(String day) {
//        String day = "2018-03-1";
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day1 = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day1 - 1);
 
        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }
    
    /**
     * 传入 "yyyy-MM-dd HH:mm:ss" 字符串  返回 周数
     * @param strDate
     * @return
     */
    public static int getWeekNum(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date = formatter.parse(strDate, pos);
		//周最大值判断
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);//设置周一为一周的第一天
		calendar.setTime(date);
		int newWeekNum = calendar.get(Calendar.WEEK_OF_YEAR);
		return newWeekNum;
	}
    
	/**
	 * 判断时间是不是今天
	 * 
	 * @param date
	 * @return 是返回true，不是返回false
	 */
	public static boolean isNow(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date = formatter.parse(strDate, pos);
		// 当前时间
		Date now = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		// 获取今天的日期
		String nowDay = sf.format(now);
		// 对比的时间
		String day = sf.format(date);
		return day.equals(nowDay);

	}
	
	
	/**
	 * 得到今天周几
	 * @return 
	 */
	public static String getThisWeek() {
		String week = "";
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			week = "周日";
		if (day_of_week == 1)
			week = "周一";
		if (day_of_week == 2)
			week = "周二";
		if (day_of_week == 3)
			week = "周三";
		if (day_of_week == 4)
			week = "周四";
		if (day_of_week == 5)
			week = "周五";
		if (day_of_week == 6)
			week = "周六";
		return week;
	}
}
