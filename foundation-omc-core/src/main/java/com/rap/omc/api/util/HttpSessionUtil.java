/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : SessionUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 10. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.util;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * <pre>
 * Class : SessionUtil
 * Description : HttpSession 객체의 속성값에 대한 설정/조회를 수행하고, 필요시 invalidate시킨다.
 *               HttpSession 객체를 일관된 인터페이스를 통해 다루기 위해 사용함
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class HttpSessionUtil {

    /**
     * HttpSession 객체로부터 요청된 속성값을 추출한다.
     * 
     * @param session HttpSession 객체
     * @param attributeName 요청할 속성명
     * @return HttpSession으로부터 추출된 속성값(HttpSession이 null이거나 속성값이 null이면, null)
     */
    public static Object getAttribute(HttpSession session, String attributeName){
        return getAttributePrivate(session, attributeName);
    }

    private static Object getAttributePrivate(HttpSession session, String attributeName){
        Object result = null;
        if (session != null && attributeName != null) {
            result = session.getAttribute(attributeName);
        }
        return result;
    }

    /**
     * HttpSession 객체에 요청된 속성값을 추가한다.<br />
     * - HttpSession이 null이거나 속성명이 null이면, 아무런 작업도 하지 않는다.
     * 
     * @param session HttpSession 객체
     * @param attributeName 추가할 속성명
     * @param value 추가할 속성값
     */
    public static void setAttribute(HttpSession session, String attributeName, Object value){
        setAttributePrivate(session, attributeName, value);
    }

    private static void setAttributePrivate(HttpSession session, String attributeName, Object value){
        if (session != null && attributeName != null) {
            session.setAttribute(attributeName, value);
        }
    }

    /**
     * HttpSession 객체에서 요청된 속성값을 제거한다.<br />
     * - HttpSession이 null이거나 속성명이 null이면, 아무런 작업도 하지 않는다.
     * 
     * @param session HttpSession 객체
     * @param attributeName 제거할 속성명
     */
    public static void removeAttribute(HttpSession session, String attributeName){
        removeAttributePrivate(session, attributeName);
    }

    private static void removeAttributePrivate(HttpSession session, String attributeName){
        if (session != null && attributeName != null) {
            session.removeAttribute(attributeName);
        }
    }

    /**
     * HttpSession 객체를 invalidate한다.<br />
     * - HttpSession이 null이면, 아무런 작업도 하지 않는다.
     * 
     * @param session HttpSession 객체
     */
    public static void invalidate(HttpSession session){
        invalidatePrivate(session);
    }

    private static void invalidatePrivate(HttpSession session){
        if (session != null) {
            session.invalidate();
        }
    }

    /**
     * HttpSession 객체에 담긴 속성명들을 Enumeration 타입으로 반환한다.<br />
     * - HttpSession이 null이면, null을 반환한다.
     * 
     * @param session HttpSession 객체
     * @return HttpSession 객체에 담긴 속성명들의 Enumeration 타입
     */
    @SuppressWarnings("rawtypes")
    public static Enumeration getAttributeNames(HttpSession session){
        return getAttributeNamesPrivate(session);
    }

    @SuppressWarnings("rawtypes")
    private static Enumeration getAttributeNamesPrivate(HttpSession session){
        Enumeration result = null;
        if (session != null) {
            result = session.getAttributeNames();
        }
        return result;
    }

    /**
     * HttpSession 객체에 담긴 생성시간을 반환한다.(the number of milliseconds since midnight January 1, 1970 GMT)<br />
     * - HttpSession이 null이면, 0을 반환한다.
     * 
     * @param session HttpSession 객체
     * @return HttpSession 객체에 담긴 생성시간(the number of milliseconds since midnight January 1, 1970 GMT)
     */
    public static long getCreationTime(HttpSession session){
        return getCreationTimePrivate(session);
    }

    private static long getCreationTimePrivate(HttpSession session){
        long result = 0L;
        if (session != null) {
            result = session.getCreationTime();
        }
        return result;
    }

    /**
     * HttpSession 객체에 담긴 세션ID를 반환한다.<br />
     * - HttpSession이 null이면, null을 반환한다.
     * 
     * @param session HttpSession 객체
     * @return HttpSession 객체에 담긴 세션ID
     */
    public static String getId(HttpSession session){
        return getIdPrivate(session);
    }

    private static String getIdPrivate(HttpSession session){
        String result = null;
        if (session != null) {
            result = session.getId();
        }
        return result;
    }

    /**
     * HttpSession 객체에 담긴 최종접근시간을 반환한다.(the number of milliseconds since midnight January 1, 1970 GMT)<br />
     * - HttpSession이 null이면, 0을 반환한다.
     * 
     * @param session HttpSession 객체
     * @return HttpSession 객체에 담긴 최종접근시간(the number of milliseconds since midnight January 1, 1970 GMT)
     */
    public static long getLastAccessedTime(HttpSession session){
        return getLastAccessedTimePrivate(session);
    }

    private static long getLastAccessedTimePrivate(HttpSession session){
        long result = 0L;
        if (session != null) {
            result = session.getLastAccessedTime();
        }
        return result;
    }

    /**
     * HttpSession 객체에 담긴 세션무효화시간(초 단위)을 반환한다.<br />
     * - HttpSession이 null이면, 0을 반환한다.
     * 
     * @param session HttpSession 객체
     * @return 세션무효화시간(초 단위)
     */
    public static int getMaxInactiveInterval(HttpSession session){
        return getMaxInactiveIntervalPrivate(session);
    }

    private static int getMaxInactiveIntervalPrivate(HttpSession session){
        int result = 0;
        if (session != null) {
            result = session.getMaxInactiveInterval();
        }
        return result;
    }

    /**
     * HttpSession 객체에 세션무효화시간(초 단위)을 설정한다.<br />
     * - HttpSession이 null이면, 아무런 작업도 하지 않는다.
     * 
     * @param session HttpSession 객체
     * @param interval 설정할 세션무효화시간(초 단위)
     */
    public static void setMaxInactiveInterval(HttpSession session, int interval){
        setMaxInactiveIntervalPrivate(session, interval);
    }

    private static void setMaxInactiveIntervalPrivate(HttpSession session, int interval){
        if (session != null) {
            session.setMaxInactiveInterval(interval);
        }
    }

    /**
     * HttpSession 객체로부터 ServletContext를 반환한다.<br />
     * - HttpSession이 null이면, null을 반환한다.
     * 
     * @param session HttpSession 객체
     * @return ServletContext 객체
     */
    public static ServletContext getServletContext(HttpSession session){
        return getServletContextPrivate(session);
    }

    private static ServletContext getServletContextPrivate(HttpSession session){
        ServletContext result = null;
        if (session != null) {
            result = session.getServletContext();
        }
        return result;
    }

}
