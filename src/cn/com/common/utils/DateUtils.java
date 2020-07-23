package cn.com.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期转换工具类
 * 
 * @author zhj
 * 
 */
public class DateUtils {

	/**
	 * 根据传入的 pattern 将 dateString 格式化成日期类型
	 * 
	 * @param pattern
	 * @param dateString
	 * @return
	 * @throws Exception
	 */
	public static Date parseStringByPattern(String pattern, String dateString) throws Exception {
		Date date = new SimpleDateFormat(pattern).parse(dateString);
		return date;
	}

	/**
	 * 根据传入的 pattern 将 dateString 格式化成日期类型
	 *
	 * @param dateString
	 * @return
	 * @throws Exception
	 */
	public static Date parseStringByPatternYYYYMMDD(String dateString) throws Exception {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		return date;
	}

	/**
	 * 获取年份后两位
	 * @return
	 */
	public static String getYearYY(){
		String yearLast = new SimpleDateFormat("yy",Locale.CHINESE).format(Calendar.getInstance().getTime());
		return yearLast;
	}


	/**
	 * 根据传入的 pattern 将 date 格式化成字符串
	 * 
	 * @param pattern
	 * @param date
	 * @return
	 */
	public static String formatDateByPattern(String pattern, Date date) {
		String dateTime = new SimpleDateFormat(pattern).format(date);
		return dateTime;
	}
	
	/**
	 * 获取当前时间的特殊格式:2014年3月5日  星期三
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日  E", Locale.SIMPLIFIED_CHINESE);
		return sdf.format(new Date());
	}

	public static String getDateYYYYMMDDSS(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日HH时mm分ss秒", Locale.SIMPLIFIED_CHINESE);
		return sdf.format(new Date());
	}
	public static String getDateYYYYMMDD(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日", Locale.SIMPLIFIED_CHINESE);
		return sdf.format(new Date());
	}
	public static String getDateyMdhms(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.SIMPLIFIED_CHINESE);
		return sdf.format(new Date());
	}
	

	 /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
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
        long between_days=(time2-time1)/(1000*3600*24);  
            
		Integer df = Integer.parseInt(String.valueOf(between_days));
		return df;
    }

	public static String stapToFileNameDate(String s){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd~HH-mm");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}
	public static String getDateYYYYMMDD(Date date){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		res = simpleDateFormat.format(date);
		return res;
	}
	//获取本周一日期
	public static String currentMonday(){
		Date a = org.apache.commons.lang3.time.DateUtils.addDays(new Date(),-1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(a);
		calendar.add(Calendar.WEEK_OF_YEAR,0); //一周
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		return getDateYYYYMMDD(calendar.getTime());
	}
	//获取本周一日期
	public static String currentMonday(String strDay) throws Exception{
		Date day = parseStringByPatternYYYYMMDD(strDay);
		Date a = org.apache.commons.lang3.time.DateUtils.addDays(day,-1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(a);
		calendar.add(Calendar.WEEK_OF_YEAR,0); //一周
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		return getDateYYYYMMDD(calendar.getTime());
	}
	//获取本周五日期
	public static String currentFriDay(){
		Date a = org.apache.commons.lang3.time.DateUtils.addDays(new Date(),-1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(a);
		calendar.add(Calendar.WEEK_OF_YEAR,0); //一周
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);
		return getDateYYYYMMDD(calendar.getTime());
	}
	//获取本周五日期
	public static String currentFriDay(String strDay) throws Exception{
		Date day = parseStringByPatternYYYYMMDD(strDay);
		Date a = org.apache.commons.lang3.time.DateUtils.addDays(day,-1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(a);
		calendar.add(Calendar.WEEK_OF_YEAR,0); //一周
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);
		return getDateYYYYMMDD(calendar.getTime());
	}

	//获取下周一日期
	public static String firstMonday(){
		Date a = org.apache.commons.lang3.time.DateUtils.addDays(new Date(),-1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(a);
		calendar.add(Calendar.WEEK_OF_YEAR,1); //一周
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		return getDateYYYYMMDD(calendar.getTime());
	}

	//获取上周一日期
	public static String lastMonday(){
		Date a = org.apache.commons.lang3.time.DateUtils.addDays(new Date(),-1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(a);
		calendar.add(Calendar.WEEK_OF_YEAR,-1); //一周
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		return getDateYYYYMMDD(calendar.getTime());
	}
	//获取上周一日期
	public static String lastMonday(Date day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		calendar.add(Calendar.WEEK_OF_YEAR,-1); //一周
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		return getDateYYYYMMDD(calendar.getTime());
	}
	//获取上周一日期
	public static String lastMonday(String strDay) throws Exception{
		Date day = parseStringByPatternYYYYMMDD(strDay);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		calendar.add(Calendar.WEEK_OF_YEAR,-1); //一周
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		return getDateYYYYMMDD(calendar.getTime());
	}
	//获取上周日日期
	public static String lastWeekDay(){
		Date a = org.apache.commons.lang3.time.DateUtils.addDays(new Date(),-1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(a);
		calendar.set(Calendar.DAY_OF_WEEK,1);
		return getDateYYYYMMDD(calendar.getTime());
	}
	//获取上周日日期
	public static String lastWeekDay(Date day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		calendar.set(Calendar.DAY_OF_WEEK,1);
		return getDateYYYYMMDD(calendar.getTime());
	}
	//获取上周日日期
	public static String lastWeekDay(String strDay) throws Exception{
		Date day = parseStringByPatternYYYYMMDD(strDay);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		calendar.set(Calendar.DAY_OF_WEEK,1);
		return getDateYYYYMMDD(calendar.getTime());
	}
	//获取上周一日期带小时
	public static String lastMondayHour(){
		Date a = org.apache.commons.lang3.time.DateUtils.addDays(new Date(),-1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(a);
		calendar.add(Calendar.WEEK_OF_YEAR,-1); //一周
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		String date = getDateYYYYMMDD(calendar.getTime());
		return initHour(date);
	}
	//获取日期上周一日期带小时
	public static String lastMondayHour(String strDay) throws Exception{
		Date day = parseStringByPatternYYYYMMDD(strDay);
		Date a = org.apache.commons.lang3.time.DateUtils.addDays(day,-1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(a);
		calendar.add(Calendar.WEEK_OF_YEAR,-1); //一周
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		String date = getDateYYYYMMDD(calendar.getTime());
		return initHour(date);
	}

	//获取上周日日期带小时
	public static String lastWeekDayHour(){
		Date a = org.apache.commons.lang3.time.DateUtils.addDays(new Date(),-1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(a);
		calendar.set(Calendar.DAY_OF_WEEK,1);
		String date = getDateYYYYMMDD(calendar.getTime());
		return endHour(date);
	}

	//获取日期上周日日期带小时
	public static String lastWeekDayHour(String strDay) throws Exception{
		Date day = parseStringByPatternYYYYMMDD(strDay);
		Date a = org.apache.commons.lang3.time.DateUtils.addDays(day,-1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(a);
		calendar.set(Calendar.DAY_OF_WEEK,1);
		String date = getDateYYYYMMDD(calendar.getTime());
		return endHour(date);
	}

	//增加6天日期 添加23:59:00
	public static String addSixDay(String day) throws Exception {
		Date a = parseStringByPatternYYYYMMDD(day);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(a);
		calendar.add(Calendar.DAY_OF_MONTH,+6); //添加6天
		String date = getDateYYYYMMDD(calendar.getTime());
		return date;
	}

	//开始时间
	public static String initHour(String day){
		return day + " 00:00:00";
	}

	//结束时间
	public static String endHour(String day){
		return day + " 23:59:59";
	}

	
}