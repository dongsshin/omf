/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : LogKeyUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 10. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.framework.log;

/**
 * <pre>
 * Class : LogKeyUtil
 * Description : HTTP Request에 하나의 key를 할당하여, 남겨지는 로그를 request 별로 구분하여 볼 수 있도록 한다.
 * </pre>
 *
 * @author hyeyoung.park
 */
public class LogKeyUtil {

    // log key
    private static int logKey = 0;

    /**
     * thread Local 변수
     */
    private static ThreadLocal<String> threadLogKey = new ThreadLocal<String>();

    /**
     * thread에 세팅된 logKey를 가져온다.
     */
    public static String getLogKey(){
        String key = threadLogKey.get();
        if (key == null) {
            key = "GPLM ";
        }

        return key;
    }

    /**
     * logKey를 thread변수로 지정한다.
     */
    public static void setLogKey(final String key){
        threadLogKey.set(key);
    }

    /**
     * logKey를 생성하여 thread변수로 지정한다.
     */
    public static void startLogKey(){
        setLogKey("GPLM" + "-" + makeLogKey());
    }

    /**
     * logKey를 초기화한다.
     */
    public static void clearLogKey(){
        threadLogKey.set(null);
    }

    /**
     * log key를 증가하여 return 한다.
     */
    public synchronized static int makeLogKey(){
        if (logKey < Integer.MAX_VALUE) {
            logKey++;
        } else {
            logKey = 0;
        }
        return logKey;
    }

    public static String getLogKeyWithStart(){
        startLogKey();
        String key = getLogKey();
        return key;
    }
}
