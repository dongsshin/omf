/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : Bit.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 2.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.util;

import java.util.Arrays;

import com.rap.omc.framework.exception.FoundationException;
/**
 * <pre>
 * Class : Bit
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class Bit {
    public static boolean isInclude(int all, int include){
        return(isInclude((long)all,(long)include));
    }
    public static boolean isInclude(double all, double include){
        return(isInclude((long)all,(long)include));
    }
    public static boolean isInclude(long all, long include){
        if(and(all,include) == include) return true;
        return false;
    }
    public static int or(int ...v1){
        int r = v1[0];
        for(int i = 1; i < v1.length ; i++){
            r = r | v1[i];
            if (r==0) break;
        }
        return(r);
    }
    public static double or(double ...v1){
        long r = (long)v1[0];
        for(int i = 1; i < v1.length ; i++){
            r = r | (long)v1[i];
            if (r==0) break;
        }
        return((double)r);
    }
    public static long or(long ...v1){
        long r = v1[0];
        for(int i = 1; i < v1.length ; i++){
            r = r | v1[i];
            if (r==0) break;
        }
        return(r);
    }
    public static int and(int ...v1){
        int r = v1[0];
        for(int i = 1; i < v1.length ; i++){
            r = r & v1[i];
            if (r==0) break;
        }
        return(r);
    }
    public static double and(double ...v1){
        long r = (long)v1[0];
        for(int i = 1; i < v1.length ; i++){
            r = r & (long)v1[i];
            if (r==0) break;
        }
        return((double)r);
    }
    public static long and(long ...v1){
        long r = v1[0];
        for(int i = 1; i < v1.length ; i++){
            r = r & v1[i];
            if (r==0) break;
        }
        return(r);
    }
    public static long h2d(String v1){
        char[] a = (v1.toUpperCase()).toCharArray();
        long   t = 0;
        long   r = 0;
        int    n = a.length;
        for(int i = 0; i < n; i++){
            if(a[i] <= 57){
                r = r + (a[i] - 48)*(long)Math.pow(16, n - (i + 1));
            }else if (a[i] <= 70){
                r = r + (a[i] - 55)*(long)Math.pow(16, n - (i + 1));
            }else{
                throw new FoundationException("omc.util.arithmatic.notsupport");
            }
        }
        return r;
    }
    public static int xor(int v1, int v2){
        return (int)xor((long)v1,(long)v2);
    }
    public static double xor(double v1, double v2){
        return (double)xor((long)v1,(long)v2);
    }
    public static long xor(long v1, long v2){
        return((or(v1,v2) - and(v1,v2)));
    }
    public static String d2h(double v1, int len){
        return(d2h((long)v1,len));
    }
    public static String d2h(int v1, int len){
        return(d2h((long)v1,len));
    }
    public static String d2h(long v1, int len){
        StringBuffer sb = new StringBuffer(Long.toHexString(v1));
        int ilen = sb.length();
        if (sb.length() < len){
            for (int i = (len - ilen); i > 0; i--)
            {
                sb.insert(0, "0");
            }
        }
        return sb.toString();
    }
}