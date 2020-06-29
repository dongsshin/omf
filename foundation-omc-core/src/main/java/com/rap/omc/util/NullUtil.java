package com.rap.omc.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NullUtil {
	public static boolean isNull(Object value){
	    return (value == null);
	    }
	    public static boolean isNull(BigDecimal value){
	    return (value == null);
	    }
	    public static boolean isNone(String value)
	    {
	        return ((value == null) || (value.length() == 0));
	    }
	    public static boolean notNone(String value)
	    {
	        return ((value != null) && (value.length() > 0));
	    }
	    public static boolean isNone(@SuppressWarnings("rawtypes") List value)
	    {
	        return ((value == null) || (value.size() == 0));
	    }
	    public static boolean isNone(@SuppressWarnings("rawtypes") Set value)
	    {
	        return ((value == null) || (value.size() == 0));
	    }
	    public static boolean isNone(Object[] value)
	    {
	        return ((value == null) || (value.length == 0));
	    }
	    public static boolean isNone(@SuppressWarnings("rawtypes") Map value)
	    {
	        return ((value == null) || (value.size() == 0));
	    }
	    public static String nvl(String originalStr, String defaultStr)
	    {
	        if ((originalStr == null) || (originalStr.length() < 1)) return defaultStr;
	        return originalStr;
	    }
	    public static String nvl(Object object, String defaultValue)
	    {
	        if (object == null) return defaultValue;
	        return nvl(object.toString(), defaultValue);
	    }
	    public static String print(Object o)
	    {
	        if (o == null) return "";
	        return o.toString();
	    }
//    public static boolean isNull(Object value)
//    {
//        return (value == null);
//    }
//    public static boolean isNone(String value)
//    {
//        return ((value == null) || (value.length() == 0));
//    }
//    public static boolean notNone(String value)
//    {
//        return ((value != null) && (value.length() > 0));
//    }
//    @SuppressWarnings("rawtypes")
//	public static boolean isNone(List value)
//    {
//    	return ((value == null) || (value.size() == 0));
//    }
//    public static boolean isNone(Object[] value)
//    {
//    	return ((value == null) || (value.length == 0));
//    }
//    @SuppressWarnings("rawtypes")
//	public static boolean isNone(Map value)
//    {
//    	return ((value == null) || (value.size() == 0));
//    }
//    public static String nvl(String originalStr, String defaultStr)
//    {
//    	if ((originalStr == null) || (originalStr.length() < 1)) return defaultStr;
//    	return originalStr;
//    }
//    public static String nvl(Object object, String defaultValue)
//    {
//    	if (object == null) return defaultValue;
//    	return nvl(object.toString(), defaultValue);
//    }
//    public static String print(Object o)
//    {
//    	if (o == null) return "";
//    	return o.toString();
//    }
}