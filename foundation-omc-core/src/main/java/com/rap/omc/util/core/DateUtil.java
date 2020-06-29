package com.rap.omc.util.core;

import java.sql.Timestamp;
/*      */ import java.text.DateFormat;
/*      */ import java.text.ParseException;
import java.text.ParsePosition;
/*      */ import java.text.SimpleDateFormat;
import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.Enumeration;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.HashMap;
import java.util.List;
/*      */ import java.util.Locale;
import java.util.Map;
/*      */ import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
/*      */ import org.slf4j.Logger;
/*      */ import org.slf4j.LoggerFactory;
/*      */ import org.springframework.context.i18n.LocaleContextHolder;

import com.rap.omc.api.util.StrUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

@SuppressWarnings({ "rawtypes", "unchecked" ,"unused"})
public final class DateUtil
/*      */ {
	
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATETIME_MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT_DATETIME_STRING_FORMAT = "yyyyMMddHHmmss";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_YYYYMMDD_FORMAT = "yyyyMMdd";
    /*   74 */ private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    /*      */
    /*   79 */ private static Properties holidayInfo = null;

	private static long lastModified = 0L;

    /*      */ private static final String HOLIDAY_FILENAME = "holiday.properties";

    /*   88 */ private static final String FILE_ENCODING = System.getProperty("file.encoding");

    /*      */ private static final String DEFAULT_DATEFORMAT = "yyyyMMdd";

    /*   92 */ private static final Object DATEFORMAT_LOCK = new Object();

    /*      */
    /*   98 */ private static SimpleDateFormat simpleDateFormat = null;

    /*      */
    /*  103 */ private static final int[] DT = new int[163];

    /*      */
    /*  108 */ private static final int[] DATE_COUNT = { 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    /*      */
    /*  113 */ private static final int[] KK = { /*  114 */ 1, 2, 1, 2, 1, 2, 2, 3, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 1,
            2, 2, 1, 2, 2, 0, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 0, 2, 1, 1, 2, 1, 3, 2, 1, 2, 2, 1, 2, 2, 2, 1, 1, 2,
            1, 1, 2, 1, 2, 1, 2, 2, 0, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0, 2, 2, /*  115 */ 1, 2, 3, 2, 1, 1, 2, 1,
            2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0, 1, 2, 3, 2, 1, 2, 2,
            1, 2, 1, 2, 1, 2, /*  117 */ 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0, 1, 1, 2, 1, 1, 2, 3, 2, 2, 1, 2, 2, 2,
            1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 0, 2, 1, 2, 1, 2, 3, 1, 2, 1, 2,
            1, 2, 1, 2, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, /*  118 */ 0, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0, 2, 1, 2,
            3, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 0, 1, 2, 1, 1, 2, 1, 2, 2, 3, 2, 2, 1, 2,
            /*  120 */ 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0, 1, 2, 1, 2, 1, 3,
            2, 1, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 0, 2, 2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 2, 0, 1, 2, 2,
            1, 4, 1, 2, 1, 2, 1, 2, 1, 2, /*  121 */ 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0, 2, 1, 1, 2, 2, 1, 2, 1, 2,
            2, 1, 2, 0, 1, 2, 3, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, /*  123 */ 0, 2, 1,
            2, 1, 1, 2, 3, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 0, 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2,
            0, 2, 2, 1, 2, 2, 3, 1, 2, 1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0, 1, 2, 1, 2, 1, 2, 2, 1, 2,
            1, 2, 1, /*  124 */ 0, 2, 1, 3, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2, 0, 1, 2,
            1, 1, 2, 1, 2, 3, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, /*  126 */ 0, 2, 1, 2, 1, 1, 2, 1, 1,
            2, 1, 2, 2, 0, 2, 1, 2, 2, 1, 3, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 0, 2, 1, 2, 1, 2,
            2, 1, 2, 1, 2, 1, 1, 0, 2, 1, 2, 2, 3, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1,
            /*  127 */ 0, 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0, 1, 2, 3, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 2,
            1, 1, 2, 1, 2, 2, 2, 0, 1, 2, 2, 1, 1, 2, 3, 1, 2, 1, 2, 2, 1, /*  129 */ 2, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2,
            1, 0, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 0, 1, 2, 2, 1, 2, 4, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2,
            1, 2, 1, 2, 0, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0, 2, 1, 1, 4, 1, 2, 1, 2, 1, 2, 2, 2, 1, /*  130 */ 2,
            1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 0, 2, 2, 1, 1, 2, 1, 1, 4, 1, 2, 2, 1, 2, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1,
            2, 0, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, /*  132 */ 0, 2, 2, 1, 2, 2, 1, 4, 1, 1, 2, 1, 2, 1, 2, 1, 2, 2,
            1, 2, 2, 1, 2, 1, 1, 2, 0, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0, 1, 1, 2, 1, 4, 1, 2, 1, 2, 2, 1, 2, 2, 1,
            1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 0, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, /*  133 */ 0, 2, 2, 3, 1, 2, 1, 1,
            2, 1, 2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0, 2, 2, 1, 2, 1, 2, 1, 3, 2, 1, 2, 1, 2, 2, 1, 2, 2,
            1, 2, 1, 1, 2, 1, 2, 1, /*  135 */ 0, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0, 1, 2, 1, 2, 1, 4, 2, 1, 2, 1,
            2, 1, 2, 1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 2, 2, 0, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 0, 2, 1, 1, 4, 1, 1, 2,
            1, 2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, /*  136 */ 0, 2, 1, 2, 1, 2, 1, 1, 2, 3, 2, 1, 2, 2,
            1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0, 2, 1, 2, 1, 2, 2, 3, 2, 1, 2,
            1, 2, 1, /*  138 */ 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 0, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0, 2, 1, 2,
            1, 3, 2, 1, 2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0, 1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 0,
            2, 2, 2, 3, 2, 1, 1, 2, 1, 1, 2, 2, 1, /*  139 */ 2, 2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 2, 0, 1, 2, 2, 1, 2, 1,
            2, 3, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0, 2, 1, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2,
            /*  141 */ 0, 1, 2, 1, 1, 2, 3, 2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0, 2, 1, 2, 1, 1,
            2, 1, 1, 2, 2, 2, 1, 0, 2, 2, 1, 2, 3, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 0, 2, 2,
            1, 2, 1, 2, 1, 2, 3, 2, 1, 1, 2, /*  142 */ 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 0, 2, 2, 1, 2, 1, 2, 2, 1,
            2, 1, 2, 1, 0, 2, 1, 1, 2, 1, 2, 4, 1, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, /*  144 */ 0, 1,
            2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 0, 2, 1, 2, 1, 3, 2, 1, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2,
            2, 0, 2, 1, 2, 2, 1, 1, 2, 1, 1, 2, 3, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 0, 1, 2, 2, 1, 2, 2, 1, 2,
            1, 2, 1, 1, /*  145 */ 0, 2, 1, 2, 2, 1, 2, 3, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0, 2,
            1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0, 1, 2, 1, 1, 2, 3, 1, 2, 1, 2, 2, 2, 2, /*  147 */ 1, 2, 1, 1, 2, 1, 1,
            2, 1, 2, 2, 2, 0, 1, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 0, 1, 2, 2, 3, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1,
            2, 1, 2, 1, 1, 2, 1, 2, 0, 1, 2, 2, 1, 2, 2, 1, 2, 3, 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2,
            /*  148 */ 0, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0, 2, 1, 1, 2, 1, 3, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 2, 1,
            1, 2, 1, 2, 2, 2, 1, 0, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, /*  150 */ 0, 2, 2, 2, 1, 3, 2, 1, 1, 2, 1, 2,
            1, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 0, 1, 2, 3, 2, 2, 1, 2, 1,
            2, 2, 1, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0, 1, 1, 2, 1, 2, 1, 2, 3, 2, 2, 1, 2, 2, /*  151 */ 1,
            1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 0, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 0, 2, 2, 1, 1, 2, 3, 1, 2, 1, 2, 1,
            2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, /*  153 */ 0, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0, 2, 1, 2, 4,
            2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0, 1, 2, 1, 2, 1, 2, 1, 2, 2, 3, 2, 1, 2, 1,
            2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 2, 0, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, /*  154 */ 0, 2, 1, 1, 2, 1, 3, 2,
            1, 2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 0, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0, 2, 1, 2, 2,
            3, 2, 1, 1, 2, 1, 2, 1, 2, /*  156 */ 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2,
            1, 2, 0, 1, 2, 3, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0, 2, 1, 2, 1, 1, 2, 3,
            2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, /*  157 */ 0, 1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2, 0,
            1, 2, 2, 1, 2, 3, 1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 2, 2, 1, 1, 2, 1, 1, 2, 2, 0, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1,
            2, 1, /*  159 */ 0, 2, 1, 2, 3, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0, 1, 2, 1,
            1, 2, 1, 2, 3, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 0,
            2, 2, 1, 2, 1, 1, 4, 1, 1, 2, 1, 2, 2, /*  160 */ 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 0, 2, 2, 1, 2, 1, 2,
            1, 2, 1, 1, 2, 1, 0, 2, 2, 1, 2, 2, 3, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1,
            /*  162 */ 0, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0, 1, 2, 3, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2,
            1, 1, 2, 2, 1, 2, 2 };

    /*      */
    /*      */ private static SimpleDateFormat getSimpleDateFormat(String format, Locale locale)
    /*      */ {
        /*  170 */ if (simpleDateFormat == null) {
            /*  171 */ synchronized (DATEFORMAT_LOCK) {
                /*      */ try {
                    /*  173 */ simpleDateFormat = new SimpleDateFormat(format, locale);
                    /*      */ } catch (Exception e) {
                    /*  175 */ simpleDateFormat = new SimpleDateFormat("yyyyMMdd", LocaleContextHolder.getLocale());
                    /*      */ }
                /*      */ }
            /*      */ }
        /*  179 */ return simpleDateFormat;
        /*      */ }

    /*      */
    /*      */ public static DateFormat getDateFormat()
    /*      */ {
        /*  188 */ return DateFormat.getDateInstance(2, LocaleContextHolder.getLocale());
        /*      */ }

    /*      */
    /*      */ public static DateFormat getTimeFormat()
    /*      */ {
        /*  197 */ return DateFormat.getTimeInstance(2, LocaleContextHolder.getLocale());
        /*      */ }

    /*      */
    /*      */ public static DateFormat getDateTimeFormat()
    /*      */ {
        /*  206 */ return DateFormat.getDateTimeInstance(2, 2, LocaleContextHolder.getLocale());
        /*      */ }

    /*      */
    /*      */ public static boolean isHoliday(String pDate)
    /*      */ {
        /*  216 */ return isHoliday(pDate, false);
        /*      */ }

    /*      */
    /*      */ public static boolean isHoliday(String pDate, boolean checkSaturday)
    /*      */ {
        /*  228 */ String sDate = toBaseDate(pDate);
        /*  229 */ boolean isHoliday = false;
        /*      */
        /*  231 */ //if (holidayInfo == null) getHolidayInfo();
        /*      */
        /*  233 */ Calendar cal = Calendar.getInstance(Locale.getDefault());
        /*      */
        /*  235 */ String year = sDate.substring(0, 4);
        /*  236 */ String month = sDate.substring(5, 7);
        /*  237 */ String day = sDate.substring(8, 10);
        /*      */
        /*  239 */ cal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));
        /*      */
        /*  241 */ if ((checkSaturday) && (cal.get(7) == 7)) return true;
        /*      */
        /*  243 */ if (cal.get(7) == 1) return true;
        /*      */
        /*  245 */ if ((holidayInfo.getProperty(sDate) != null)
                || (holidayInfo.getProperty("9999" + sDate.substring(4, 10)) != null))
            isHoliday = true;
        /*      */
        /*  247 */ return isHoliday;
    }
        public static boolean isBefore(String pBasisDate, String pComparativeDate){
        if (!(isValid(toYYYYMMDDDate(pBasisDate)))) throw new IllegalArgumentException(
                DateUtil.class.getName() + " - isBefore(String pBasisDate, String pComparativeDate) ▶ Format of date("
                        + pBasisDate + ") is not valid.");
        /*      */
        /*  301 */ if (!(isValid(toYYYYMMDDDate(pComparativeDate)))) throw new IllegalArgumentException(
                DateUtil.class.getName() + " - isBefore(String pBasisDate, String pComparativeDate) ▶ Format of date("
                        + pComparativeDate + ") is not valid.");
        /*  302 */ Calendar cal1 = toCalendar(pBasisDate);
        /*  303 */ Calendar cal2 = toCalendar(pComparativeDate);
        /*      */
        /*  305 */ return cal1.before(cal2);
        /*      */ }

    /*      */
    /*      */ public static boolean isAfter(String pBasisDate, String pComparativeDate)
    /*      */ {
        /*  317 */ if (!(isValid(toYYYYMMDDDate(pBasisDate)))) throw new IllegalArgumentException(
                DateUtil.class.getName() + " - isAfter(String pBasisDate, String pComparativeDate) ▶ Format of date("
                        + pBasisDate + ") is not valid.");
        /*      */
        /*  319 */ if (!(isValid(toYYYYMMDDDate(pComparativeDate)))) throw new IllegalArgumentException(
                DateUtil.class.getName() + " - isAfter(String pBasisDate, String pComparativeDate) ▶ Format of date("
                        + pComparativeDate + ") is not valid.");
        /*  320 */ Calendar cal1 = toCalendar(pBasisDate);
        /*  321 */ Calendar cal2 = toCalendar(pComparativeDate);
        /*      */
        /*  323 */ return cal1.after(cal2);
        /*      */ }

    /*      */
    /*      */ public static int getDaysBetween(String pFromDate, String pToDate)
    /*      */ {
        /*  346 */ Calendar cal1 = toCalendar(pFromDate, "000000");
        /*  347 */ Calendar cal2 = toCalendar(pToDate, "000000");
        /*      */
        /*  349 */ double result = 0.0D;
        /*      */
        /*  351 */ if (cal1.getTime().compareTo(cal2.getTime()) == -1)
            /*  352 */ result = (cal2.getTime().getTime() - cal1.getTime().getTime()) / 86400000.0D;
        /*      */ else {
            /*  354 */ result = (cal1.getTime().getTime() - cal2.getTime().getTime()) / 86400000.0D;
            /*      */ }
        /*  356 */ return (int)Math.round(result);
        /*      */ }

    /*      */
    /*      */ public static HashMap<String, String> getHolidayByMonth(String pDate)
    /*      */ {
        /*  367 */ if (pDate.length() != 6) throw new IllegalArgumentException(
                DateUtil.class.getName() + " - getHolidayByMonth(String pDate) ▶  Valid date format is yyyyMMdd.");
        /*      */
        /*  369 */ HashMap haspmap = new HashMap();
        /*  370 */ //getHolidayInfo();
        /*  371 */ Enumeration keyList = holidayInfo.propertyNames();
        /*  372 */ while (keyList.hasMoreElements()) {
            /*  373 */ String key = (String)keyList.nextElement();
            /*  374 */ if ((!(key.startsWith("9999." + pDate.substring(4, 6))))
                    && (!(key.startsWith(pDate.substring(0, 4) + "." + pDate.substring(4, 6)))))
                continue;
            haspmap.put(key, holidayInfo.getProperty(key));
            /*      */ }
        /*  376 */ return haspmap;
        /*      */ }

    /*      */
    /*      */ public static int getLastDate()
    /*      */ {
        /*  392 */ GregorianCalendar calendar = new GregorianCalendar();
        /*  393 */ calendar.clear();
        /*  394 */ calendar.add(2, 1);
        /*  395 */ calendar.set(5, 1);
        /*  396 */ calendar.add(5, -1);
        /*      */
        /*  398 */ return calendar.get(5);
        /*      */ }

    /*      */
    /*      */ public static int getLastDate(String pDate)
    /*      */ {
        /*  419 */ if (!(isValid(toYYYYMMDDDate(pDate)))) throw new IllegalArgumentException(DateUtil.class.getName()
                + " - getLastDate(String pDate) ▶ Format of date(" + pDate + ") is not valid.");
        /*      */
        /*  421 */ Calendar cal = toCalendar(pDate);
        /*      */
        /*  423 */ cal.add(2, 1);
        /*  424 */ cal.set(5, 1);
        /*  425 */ cal.add(5, -1);
        /*      */
        /*  427 */ return cal.get(5);
        /*      */ }

    /*      */
    /*      */ public static String getNextDate(String pDate, int pDays)
    /*      */ {
        /*  451 */ if (pDays < 0) {
            /*  452 */ LOGGER.debug(DateUtil.class.getName()
                    + " -  getNextDate(String pDate, int pDays) ▶ [pDays] Int value under 0 is not allowed and original data will be returned.");
            /*  453 */ return pDate;
            /*      */ }
        /*      */
        /*  456 */ if ((!(isValid(toYYYYMMDDDate(pDate)))) || (pDate.length() < 8))
            throw new IllegalArgumentException(DateUtil.class.getName()
                    + " - getNextDate(String pDate, int pDays) ▶ Format of date(" + pDate + ") is not valid.");
        /*      */
        /*  460 */ Calendar cal = toCalendar(pDate);
        /*      */
        /*  462 */ cal.add(5, pDays);
        /*      */
        /*  464 */ return format(cal.getTime());
        /*      */ }

    /*      */
    /*      */ public static Date getNextDate(Date pDate, int pDays)
    /*      */ {
        /*  488 */ if (pDays < 0) {
            /*  489 */ LOGGER.debug(DateUtil.class.getName()
                    + " -  getNextDate(String pDate, int pDays) ▶ [pDays] Int value under 0 is not allowed and original data will be returned.");
            /*  490 */ return pDate;
            /*      */ }
        /*      */
        /*  493 */ GregorianCalendar cal = new GregorianCalendar();
        /*  494 */ if (cal.isSet(5)) {
            /*  495 */ cal.clear();
            /*      */ }
        /*  497 */ cal.setTime(pDate);
        /*      */
        /*  499 */ cal.add(5, pDays);
        /*      */
        /*  501 */ return cal.getTime();
        /*      */ }

    /*      */
    /*      */ public static String getPrevDate(String pDate, int pDays)
    /*      */ {
        /*  521 */ if (pDays < 0) {
            /*  522 */ LOGGER.debug(DateUtil.class.getName()
                    + " -  getPrevDate(String pDate, int pDays) ▶ [pDays] Int value under 0 is not allowed and original data will be returned.");
            /*      */
            /*  524 */ return pDate;
            /*      */ }
        /*      */
        /*  527 */ if (!(isValid(toYYYYMMDDDate(pDate)))) throw new IllegalArgumentException(DateUtil.class.getName()
                + " - getPrevDate(String pDate, int pDays) ▶ Format of date(" + pDate + ") is not valid.");
        /*      */
        /*  531 */ Calendar cal = toCalendar(pDate);
        /*      */
        /*  533 */ cal.add(5, -pDays);
        /*      */
        /*  535 */ return format(cal.getTime());
        /*      */ }

    /*      */
    /*      */ public static String getNextDateSkipLastHoliday(String pDate, int pDays)
    /*      */ {
        /*  556 */ return getNextDateSkipLastHoliday(pDate, pDays, false);
        /*      */ }

    /*      */
    /*      */ public static String getNextDateSkipLastHoliday(String pDate, int pHowMany, boolean checkSaturday)
    /*      */ {
        /*  577 */ if (!(isValid(toYYYYMMDDDate(pDate)))) throw new IllegalArgumentException(DateUtil.class.getName()
                + " - getNextDateSkipLastHoliday(String pDate, int pHowMany, boolean checkSaturday) ▶ Format of date("
                + pDate + ") is not valid.");
        /*      */
        /*  579 */ Calendar cal = toCalendar(pDate);
        /*      */
        /*  581 */ cal.add(5, pHowMany);
        /*      */
        /*  583 */ return getNextDayIfHoliday(cal, checkSaturday);
        /*      */ }

    /*      */
    /*      */ public static String getNextDateSkipHoliday(String pDate, int pDays)
    /*      */ {
        /*  604 */ return getNextDateSkipHoliday(pDate, pDays, false);
        /*      */ }

    /*      */
    /*      */ public static String getNextDateSkipHoliday(String pDate, int pDays, boolean checkSaturday)
    /*      */ {
        /*  628 */ if (!(isValid(toYYYYMMDDDate(pDate)))) throw new IllegalArgumentException(DateUtil.class.getName()
                + " - getNextDateSkipHoliday(String pDate, int pDays, boolean checkSaturday) ▶ Format of date(" + pDate
                + ") is not valid.");
        /*      */
        /*  630 */ Calendar cal = null;
        /*  631 */ String rDate = pDate;
        /*  632 */ for (int d = 0; d < pDays; ++d) {
            /*  633 */ cal = toCalendar(rDate);
            /*  634 */ cal.add(5, 1);
            /*  635 */ rDate = getNextDayIfHoliday(cal, checkSaturday);
            /*      */ }
        /*  637 */ return rDate;
        /*      */ }

    /*      */
    /*      */ public static String getNextWeekDate(String pDate, int pWeeks)
    /*      */ {
        /*  659 */ if (pWeeks < 0) {
            /*  660 */ LOGGER.debug(DateUtil.class.getName()
                    + " - getNextWeekDate(String pDate, int pWeeks) ▶ Int value under 0 is not allowed and original data will be returned.");
            /*  661 */ return pDate;
            /*      */ }
        /*      */
        /*  664 */ if ((!(isValid(toYYYYMMDDDate(pDate)))) || (pDate.length() < 8))
            throw new IllegalArgumentException("Format of date(" + pDate + ") is not valid.");
        /*      */
        /*  666 */ Calendar cal = toCalendar(pDate);
        /*      */
        /*  668 */ cal.add(5, pWeeks * 7);
        /*      */
        /*  670 */ return format(cal.getTime());
        /*      */ }

    /*      */
    /*      */ private static synchronized String format(Date date){
        /*  674 */ return getSimpleDateFormat("yyyyMMdd", LocaleContextHolder.getLocale()).format(date);
        /*      */ }

    /*      */
    /*      */ public static String getPrevWeekDate(String pDate, int pWeeks)
    /*      */ {
        /*  696 */ if (!(isValid(toYYYYMMDDDate(pDate))))
            throw new IllegalArgumentException("Format of date(" + pDate + ") is not valid.");
        /*      */
        /*  698 */ if (pWeeks < 0) {
            /*  699 */ LOGGER.debug(DateUtil.class.getName()
                    + " - getPrevWeekDate(String pDate, int pWeeks) ▶ Int value under 0 is not allowed and original data will be returned.");
            /*  700 */ return pDate;
            /*      */ }
        /*      */
        /*  703 */ Calendar cal = toCalendar(pDate);
        /*      */
        /*  705 */ cal.add(5, pWeeks * -7);
        /*      */
        /*  707 */ return format(cal.getTime());
        /*      */ }

    /*      */
    /*      */ public static String getNextMonthDate(String pDate, int pMonths)
    /*      */ {
        /*  730 */ if (!(isValid(toYYYYMMDDDate(pDate))))
            throw new IllegalArgumentException("Format of date(" + pDate + ") is not valid.");
        /*      */
        /*  732 */ if (pMonths < 0) {
            /*  733 */ LOGGER.debug(DateUtil.class.getName()
                    + " - getNextMonthDate(String pDate, int pMonths) ▶ Int value under 0 is not allowed and original data will be returned.");
            /*  734 */ return pDate;
            /*      */ }
        /*      */
        /*  737 */ Calendar cal = toCalendar(pDate);
        /*      */
        /*  739 */ cal.add(2, pMonths);
        /*      */
        /*  741 */ return format(cal.getTime());
        /*      */ }

    /*      */
    /*      */ public static String getPrevMonthDate(String pDate, int pMonth)
    /*      */ {
        /*  764 */ if ((!(isValid(toYYYYMMDDDate(pDate)))) &&
        /*  765 */ (!(isValid(toYYYYMMDDDate(pDate)))))
            throw new IllegalArgumentException("Format of date(" + pDate + ") is not valid.");
        /*      */
        /*  768 */ if (pMonth < 0) {
            /*  769 */ LOGGER.debug(DateUtil.class.getName()
                    + " - getPrevMonthDate(String pDate, int pMonth) ▶ Int value under 0 is not allowed and original data will be returned.");
            /*  770 */ return pDate;
            /*      */ }
        /*      */
        /*  773 */ Calendar cal = toCalendar(pDate);
        /*      */
        /*  775 */ cal.add(2, -pMonth);
        /*      */
        /*  777 */ return format(cal.getTime());
        /*      */ }

    /*      */
    /*      */ public static String getNextMonthDateSkipLastHoliday(String pDate, int pMonths)
    /*      */ {
        /*  799 */ return getNextMonthDateSkipLastHoliday(pDate, pMonths, false);
        /*      */ }

    /*      */
    /*      */ public static String getNextMonthDateSkipLastHoliday(String pDate, int pMonths, boolean checkSaturday)
    /*      */ {
        /*  821 */ if (!(isValid(toYYYYMMDDDate(pDate))))
            throw new IllegalArgumentException("Format of date(" + pDate + ") is not valid.");
        /*      */
        /*  823 */ String sDate = getNextMonthDate(pDate, pMonths);
        /*  824 */ Calendar cal = toCalendar(sDate);
        /*      */
        /*  826 */ return getNextDayIfHoliday(cal, checkSaturday);
        /*      */ }

    /*      */
    /*      */ private static String getNextDayIfHoliday(Calendar cal, boolean checkSaturday)
    /*      */ {
        /*  837 */ while (isHoliday(format(cal.getTime()), checkSaturday)) {
            /*  838 */ cal.add(5, 1);
            /*      */ }
        /*      */
        /*  841 */ return format(cal.getTime());
        /*      */ }

    /*      */
    /*      */ public static boolean isLeapYear(String pDate)
    /*      */ {
        /*  851 */ GregorianCalendar gCal = new GregorianCalendar();
        /*  852 */ gCal.clear();
        /*  853 */ if (pDate.length() == 4) {
            /*  854 */ gCal.setTime(new Date(toCalendar(pDate + "0101").getTime().getTime()));
            /*      */ } else {
            /*  856 */ if (!(isValid(toYYYYMMDDDate(pDate))))
                throw new IllegalArgumentException("Format of date(" + pDate + ") is not valid.");
            /*  857 */ gCal.setTime(new Date(toCalendar(pDate).getTime().getTime()));
            /*      */ }
        /*      */
        /*  860 */ return gCal.isLeapYear(gCal.get(1));
        /*      */ }

    /*      */
    /*      */ public static String getHanDay()
    /*      */ {
        /*  869 */ return getHanDay(getDate());
        /*      */ }

    /*      */
    /*      */ public static String getHanDay(String pDate)
    /*      */ {
        /*  880 */ if (!(isValid(toYYYYMMDDDate(pDate))))
            throw new IllegalArgumentException("Format of date(" + pDate + ") is not valid.");
        /*      */
        /*  882 */ Calendar cal = toCalendar(pDate);
        /*  883 */ SimpleDateFormat dateForm = new SimpleDateFormat("EEE", Locale.KOREA);
        /*      */
        /*  885 */ return dateForm.format(cal.getTime());
        /*      */ }

    /*      */
    /*      */ public static String getEngDay()
    /*      */ {
        /*  894 */ return getEngDay(getDate());
        /*      */ }

    /*      */
    /*      */ public static String getEngDay(String pDate)
    /*      */ {
        /*  905 */ if (!(isValid(toYYYYMMDDDate(pDate))))
            throw new IllegalArgumentException("Format of date(" + pDate + ") is not valid.");
        /*      */
        /*  907 */ Calendar cal = toCalendar(pDate);
        /*      */
        /*  909 */ SimpleDateFormat dateForm = new SimpleDateFormat("EEEEEEEE", Locale.ENGLISH);
        /*      */
        /*  911 */ return dateForm.format(cal.getTime());
        /*      */ }

    /*      */
    /*      */ public static int getDay()
    /*      */ {
        /*  920 */ GregorianCalendar calendar = new GregorianCalendar();
        /*  921 */ return calendar.get(7);
        /*      */ }

    /*      */
    /*      */ public static int getDay(String pDate)
    /*      */ {
        /*  932 */ Calendar cal = toCalendar(pDate);
        /*      */
        /*  934 */ return cal.get(7);
        /*      */ }

    /*      */
    /*      */ public static String getDate(String pattern)
    /*      */ {
        /*  953 */ return getDate(pattern, LocaleContextHolder.getLocale());
        /*      */ }

    /*      */
    /*      */ public static String getDate(String pattern, Locale locale)
/*      */   {
/*  973 */     SimpleDateFormat dateForm = null;
/*      */     try {
/*  975 */       if (pattern != null) {
/*  976 */         dateForm = new SimpleDateFormat(pattern, locale);
                  }
else {
	dateForm = getSimpleDateFormat("yyyyMMdd", locale);
}

/*  978 */       
/*      */     }
/*      */     catch (IllegalArgumentException ie) {
/*  981 */       LOGGER.debug(DateUtil.class.getName() + " - getDate(String pattern, Locale locale) ▶ Format (" + pattern + ") is  IllegalArgument");
/*  982 */       throw new IllegalArgumentException(ie.getMessage());
/*      */     }
/*      */ 
/*  985 */     return dateForm.format(new Date());
/*      */   }

    /*      */
    /*      */ public static int getMonth()
    /*      */ {
        /* 1001 */ GregorianCalendar calendar = new GregorianCalendar();
        /* 1002 */ return (calendar.get(2) + 1);
        /*      */ }

    /*      */
    /*      */ public static String getEngMonth()
    /*      */ {
        /* 1018 */ GregorianCalendar calendar = new GregorianCalendar();
        /* 1019 */ SimpleDateFormat monthForm = new SimpleDateFormat("MMMMM", Locale.ENGLISH);
        /*      */
        /* 1021 */ return monthForm.format(calendar.getTime());
        /*      */ }

    /*      */
    /*      */ public static String getShortEngMonth()
    /*      */ {
        /* 1030 */ GregorianCalendar calendar = new GregorianCalendar();
        /* 1031 */ SimpleDateFormat monthForm = new SimpleDateFormat("MMM", Locale.ENGLISH);
        /*      */
        /* 1033 */ return monthForm.format(calendar.getTime());
        /*      */ }

    /*      */
    /*      */ public static String getDate()
    /*      */ {
        /* 1049 */ return format(new Date());
        /*      */ }

    /*      */
    /*      */ public static String getYear(String argYear)
    /*      */ {
        /* 1063 */ String year = argYear;
        /* 1064 */ String resultYear = "";
        /* 1065 */ String tempYear = "";
        /*      */
        /* 1067 */ if (year != null) year = year.trim();
        /*      */
        /* 1069 */ if ((year.length() > 4) || (year.length() == 0))
            throw new IllegalArgumentException("Year(" + argYear + ") is not valid.");
        /*      */
        /* 1071 */ for (int inx = 0; inx < year.length(); ++inx) {
            /* 1072 */ if (Character.isDigit(year.charAt(inx))) continue;
            throw new IllegalArgumentException("Year(" + argYear + ") is not valid.");
            /*      */ }
        /*      */
        /* 1075 */ if (year.length() == 4) {
            /* 1076 */ resultYear = year;
            /*      */ } else {
            /* 1078 */ if (year.length() == 3)/* 1079 */ tempYear = year.substring(1, 3);
            /* 1080 */ else if (year.length() == 1) {
                /* 1081 */ tempYear = "0" + year;
                /*      */ }
            /* 1083 */ if (Integer.parseInt(tempYear) > 50)/* 1084 */ resultYear = "19" + tempYear;
            /*      */ else {
                /* 1086 */ resultYear = "20" + tempYear;
                /*      */ }
            /*      */ }
        /*      */
        /* 1090 */ return resultYear;
        /*      */ }

    /*      */
    /*      */ public static String toBaseDate(String argDate)
    /*      */ {
        /* 1101 */ if (!(isValid(toYYYYMMDDDate(argDate))))
            throw new IllegalArgumentException("Format of date(" + argDate + ") is not valid.");
        /*      */
        /* 1103 */ String formattedDate = toYYYYMMDDDate(argDate);
        /*      */ try
        /*      */ {
            /* 1106 */ Integer.parseInt(formattedDate);
            /*      */ } catch (NumberFormatException ne) {
            /* 1108 */ throw new NumberFormatException(DateUtil.class.getName()
                    + " - toBaseDate(String argDate) ▶ Format of date(" + argDate + ") is not valid.");
            /*      */ }
        /*      */
        /* 1111 */ if (formattedDate != "") return formattedDate.substring(0, 4) + "." + formattedDate.substring(4, 6)
                + "." + formattedDate.substring(6, 8);
        /*      */
        /* 1113 */ return argDate;
        /*      */ }

    /*      */
    /*      */ public static String toYYYYMMDDDate(String argDate)
    /*      */ {
        /* 1123 */ boolean isMunja = false;
        /* 1124 */ boolean isCorrectArg = true;
        /* 1125 */ String subArg = "";
        /* 1126 */ String date = "";
        /* 1127 */ String result = "";
        /*      */
        /* 1129 */ if (argDate != null) subArg = argDate.trim();
        /*      */
        /* 1131 */ for (int inx = 0; inx < subArg.length(); ++inx) {
            /* 1132 */ if ((Character.isLetter(subArg.charAt(inx))) || (subArg.charAt(inx) == ' ')) {
                /* 1133 */ isCorrectArg = false;
                /* 1134 */ break;
                /*      */ }
            /*      */ }
        /*      */
        /* 1138 */ if (!(isCorrectArg))
            throw new IllegalArgumentException("Format of date(" + argDate + ") is not valid.");
        /*      */
        /* 1142 */ if (subArg.length() != 8) {
            /* 1143 */ if ((subArg.length() != 6) && (subArg.length() != 10))
                throw new IllegalArgumentException("Format of date(" + argDate + ") is not valid.");
            /*      */
            /* 1145 */ if (subArg.length() == 6) {
                /* 1146 */ if (Integer.parseInt(subArg.substring(0, 2)) > 50)/* 1147 */ date = "19";
                /*      */ else {
                    /* 1149 */ date = "20";
                    /*      */ }
                /* 1151 */ result = date + subArg;
                /*      */ }
            /*      */
            /* 1154 */ if (subArg.length() == 10)
                result = subArg.substring(0, 4) + subArg.substring(5, 7) + subArg.substring(8, 10);
            /*      */ }
        /*      */ else {
            /*      */ try {
                /* 1158 */ Integer.parseInt(subArg);
                /*      */ } catch (NumberFormatException ne) {
                /* 1160 */ isMunja = true;
                /*      */ }
            /*      */
            /* 1163 */ if (isMunja)
            /*      */ {
                /* 1165 */ date = subArg.substring(0, 2) + subArg.substring(3, 5) + subArg.substring(6, 8);
                /* 1166 */ if (Integer.parseInt(subArg.substring(0, 2)) > 50)/* 1167 */ result = "19" + date;
                /*      */ else/* 1169 */ result = "20" + date;
                /*      */ }
            /*      */ else
            /*      */ {
                /* 1173 */ return subArg;
                /*      */ }
            /*      */ }
        /* 1176 */ return result;
        /*      */ }

    /*      */
    /*      */ public static String toLunarDate(String argDate)
    /*      */ {
        /* 1186 */ if (!(isValid(toYYYYMMDDDate(argDate))))
            throw new IllegalArgumentException("Format of date(" + argDate + ") is not valid.");
        /*      */
        /* 1188 */ int lday = 0;
        /* 1189 */ int jcount = 0;
        /* 1190 */ boolean leapMonth = false;
        /* 1191 */ long td2 = 0L;
        /*      */
        /* 1193 */ for (int inx = 0; inx < 163; ++inx) {
            /* 1194 */ DT[inx] = 0;
            /* 1195 */ for (int jnx = 0; jnx < 12; ++jnx) {
                /* 1196 */ switch (KK[(inx * 13 + jnx)])
                /*      */ {
                    /*      */ case 1:
                        /*      */ case 3:
                        /* 1199 */ DT[inx] += 29;
                        /* 1200 */ break;
                    /*      */ case 2:
                        /*      */ case 4:
                        /* 1203 */ DT[inx] += 30;
                        /*      */ }
                /*      */
                /*      */ }
            /*      */
            /* 1208 */ switch (KK[(inx * 13 + 12)])
            /*      */ {
                /*      */ case 0:
                    /* 1210 */ break;
                /*      */ case 1:
                    /*      */ case 3:
                    /* 1213 */ DT[inx] += 29;
                    /* 1214 */ break;
                /*      */ case 2:
                    /*      */ case 4:
                    /* 1217 */ DT[inx] += 30;
                    /*      */ }
            /*      */
            /*      */ }
        /*      */
        /* 1222 */ td2 = 686686L;
        /*      */
        /* 1224 */ String standardDate = toYYYYMMDDDate(argDate);
        /* 1225 */ int syear = Integer.parseInt(standardDate.substring(0, 4));
        /* 1226 */ int smonth = Integer.parseInt(standardDate.substring(4, 6));
        /* 1227 */ int sday = Integer.parseInt(standardDate.substring(6, 8));
        /*      */int inx = 0;
        /* 1229 */ long k11 = syear - 1;
        /* 1230 */ long td3 = k11 * 365L + k11 / 4L - (k11 / 100L) + k11 / 400L;
        /* 1231 */ leapMonth = (syear % 400 == 0) || ((syear % 100 != 0) && (syear % 4 == 0));
        /* 1232 */ if (leapMonth)/* 1233 */ DATE_COUNT[1] = 29;
        /*      */ else {
            /* 1235 */ DATE_COUNT[1] = 28;
            /*      */ }
        /* 1237 */ for (inx = 0; inx < smonth - 1; ++inx) {
            /* 1238 */ td3 += DATE_COUNT[inx];
            /*      */ }
        /*      */
        /* 1241 */ td3 += sday;
        /*      */
        /* 1244 */ long td0 = td3 - td2 + 1L;
        /*      */
        /* 1247 */ long td1 = DT[0];
        /* 1248 */ for (inx = 0; inx < 163; ++inx) {
            /* 1249 */ if (td0 <= td1) break;
            /* 1250 */ td1 += DT[(inx + 1)];
            /*      */ }
        /* 1252 */ int lyear = inx + 1881;
        /*      */
        /* 1255 */ td1 -= DT[inx];
        /* 1256 */ td0 -= td1;
        /*      */
        /* 1258 */ if (KK[(inx * 13 + 12)] != 0)/* 1259 */ jcount = 13;
        /*      */ else {
            /* 1261 */ jcount = 12;
            /*      */ }
        /* 1263 */ int vM2 = 0;
        /*      */int jnx = 0;
        /* 1265 */ for (jnx = 0; jnx < jcount; ++jnx) {
            /* 1266 */ if (KK[(inx * 13 + jnx)] <= 2) ++vM2;
            /*      */ int vM1;
            /* 1267 */ if (KK[(inx * 13 + jnx)] <= 2)/* 1268 */ vM1 = KK[(inx * 13 + jnx)] + 28;
            /*      */ else {
                /* 1270 */ vM1 = KK[(inx * 13 + jnx)] + 26;
                /*      */ }
            /* 1272 */ if (td0 <= vM1) break;
            /* 1273 */ td0 -= vM1;
            /*      */ }
        /*      */
        /* 1276 */ int lmonth = vM2;
        /*      */
        /* 1278 */ lday = (int)td0;
        /*      */
        /* 1280 */ inx = (int)((td3 + 4L) % 10L);
        /* 1281 */ jnx = (int)((td3 + 2L) % 12L);
        /*      */
        /* 1283 */ Date lunarDate = createDate(lyear, lmonth, lday);
        /*      */
        /* 1285 */ return format(lunarDate);
        /*      */ }

    /*      */
    /*      */ public static String toSolarDate(String argDate)
    /*      */ {
        /* 1296 */ return toSolarDate(argDate, false);
        /*      */ }

    /*      */
    /*      */ public static String toSolarDate(String argDate, boolean isLeap)
    /*      */ {
        /* 1307 */ int sday = 0;
        /* 1308 */ int vM2 = 0;
        /* 1309 */ boolean leap = false;
        /* 1310 */ long vTd = 0L;
        /*      */
        /* 1312 */ for (int inx = 0; inx < 163; ++inx) {
            /* 1313 */ DT[inx] = 0;
            /* 1314 */ for (int jnx = 0; jnx < 12; ++jnx) {
                /* 1315 */ switch (KK[(inx * 13 + jnx)])
                /*      */ {
                    /*      */ case 1:
                        /*      */ case 3:
                        /* 1318 */ DT[inx] += 29;
                        /* 1319 */ break;
                    /*      */ case 2:
                        /*      */ case 4:
                        /* 1322 */ DT[inx] += 30;
                        /*      */ }
                /*      */
                /*      */ }
            /*      */
            /* 1327 */ switch (KK[(inx * 13 + 12)])
            /*      */ {
                /*      */ case 0:
                    /* 1329 */ break;
                /*      */ case 1:
                    /*      */ case 3:
                    /* 1332 */ DT[inx] += 29;
                    /* 1333 */ break;
                /*      */ case 2:
                    /*      */ case 4:
                    /* 1336 */ DT[inx] += 30;
                    /*      */ }
            /*      */
            /*      */ }
        /*      */
        /* 1342 */ String standardDate = toYYYYMMDDDate(argDate);
        /* 1343 */ int lyear = Integer.parseInt(standardDate.substring(0, 4));
        /* 1344 */ int lmonth = Integer.parseInt(standardDate.substring(4, 6));
        /* 1345 */ int lday = Integer.parseInt(standardDate.substring(6, 8));
        /*      */
        /* 1347 */ boolean isLeapYear = isLeap;
        /*      */
        /* 1349 */ if ((!(isLeapYear)) && (!(verifyDate(lyear, lmonth, lday, "To Solar-")))) {
            /* 1350 */ LOGGER.debug(DateUtil.class.getName() + " - toSolarDate(String argDate, boolean isLeap) ▶ "
                    + "Date Range Error");
            /* 1351 */ throw new IllegalArgumentException("Date Range Error");
            /*      */ }
        /*      */
        /* 1354 */ if ((isLeapYear) && (!(verifyDate(lyear, lmonth, lday, "To Solar+")))) {
            /* 1355 */ LOGGER.debug(DateUtil.class.getName() + " - toSolarDate(String argDate, boolean isLeap) ▶ "
                    + "Date Range or Leap Check Error");
            /* 1356 */ throw new IllegalArgumentException("Date Range or Leap Check Error");
            /*      */ }
        /*      */
        /* 1359 */ int vM1 = -1;
        /* 1360 */ vTd = 0L;
        /*      */ int inx = 0;
        /* 1362 */ if (lyear != 1881) {
            /* 1363 */ vM1 = lyear - 1882;
            /* 1364 */ for (inx = 0; inx <= vM1; ++inx) {
                /* 1365 */ for (int jnx = 0; jnx < 13; ++jnx) {
                    /* 1366 */ vTd += KK[(inx * 13 + jnx)];
                    /*      */ }
                /* 1368 */ if (KK[(inx * 13 + 12)] == 0)/* 1369 */ vTd += 336L;
                /*      */ else {
                    /* 1371 */ vTd += 362L;
                    /*      */ }
                /*      */ }
            /*      */
            /* 1375 */ if (((lyear == 1966) && (lmonth == 1) && (lday != 30)) ||
            /* 1376 */ ((lyear == 1990) && (lmonth == 9) && (lday != 30)) || (
            /* 1377 */ (lyear == 2036) && (lmonth == 11) && (lday != 30))) {
                /* 1378 */ vTd += 1L;
                /*      */ }
            /* 1380 */ if (((lyear == 2004) && (lmonth == 10)) ||
            /* 1381 */ ((lyear == 2006) && (lmonth == 1)) ||
            /* 1382 */ ((lyear == 2016) && (lmonth == 1)) || (
            /* 1383 */ (lyear == 2017) && (lmonth == 2))) {
                /* 1384 */ vTd -= 1L;
                /*      */ }
            /*      */ }
        /*      */
        /* 1388 */ ++vM1;
        /* 1389 */ int vN2 = lmonth - 1;
        /* 1390 */ vM2 = -1;
        /*      */ while (true)
        /*      */ {
            /* 1393 */ ++vM2;
            /* 1394 */ if (KK[(vM1 * 13 + vM2)] > 2) {
                /* 1395 */ vTd = vTd + 26L + KK[(vM1 * 13 + vM2)];
                /* 1396 */ ++vN2;
            }
            /* 1397 */ if (vM2 == vN2) {
                /*      */ break;
                /*      */ }
            /* 1400 */ vTd = vTd + 28L + KK[(vM1 * 13 + vM2)];
            /*      */ }
        /*      */
        /* 1404 */ if (isLeapYear) vTd = vTd + 28L + KK[(vM1 * 13 + vM2)];
        /*      */
        /* 1406 */ vTd = vTd + lday + 29L;
        /* 1407 */ vM1 = 1880;
        /*      */ while (true)
        /*      */ {
            /* 1410 */ ++vM1;
            /* 1411 */ leap = (vM1 % 400 == 0) || ((vM1 % 100 != 0) && (vM1 % 4 == 0));
            /* 1412 */ if (leap)/* 1413 */ vM2 = 366;
            /*      */ else {
                /* 1415 */ vM2 = 365;
                /*      */ }
            /* 1417 */ if (vTd < vM2) break;
            /* 1418 */ vTd -= vM2;
            /*      */ }
        /*      */
        /* 1421 */ int syear = vM1;
        /* 1422 */ DATE_COUNT[1] = (vM2 - 337);
        /* 1423 */ vM1 = 0;
        /*      */ while (true) {
            /* 1425 */ ++vM1;
            /* 1426 */ if (vTd <= DATE_COUNT[(vM1 - 1)]) break;
            /* 1427 */ vTd -= DATE_COUNT[(vM1 - 1)];
            /*      */ }
        /*      */
        /* 1430 */ int smonth = vM1;
        /* 1431 */ sday = (int)vTd;
        /*      */
        /* 1433 */ long lyy = syear - 1L;
        /*      */
        /* 1435 */ vTd = lyy * 365L + lyy / 4L - (lyy / 100L) + lyy / 400L;
        /*      */
        /* 1437 */ leap = (syear % 400 == 0) || ((syear % 100 != 0) && (syear % 4 == 0));
        /*      */
        /* 1439 */ if (leap)/* 1440 */ DATE_COUNT[1] = 29;
        /*      */ else {
            /* 1442 */ DATE_COUNT[1] = 28;
            /*      */ }
        /*      */
        /* 1445 */ for (inx = 0; inx < smonth - 1; ++inx) {
            /* 1446 */ vTd += DATE_COUNT[inx];
            /*      */ }
        /* 1448 */ vTd += sday;
        /*      */
        /* 1450 */ inx = (int)(vTd % 10L);
        /* 1451 */ inx = (inx + 4) % 10;
        /* 1452 */ int jnx = (int)(vTd % 12L);
        /* 1453 */ jnx = (jnx + 2) % 12;
        /*      */
        /* 1455 */ Date solarDate = createDate(syear, smonth, sday);
        /*      */
        /* 1457 */ return format(solarDate);
        /*      */ }

    /*      */
    /*      */ public static Calendar toCalendar(String pDate)
    /*      */ {
        /* 1467 */ String date = toYYYYMMDDDate(pDate);
        /*      */
        /* 1469 */ return createCalendar(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6)),
                Integer.parseInt(date.substring(6, 8)));
        /*      */ }

    /*      */
    /*      */ public static Calendar toCalendar(String pDate, String time)
    /*      */ {
        /* 1480 */ String date = toYYYYMMDDDate(pDate);
        /*      */
        /* 1482 */ return createCalendar(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6)),
                Integer.parseInt(date.substring(6, 8)), Integer.parseInt(time.substring(0, 2)),
                Integer.parseInt(time.substring(2, 4)), /* 1483 */ Integer.parseInt(time.substring(4, 6)));
        /*      */ }

    /*      */
    /*      */ public static Date toDate(String pDate)
    /*      */ {
        /* 1493 */ String date = toYYYYMMDDDate(pDate);
        /*      */
        /* 1495 */ return createDate(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6)),
                Integer.parseInt(date.substring(6, 8)));
        /*      */ }

    /*      */
    /*      */ public static Date createDate(int year, int month, int date)
    /*      */ {
        /* 1507 */ return createCalendar(year, month, date).getTime();
        /*      */ }

    /*      */
    /*      */ public static Date createDate(int year, int month, int date, int hour, int minute)
    /*      */ {
        /* 1521 */ return createCalendar(year, month, date, hour, minute).getTime();
        /*      */ }

    /*      */
    /*      */ public static Date createDate(int year, int month, int date, int hour, int minute, int second)
    /*      */ {
        /* 1536 */ return createCalendar(year, month, date, hour, minute, second).getTime();
        /*      */ }

    /*      */
    /*      */ public static Calendar createCalendar(int year, int month, int date)
    /*      */ {
        /* 1548 */ GregorianCalendar calendar = new GregorianCalendar();
        /* 1549 */ calendar.set(year, month - 1, date);
        /* 1550 */ return calendar;
        /*      */ }

    /*      */
    /*      */ public static Calendar createCalendar(int year, int month, int date, int hour, int minute)
    /*      */ {
        /* 1564 */ GregorianCalendar calendar = new GregorianCalendar();
        /* 1565 */ calendar.clear();
        /* 1566 */ calendar.set(year, month - 1, date, hour, minute);
        /* 1567 */ return calendar;
        /*      */ }

    /*      */
    /*      */ public static Calendar createCalendar(int year, int month, int date, int hour, int minute, int second)
    /*      */ {
        /* 1582 */ GregorianCalendar calendar = new GregorianCalendar();
        /* 1583 */ calendar.clear();
        /* 1584 */ calendar.set(year, month - 1, date, hour, minute, second);
        /* 1585 */ return calendar;
        /*      */ }

    /*      */
    /*      */ private static boolean verifyDate(int year, int month, int day, String whatToDo)
    /*      */ {
        /* 1598 */ if ((year < 1881) || (year > 2043) || (month < 1) || (month > 12)) return false;
        /*      */
        /* 1600 */ if ((whatToDo.equals("To Lunar")) &&
        /* 1601 */ (day > DATE_COUNT[(month - 1)])) return false;
        /*      */
        /* 1604 */ if (whatToDo.equals("To Solar+")) {
            /* 1605 */ if (KK[((year - 1881) * 13 + 12)] < 1) return false;
            /* 1606 */ if (KK[((year - 1881) * 13 + month)] < 3) return false;
            /* 1607 */ if (KK[((year - 1881) * 13 + month)] + 26 < day) return false;
            /*      */ }
        /*      */
        /* 1610 */ if (whatToDo.equals("To Solar-")) {
            /* 1611 */ int j = month - 1;
            /* 1612 */ for (int i = 1; i <= 12; ++i) {
                /* 1613 */ if (KK[((year - 1881) * 13 + i - 1)] > 2)/* 1614 */ ++j;
                /*      */ }
            /* 1616 */ if (((year == 1966) && (month == 1) && (day == 30)) ||
            /* 1617 */ ((year == 1990) && (month == 9) && (day == 30)) ||
            /* 1618 */ ((year == 2036) && (month == 11) && (day == 30)) ||
            /* 1620 */ ((year == 2004) && (month == 9) && (day == 30)) ||
            /* 1621 */ ((year == 2005) && (month == 12) && (day == 30)) ||
            /* 1622 */ ((year == 2006) && (month == 2) && (day == 30)) ||
            /* 1623 */ ((year == 2015) && (month == 12) && (day == 30)) ||
            /* 1624 */ ((year == 2017) && (month == 1) && (day == 30)) || (
            /* 1625 */ (year == 2017) && (month == 3) && (day == 30)))
            /*      */ {
                /* 1627 */ return false;
                /*      */ }
            /* 1629 */ if (day > KK[((year - 1881) * 13 + j)] + 28)
            /*      */ {
                /* 1631 */ String beforeLunarDate = "";
                /* 1632 */ String afterLunarDate = "";
                /*      */
                /* 1634 */ if (month < 10)/* 1635 */ beforeLunarDate = year + ".0" + month + "." + (day - 1);
                /*      */ else {
                    /* 1637 */ beforeLunarDate = year + "." + month + "." + (day - 1);
                    /*      */ }
                /*      */
                /* 1640 */ if (month < 12) {
                    /* 1641 */ if (month < 9)/* 1642 */ afterLunarDate = year + ".0" + (month + 1) + "." + "01";
                    /*      */ else/* 1644 */ afterLunarDate = year + "." + (month + 1) + "." + "01";
                    /*      */ }
                /*      */ else {
                    /* 1647 */ afterLunarDate = (year + 1) + "." + "01" + "." + "01";
                    /*      */ }
                /*      */
                /* 1650 */ String beforeSolarDate = toSolarDate(beforeLunarDate);
                /* 1651 */ String afterSolarDate = toSolarDate(afterLunarDate);
                /*      */
                /* 1654 */ return (getDaysBetween(beforeSolarDate, afterSolarDate) == 2);
                /*      */ }
            /*      */
            /*      */ }
        /*      */
        /* 1660 */ return true;
        /*      */ }

    /*      */
    /*      */ public static boolean isValid(String string)
    /*      */ {
        /* 1670 */ return isValid(string, "yyyyMMdd", Locale.getDefault());
        /*      */ }

    /*      */
    /*      */ public static boolean isValid(String string, String format, Locale locale)
    /*      */ {
        /* 1685 */ SimpleDateFormat formatter = new SimpleDateFormat(format, locale);
        /* 1686 */ Date date = null;
        /*      */ try {
            /* 1688 */ date = formatter.parse(string);
            /*      */ } catch (ParseException e) {
            /* 1690 */ return false;
            /*      */ }
        /*      */
        /* 1693 */ return (formatter.format(date).equals(string));
        /*      */ }

    /*      */
    /*      */ public static int getMonthsBetween(String pFrom, String pTo)
    /*      */ {
        /* 1706 */ return getMonthsBetween(pFrom, pTo, "yyyyMMdd", Locale.getDefault());
        /*      */ }

    /*      */
    /*      */ public static int getMonthsBetween(String pFrom, String pTo, String format, Locale locale)
    /*      */ {
        /* 1719 */ Date fromDate = new Date();
        /* 1720 */ Date toDate = new Date();
        /*      */ try {
            /* 1722 */ fromDate = check(pFrom, format);
            /* 1723 */ toDate = check(pTo, format);
            /*      */ } catch (ParseException e) {
            /* 1725 */ LOGGER.debug(DateUtil.class.getName()
                    + " - getMonthsBetween(String from, String to, String format) ▶ " + e.getMessage());
            /* 1726 */ throw new IllegalArgumentException(e.getMessage());
            /*      */ }
        /*      */
        /* 1730 */ if (fromDate.compareTo(toDate) == 0) return 0;
        /*      */
        /* 1732 */ SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", locale);
        /* 1733 */ SimpleDateFormat monthFormat = new SimpleDateFormat("MM", locale);
        /* 1734 */ SimpleDateFormat dayFormat = new SimpleDateFormat("dd", locale);
        /*      */
        /* 1736 */ int fromYear = Integer.parseInt(yearFormat.format(fromDate));
        /* 1737 */ int toYear = Integer.parseInt(yearFormat.format(toDate));
        /* 1738 */ int fromMonth = Integer.parseInt(monthFormat.format(fromDate));
        /* 1739 */ int toMonth = Integer.parseInt(monthFormat.format(toDate));
        /* 1740 */ int fromDay = Integer.parseInt(dayFormat.format(fromDate));
        /* 1741 */ int toDay = Integer.parseInt(dayFormat.format(toDate));
        /*      */
        /* 1743 */ int result = 0;
        /* 1744 */ result += (toYear - fromYear) * 12;
        /* 1745 */ result += toMonth - fromMonth;
        /*      */
        /* 1748 */ if (toDay - fromDay > 0) {
            /* 1749 */ result += toDate.compareTo(fromDate);
            /*      */ }
        /*      */
        /* 1752 */ return result;
        /*      */ }

    /*      */
    /*      */ private static Date check(String string, String format)/*      */ throws ParseException
    /*      */ {
        /* 1764 */ if (string == null) throw new ParseException("date string to check is null", 0);
        /* 1765 */ if (format == null) throw new ParseException("format string to check date is null", 0);
        /*      */
        /* 1767 */ SimpleDateFormat formatter = new SimpleDateFormat(format, LocaleContextHolder.getLocale());
        /* 1768 */ Date date = null;
        /*      */ try {
            /* 1770 */ date = formatter.parse(string);
            /*      */ } catch (ParseException e) {
            /* 1772 */ throw new ParseException(" wrong date:\"" + string + "\" with format \"" + format + "\"", 0);
            /*      */ }
        /*      */
        /* 1775 */ if (!(formatter.format(date).equals(string)))
            throw new ParseException("Out of bound date:\"" + string + "\" with format \"" + format + "\"", 0);
        /* 1776 */ return date;
        /*      */ }

    /*      */
    /*      */ public static String convertToString(Timestamp dateData)
    /*      */ {
        /* 1788 */ return convertToString(dateData, "yyyy/MM/dd");
        /*      */ }

    /*      */
    /*      */ public static String convertToString(Timestamp dateData, String pattern)
    /*      */ {
        /* 1801 */ return convertToString(dateData, pattern, LocaleContextHolder.getLocale());
        /*      */ }

    /*      */
    /*      */ public static String convertToString(Timestamp dateData, String pattern, Locale locale)
    /*      */ {
        /* 1816 */ if (dateData == null) return null;
        /*      */
        /* 1818 */ SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);
        /*      */
        /* 1820 */ return formatter.format(dateData);
        /*      */ }

    /*      */
    /*      */ public static Timestamp convertToTimestamp(String dateData)
    /*      */ {
        /* 1833 */ if (!(isValid(toYYYYMMDDDate(dateData)))) {
            /* 1834 */ throw new IllegalArgumentException(DateUtil.class.getName()
                    + " - convertToTimestamp(String dateData) ▶ Format of date(" + dateData + ") is not valid.");
            /*      */ }
        /*      */
        /* 1837 */ if (dateData == null) return null;
        /* 1838 */ if (dateData.trim().equals("")) return null;
        /*      */
        /* 1840 */ int dateObjLength = dateData.length();
        /*      */
        /* 1842 */ String yearString = "2002";
        /* 1843 */ String monthString = "01";
        /* 1844 */ String dayString = "01";
        /*      */
        /* 1846 */ if (dateObjLength >= 4) {
            /* 1847 */ yearString = dateData.substring(0, 4);
            /*      */ }
        /* 1849 */ if (dateObjLength >= 6) {
            /* 1850 */ monthString = dateData.substring(4, 6);
            /*      */ }
        /* 1852 */ if (dateObjLength >= 8) {
            /* 1853 */ dayString = dateData.substring(6, 8);
            /*      */ }
        /*      */
        /* 1856 */ int year = Integer.parseInt(yearString);
        /* 1857 */ int month = Integer.parseInt(monthString) - 1;
        /* 1858 */ int day = Integer.parseInt(dayString);
        /*      */
        /* 1860 */ Calendar cal = new GregorianCalendar();
        /* 1861 */ cal.clear();
        /* 1862 */ cal.set(year, month, day);
        /* 1863 */ return new Timestamp(cal.getTime().getTime());
        /*      */ }

    /*      */
    /*      */ public static Timestamp convertToTimestampHMS(String dateData)
    /*      */ {
        /* 1876 */ if (dateData == null) return null;
        /* 1877 */ if (dateData.trim().equals("")) return null;
        /*      */
        /* 1879 */ String yearString = dateData.substring(0, 4);
        /* 1880 */ String monthString = dateData.substring(4, 6);
        /* 1881 */ String dayString = dateData.substring(6, 8);
        /* 1882 */ String hourString = dateData.substring(8, 10);
        /* 1883 */ String minString = dateData.substring(10, 12);
        /* 1884 */ String secString = dateData.substring(12, 14);
        /*      */
        /* 1886 */ int year = Integer.parseInt(yearString);
        /* 1887 */ int month = Integer.parseInt(monthString) - 1;
        /* 1888 */ int day = Integer.parseInt(dayString);
        /* 1889 */ int hour = Integer.parseInt(hourString);
        /* 1890 */ int min = Integer.parseInt(minString);
        /* 1891 */ int sec = Integer.parseInt(secString);
        /*      */
        /* 1893 */ Calendar cal = new GregorianCalendar();
        /* 1894 */ cal.clear();
        /* 1895 */ cal.set(year, month, day, hour, min, sec);
        /*      */
        /* 1897 */ return new Timestamp(cal.getTime().getTime());
        /*      */ }
    

	 /**
	  * 현재 시간 Info
	  * @return Current Date String(yyyy-MM-dd hh:mm)
	  */
	 public static String currentTime(){
		return  currentTime( "yyyy-MM-dd hh:mm" );

	 }
  
	 /**
	  * 현재 시간 Info
	  * @return Current Date String( simpleDateFormat )
	  */
	public static String currentTime(String simpleDateFormat){
		Date vDate = new Date();
		SimpleDateFormat pOutformatter = new SimpleDateFormat( simpleDateFormat );
	
		return pOutformatter.format(vDate);
  }
	
  
  /**
	* 현재 년도 Info
	* @return Current Date String( simpleDateFormat )
	*/
  public static String currentYear(){
	   return  currentTime( "yyyy" );
	   
  }
  
  public static String getDate(String strDate, String strFormat )throws Exception 
  {
 
      return getDate(strDate,strFormat, 0 );

  } 
  
  public static String getDate(Date date, String strFormat )throws Exception 
  {
      SimpleDateFormat df = new SimpleDateFormat(strFormat);
      Calendar cal = df.getCalendar();
      cal.setTime(date);
      
      return df.format( cal.getTime() );
      

  } 
  
  public static String getDate(String strDate, String strFormat , int intGap )throws Exception 
  {
 
			SimpleDateFormat df = new SimpleDateFormat(strFormat);
	        Date date = df.parse(strDate);
	        
	        // 날짜 계산하기 
	        Calendar cal = df.getCalendar();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, intGap);//2015-12-01
	        //cal.add(Calendar.MONTH, intGap);//2015-11-17
	            	        
	        return df.format( cal.getTime() );

  } 
  public static Date getDateFromString( String strDate, String strFormat )throws Exception 
  {
           SimpleDateFormat df = new SimpleDateFormat(strFormat);
           return df.parse(strDate);

  }   
  
  
public static Date getDateFromString( String strDate )throws Exception 
  {
          SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
          //df.format(strDate);
           Date date  = df.parse(strDate);
           
           Calendar sc = Calendar.getInstance(); 
           sc.setTime(date);
           

           return df.parse(strDate);

  }  
  
  
  public static int getYear( Date df )throws Exception 
  {
      Calendar cal = Calendar.getInstance(); //객체 생성 및 현재 일시분초...셋팅
      
      cal.setTime(df);
      return cal.get(Calendar.YEAR);
  }  
  
  
  public static int getMonth( Date df )throws Exception 
  {
      Calendar cal = Calendar.getInstance(); //객체 생성 및 현재 일시분초...셋팅
      
      cal.setTime(df);
      return cal.get(Calendar.MONTH)+1;

  }     
  
  public static List<Map> getYearsFirstDay ( Date start, Date end )throws Exception {
      Calendar sc = Calendar.getInstance(); 
      sc.setTime(start);
      Calendar ec = Calendar.getInstance(); 
      ec.setTime(end);
      
      int sYear  = sc.get(Calendar.YEAR);
      int sMonth = sc.get(Calendar.MONTH)+1;
      
      int eYear  = ec.get(Calendar.YEAR);
      int eMonth = ec.get(Calendar.MONTH)+1;
      
      String sDate = Integer.toString(sYear)+"-"+Integer.toString(sMonth)+"-01";
      String eDate = Integer.toString(eYear)+"-"+Integer.toString(eMonth)+"-01";
      
      return getYears( getDateFromString(sDate) , getDateFromString(eDate) );
      
  }
  
  public static List<Integer> getYearsFirstDayInteger ( Date start, Date end )throws Exception {
      Calendar sc = Calendar.getInstance(); 
      sc.setTime(start);
      Calendar ec = Calendar.getInstance(); 
      ec.setTime(end);
      
      int sYear  = sc.get(Calendar.YEAR);
      int sMonth = sc.get(Calendar.MONTH)+1;
      
      int eYear  = ec.get(Calendar.YEAR);
      int eMonth = ec.get(Calendar.MONTH)+1;
      
      String sDate = Integer.toString(sYear)+"-"+Integer.toString(sMonth)+"-01";
      String eDate = Integer.toString(eYear)+"-"+Integer.toString(eMonth)+"-01";
      
      return getYearsInteger( getDateFromString(sDate) , getDateFromString(eDate) );
      
  }
  
  
  
  public static List<Map> getYears(Date start, Date end)throws Exception {
      ArrayList<Map> result = new ArrayList<Map>();
      Calendar sc = Calendar.getInstance(); 
      sc.setTime(start);
      Calendar ec = Calendar.getInstance(); 
      ec.setTime(end);
      
      while( sc.compareTo( ec ) !=1 ){
        Map temp = new HashMap();
          temp.put("year",sc.get(Calendar.YEAR));
          temp.put("yearTotal"   , 0  );
          result.add(temp);
        
        //시작년도 + 1
        sc.add(Calendar.YEAR, 1);
      }


      return result;
      
  }
  
  
  public static List<Integer> getYearsInteger(Date start, Date end)throws Exception {
      ArrayList<Integer> result = new ArrayList<Integer>();
      Calendar sc = Calendar.getInstance(); 
      sc.setTime(start);
      Calendar ec = Calendar.getInstance(); 
      ec.setTime(end);
      
      while( sc.compareTo( ec ) !=1 ){
          result.add(sc.get(Calendar.YEAR));
        
        //시작년도 + 1
        sc.add(Calendar.YEAR, 1);
      }


      return result;
      
  }
  
  
  
  public static List<Map> getMonthsFirstDay ( Date start, Date end )throws Exception {
      Calendar sc = Calendar.getInstance(); 
      sc.setTime(start);
      Calendar ec = Calendar.getInstance(); 
      ec.setTime(end);
      
      int sYear  = sc.get(Calendar.YEAR);
      int sMonth = sc.get(Calendar.MONTH)+1;
      
      int eYear  = ec.get(Calendar.YEAR);
      int eMonth = ec.get(Calendar.MONTH)+1;
      
      String ssMonth = "";
      String esMonth = "";
      if(sMonth<10){ssMonth = "0"+Integer.toString(sMonth);}
      else { ssMonth = Integer.toString(sMonth);}
      
      if(eMonth<10){esMonth = "0"+Integer.toString(eMonth);}
      else { esMonth = Integer.toString(eMonth);}
      
      String sDate = Integer.toString(sYear)+"-"+ssMonth+"-01";
      String eDate = Integer.toString(eYear)+"-"+esMonth+"-01";
      
      return getMonths( getDateFromString(sDate) , getDateFromString(eDate) );
      
  }
  
  public static Map<Integer,List<Integer>> getMonthsFirstDayMap ( Date start, Date end )throws Exception {
      Calendar sc = Calendar.getInstance(); 
      sc.setTime(start);
      Calendar ec = Calendar.getInstance(); 
      ec.setTime(end);
      
      int sYear  = sc.get(Calendar.YEAR);
      int sMonth = sc.get(Calendar.MONTH)+1;
      
      int eYear  = ec.get(Calendar.YEAR);
      int eMonth = ec.get(Calendar.MONTH)+1;
      
      String ssMonth = "";
      String esMonth = "";
      if(sMonth<10){ssMonth = "0"+Integer.toString(sMonth);}
      else { ssMonth = Integer.toString(sMonth);}
      
      if(eMonth<10){esMonth = "0"+Integer.toString(eMonth);}
      else { esMonth = Integer.toString(eMonth);}
      
      String sDate = Integer.toString(sYear)+"-"+ssMonth+"-01";
      String eDate = Integer.toString(eYear)+"-"+esMonth+"-01";
      
      return getMonthsMap( getDateFromString(sDate) , getDateFromString(eDate) );
      
  }
  
  
  
public static List<Map> getMonths(Date start, Date end)throws Exception {
      ArrayList<Map> result = new ArrayList<Map>();
      Calendar sc = Calendar.getInstance(); 
      sc.setTime(start);
      Calendar ec = Calendar.getInstance(); 
      ec.setTime(end);
      
      while( sc.compareTo(ec) !=1 ){
          Map temp = new HashMap();
          temp.put("year" ,sc.get(Calendar.YEAR));
          temp.put("month",sc.get(Calendar.MONTH)+1);
          
          result.add(temp);
          
          //시작월 + 1
          sc.add(Calendar.MONTH, 1);
      
      }
      return result;
      
  }
  
  
  public static Map<Integer,List<Integer>> getMonthsMap(Date start, Date end)throws Exception {
      Calendar sc = Calendar.getInstance(); 
      sc.setTime(start);
      Calendar ec = Calendar.getInstance(); 
      ec.setTime(end);
      
      int cyear = sc.get(Calendar.YEAR);
      Map<Integer,List<Integer>> resultYear = new HashMap<Integer,List<Integer>>();
      List<Integer> lmonth = new ArrayList();
      
      //최초 월 입력
      //lmonth.add(sc.get(Calendar.MONTH));
      //최초 년도 생성
      resultYear.put(cyear,lmonth);
      
      while( sc.compareTo(ec) !=1 ){
          //resultMap에 해당 년도가 없으면 추가 해준다.
          if(!resultYear.containsKey( sc.get(Calendar.YEAR) ) ){
              resultYear.put(sc.get(Calendar.YEAR), new ArrayList<Integer>());
          }
          //해당 년도 월 list
          List<Integer> list = resultYear.get( sc.get(Calendar.YEAR) );
          
          
          int changeMonth = sc.get(Calendar.MONTH)+1;
          
          list.add(changeMonth);
          
          //시작월 + 1
          sc.add(Calendar.MONTH, 1);
        
      }
      return resultYear;
      
  }
  
  
  public static int getDiffInMonths (Date startDate, Date endDate )throws Exception {
      Calendar sDate = Calendar.getInstance();
      Calendar eDate = Calendar.getInstance();
      sDate.setTime(startDate);
      eDate.setTime(endDate  );
      
      int yeaGap = eDate.get(Calendar.YEAR) - sDate.get(Calendar.YEAR);
      int monthGap =  eDate.get(Calendar.MONTH) - sDate.get(Calendar.MONTH);
      int gap = (yeaGap * 12) + monthGap ;
      
      return gap;
  }
	
  
  public static int getDiffInMonths ( Date startDate, int dayGap )throws Exception {
      Calendar sDate = Calendar.getInstance();
      Calendar eDate = Calendar.getInstance();
      sDate.setTime(startDate);
      eDate.setTime(startDate);
      eDate.add( Calendar.DATE, dayGap );
     
      int yeaGap = eDate.get(Calendar.YEAR) - sDate.get(Calendar.YEAR);
      int monthGap =  eDate.get(Calendar.MONTH) - sDate.get(Calendar.MONTH);
      int gap = (yeaGap * 12) + monthGap ;
      
      return  gap;
  }
  
  

  public static int getDiffInMonths ( String startDate, String endDate )throws Exception {
      return  getDiffInMonths ( startDate, endDate , "yyyy-MM-dd" );
  }
  
  public static int getDiffInMonths (String startDate, String endDate , String strFormat)throws Exception {
     
      SimpleDateFormat sdf = new SimpleDateFormat( strFormat );
      Date sdate  = sdf.parse(startDate);
      Date edate  = sdf.parse(endDate);
      
      return  getDiffInMonths ( sdate, edate );
  }   
  
  
  public static int getDiffInMonths ( String startDate , int gap , String strFormat )throws Exception {
      
      SimpleDateFormat sdf = new SimpleDateFormat( strFormat );
      Date sdate  = sdf.parse(startDate);
      
      return  getDiffInMonths ( sdate, gap );
  } 
  
  
  
  public static int getDiffInDays ( Date startDate, int dayGap )throws Exception {
      Calendar sDate = Calendar.getInstance();
      Calendar eDate = Calendar.getInstance();
      sDate.setTime(startDate);
      eDate.setTime(startDate);
      eDate.add( Calendar.DATE, dayGap );
     
      return  eDate.get(Calendar.DATE) - sDate.get(Calendar.DATE);
  }
  
  
  public static int getDiffInDays ( String startDate, int dayGap, String strFormat )throws Exception {
      SimpleDateFormat sdf = new SimpleDateFormat( strFormat );
      Date sdate  = sdf.parse(startDate);
     
      return  getDiffInDays(sdate, dayGap);
  }
  
  public static int getDiffInDays ( String startDate, int dayGap )throws Exception {
      SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
      Date sdate  = sdf.parse(startDate);
     
      return  getDiffInDays(sdate, dayGap);
  } 
  
  
  
  
  public static String getDiffEndDay ( Date startDate, int dayGap )throws Exception {
      return  getDiffEndDay( startDate, dayGap ,"yyyy-MM-dd" );
      
  }
  
  public static String getDiffEndDay ( Date startDate, int dayGap, String strFormat )throws Exception {
      Calendar eDate = Calendar.getInstance();
      eDate.setTime(startDate);
      eDate.add( Calendar.DATE, dayGap );
     
      String endDay = (new SimpleDateFormat( strFormat )).format(eDate.getTime());
      
      return  endDay;
      
  }
  
  
  public static String getDiffEndDay ( String startDate, int dayGap, String strFormat )throws Exception {
      SimpleDateFormat sdf = new SimpleDateFormat( strFormat );
      Date sdate  = sdf.parse(startDate);
     
      return  getDiffEndDay(sdate, dayGap, strFormat );
  }
  
  public static String getDiffEndDay ( String startDate, int dayGap )throws Exception {
      SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
      Date sdate  = sdf.parse( startDate );
     
      return  getDiffEndDay( sdate, dayGap );
  } 
  /**
   * 검색 날짜 변환
   */


  /**
   * Excel Cell 값 조회
   * @param cell
   * @return
   */
  public static String getExcelCellValue(HSSFCell cell){
      String cellValue = "";

      try{
          cellValue = cell == null ? "" : cell.getStringCellValue().trim();
      }
      catch( IllegalStateException e ){
          cellValue = String.valueOf( ((Double)cell.getNumericCellValue()).intValue() );
      }

      return cellValue;
  }

  /**
   * convert date
   *
   * @param condition
   * @param fromDate
   * @param toDate
   * @param dateCompare
   * @param wherePattern
   * @param paramPattern
   * @throws ParseException
   */
public static void convertDate(String condition, String fromDate, String toDate, String dateCompare,
    StringBuffer wherePattern, StringBuffer paramPattern) {

    if (StrUtil.isNotEmpty(fromDate) || StrUtil.isNotEmpty(toDate)) {
        if ("EQ".equals(dateCompare)) {
            if (StrUtil.isNotEmpty(fromDate)) {

                fromDate = fromDate.substring(0, 10);

                StringUtil.constructWherePattern(wherePattern, paramPattern, condition,
                        GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, "TO_DATE:" + fromDate + " 00:00:00");
                StringUtil.constructWherePattern(wherePattern, paramPattern, condition,
                        GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, "TO_DATE:" + fromDate + " 23:59:59");
            }
        } else if ("LT".equals(dateCompare)) {
            if (StrUtil.isNotEmpty(fromDate)) {
                fromDate = fromDate.substring(0, 10);

                StringUtil.constructWherePattern(wherePattern, paramPattern, condition,
                        GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, "TO_DATE:" + fromDate + " 23:59:59");
            }
        } else if ("ET".equals(dateCompare)) {
            if (StrUtil.isNotEmpty(fromDate)) {
                fromDate = fromDate.substring(0, 10);

                StringUtil.constructWherePattern(wherePattern, paramPattern, condition,
                        GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, "TO_DATE:" + fromDate + " 00:00:00");
            }
        } else if ("BT".equals(dateCompare)) {
            if (StrUtil.isNotEmpty(fromDate)) {
                fromDate = fromDate.substring(0, 10);

                StringUtil.constructWherePattern(wherePattern, paramPattern, condition,
                        GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, "TO_DATE:" + fromDate + " 00:00:00");
            }
            if (StrUtil.isNotEmpty(toDate)) {
                toDate = toDate.substring(0, 10);

                StringUtil.constructWherePattern(wherePattern, paramPattern, condition,
                        GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, "TO_DATE:" + toDate + " 23:59:59");
            }
        }
    }
  }
public static String converDateFormat(String date){
    
	SimpleDateFormat sdf = new SimpleDateFormat( DEFAULT_DATE_FORMAT );
	date = sdf.format(new Date(Long.parseLong(date)));

	return date;
}
public static String converDateFormat(Date date){
    return(converDateFormat(date,DEFAULT_DATE_FORMAT));
}

public static String converDateFormat(Date date, String format){
    if(date == null){
        return "";
    }

    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(date);
}

public static String converDateFormat(String date, String format){
    
    if(!StrUtil.isEmpty(date)){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
        SimpleDateFormat dateFormat2 = new SimpleDateFormat(format);
        try {
            Date parseDate = dateFormat.parse(date);
            date = dateFormat2.format(parseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    return date;
}

/**
 * 날짜 변환(이전/이후일자)
 */
public static String converDate(int addDate, String format){
    Calendar cad = new GregorianCalendar();
    cad.add(Calendar.DATE, addDate);
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    String date = sdf.format(cad.getTime());
    return date;
}

public static Date string2Date( String s ) {
    return string2Date( s, DEFAULT_DATE_FORMAT );
}

public static Date string2Date(String s, String format){
    Date date = null;
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    date = sdf.parse(s, new ParsePosition(0));
    return date;
}

public static String stringToDateFormat( String date)
{
    return stringToDateFormat(date, DEFAULT_DATE_FORMAT);
}

public static String stringToDateFormat( String date, String format )
{
    if(NullUtil.isNone(date)){
       return ""; 
    }
    Date cDate = string2Date( date, DATE_YYYYMMDD_FORMAT );
    SimpleDateFormat sdf = new SimpleDateFormat( format );
    return sdf.format( cDate );
}


public static String getCurrentDateString( )
{
    return  getCurrentDateString( DEFAULT_DATE_FORMAT);
}


public static String getCurrentDateString( String format )
{
    if(NullUtil.isNone(format)){
        format = DEFAULT_DATE_FORMAT;
     }
    
    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat(format );
    
    return  dateFormat.format(date);
}

public static String covertDateString( Object obj, String format )
{
    if(NullUtil.isNull(obj)) return "";
    
    String date = "";
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    date = sdf.format(obj);
    
    return date;
}

public static boolean compareNowTargetDate( Object obj )
{
    if(NullUtil.isNull(obj)) return false;
    int now = Integer.parseInt(getCurrentDateString(DATE_YYYYMMDD_FORMAT));
    int target =  Integer.parseInt(covertDateString(obj, DATE_YYYYMMDD_FORMAT));
    
     if( now > target ) return true;
     else return false;
}


public static String addMonth(String day, int num)

{
    Date date =   string2Date(day, DATE_YYYYMMDD_FORMAT);

    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.MONTH, num);

    return converDateFormat(cal.getTime(), DATE_YYYYMMDD_FORMAT);

}

public static String lastDayOfMonth(String targetMonth)

{
    if(NullUtil.isNull(targetMonth)  || targetMonth.length() !=6){
        return "";
    }
    
    int year = Integer.parseInt(targetMonth.substring(0, 4));
    int month =  Integer.parseInt(targetMonth.substring(4, 6));
    int day = 1;
    Calendar cal = Calendar.getInstance();
    cal.set(year, month-1, day); //월은 -1해줘야 해당월로 인식

    return stringToDateFormat(targetMonth+cal.getActualMaximum(Calendar.DAY_OF_MONTH));

}
    /*      */ }
