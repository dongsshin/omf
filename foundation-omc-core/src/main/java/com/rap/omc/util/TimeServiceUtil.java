/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : TimeServiceUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015.6.12 yosikim Initial
 * ===========================================
 */
package com.rap.omc.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.foundation.classes.service.TimeService;
import com.rap.omc.foundation.system.model.TimeZoneVO;
import com.rap.omc.api.util.spring.SpringFactoryUtil;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : TimeServiceUtil
 * Description : TODO
 * </pre>
 * 
 * @author yosikim
 */
public class TimeServiceUtil {

    private TimeService timeService;

    private static TimeServiceUtil sInstance;

    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static TimeServiceUtil getInstance(){
        if (sInstance == null) {
            sInstance = new TimeServiceUtil();
            sInstance.timeService = (TimeService)SpringFactoryUtil.getBean("timeService");
        }
        return sInstance;
    }
    public static List<TimeZoneVO> getTimeZoneList(){
    	return getInstance().timeService.getTimeZoneList();
    }
    public static Date getDBLocalTime(){
        return getInstance().timeService.getDBLocalTime();
    }
    public static Date getTruncatedDBLocalDate(){
        return truncate(getInstance().timeService.getDBLocalTime());
    }
    public static String getDBLocalTimeStr(){
        return getInstance().timeService.getDBLocalTimeStr();
    }
    public static String getDBLocalYearStr(){
        return getDBLocalTimeStr().substring(0,4);
    }
    public static String getDBLocalYearMonthStr(){
        return getDBLocalTimeStr().substring(0,7);
    }
    public static String getDBLocalDateStr(){
        return getDBLocalTimeStr().substring(0,10);
    }
//    public static void create(TimeVO vo){
//        getInstance().timeService.create(vo);
//    }
//    
//    public static List<TimeVO> read(TimeVO input){
//        return getInstance().timeService.read(input);
//    }
    /**
     * DBMS의 년단위 주 차를 return함.
     *
     * @return
     */
    public static int getWeekOfYear(){
        return getInstance().timeService.getWeekOfYear();
    }
    public static Date truncate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        date = cal.getTime();
        return date;
    }
    public static Date addDays(Date startBaseDate, int days){
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(startBaseDate); 
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
    public static Integer getDateDiff(Date from, Date to){
        if(from == null || to == null) return 0;
        long diff     = to.getTime() - from.getTime();
        int diffDays = (int)(diff / (60 * 60 * 1000*24)); 
        return diffDays;
    }
    public static Date getFirstWorkingDayOfWeek ( Date date ){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, (dayOfWeek-2)*-1);
        return cal.getTime();
    } 
    
    public static Date getLastWorkingDayOfWeek ( Date date ){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, 6-dayOfWeek);
        return cal.getTime();
    } 
    public static String currentClientTime(){
        return  currentClientTime( "yyyy-MM-dd hh:mm" );
    }
    
    public static String currentClientTime(String simpleDateFormat){
        Date vDate = new Date();
        SimpleDateFormat pOutformatter = new SimpleDateFormat( simpleDateFormat );
        return pOutformatter.format(vDate);
    }
    public static String convertDate2Str(Date date, String strFormat )
    {
        SimpleDateFormat df = new SimpleDateFormat(strFormat);
        return df.format(date);
    } 
    public static String getRandomTimestamp() {
        
        int targetStringLength = 20;
        StringBuilder sb = new StringBuilder(targetStringLength);

        SimpleDateFormat transFormat = new SimpleDateFormat(OmcSystemConstants.OMC_JAVA_DATE_FORMAT_SIMPLE);
        Date date = new Date();
        sb.append(transFormat.format(date));
        sb.append(NameGeneratorUtil.generateUniqueName("FileTimeStampID").substring(6));
        
        return sb.toString();
          
          
      }
}
