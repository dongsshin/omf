/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : EtcUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 2. 11. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.util;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.schema.util.OmcSystemConstants;
//import devonframe.configuration.ConfigService;
//import devonframe.security.crypto.CryptoManager;
//import devonframe.util.DateUtil;
import com.rap.omc.util.NullUtil;

/**
 * <pre>
 * Class : EtcUtil
 * Description : TODO
 * </pre>
 *
 * @author hyeyoung.park
 */
public class EtcUtil {

    /**
     * 1이면 true, 0이면 false를 리턴한다.
     *
     * @param value
     * @return
     */
    public static boolean convertIntToBoolean(int value){
        if (value == 1) { return true; }
        return false;
    }

    /**
     * boolean 데이터 형의 값이 true이면 "TRUE", false이면 "FALSE" 문자열을 리턴한다.
     *
     * @param value
     * @return
     */
    public static String convertBooleanToString(boolean value){
        if (value) { return "TRUE"; }
        return "FALSE";
    }

    /**
     * String 데이터 형의 값이 true이면 true, false이면 false boolean을 리턴한다.
     *
     * @param value
     * @return
     */
    public static boolean convertStringToBoolean(String value){
        if ("TRUE".equals(value.toUpperCase())) { return true; }
        return false;
    }
    
    /**
     * String 데이터가 null이면 빈문자열("")을 리턴한다.
     *
     * @param value
     * @return
     */
    public static String convertNullToString(String value){
        if (NullUtil.isNull(value)) { return ""; }
        return value;
    }

    /**
     * String 데이터에 '*'이 포함되어 있으면, '%'로 변환하여 리턴한다.
     *
     * @param value
     * @return
     */
    public static String convertStringToSqlLike(String value){
        if (!NullUtil.isNull(value)) {
            value = value.replace("*", "%");
        }
        return value;
    }

    /**
     * Date 타입을 Timestamp 타입으로 변환한다.
     *
     * @param date
     * @return
     */
//    public static Timestamp convertDateToTimestamp(Date date){
//        SimpleDateFormat dateFormat = new SimpleDateFormat(OmcSystemConstants.OMC_API_MYBATIS_DATE_FORMAT);
//        String strDate = dateFormat.format(date);
//        Timestamp timestamp = DateUtil.convertToTimestampHMS(strDate);
//        return timestamp;
//    }
    public static String getParamter(){
        return(OmcSystemConstants.PARAMETER_PREFIX + randomString(OmcSystemConstants.PARAMETER_LENGTH) + OmcSystemConstants.PARAMETER_SUFFIX);
    }
    public static boolean isParameter(String parm){
        if(parm.length() != OmcSystemConstants.PARAMETER_TOTAL_LENGTH) return false;
        if(!parm.startsWith(OmcSystemConstants.PARAMETER_PREFIX)) return false;
        if(!parm.endsWith(OmcSystemConstants.PARAMETER_SUFFIX)) return false;
        char[] parms = parm.toCharArray();
        boolean exists = false;
        for(int i = 0; i < parms.length; i++){
            exists = false;
            for(int j = 0; j < OmcSystemConstants.PARAMETER_CHARS.length; j ++){
                if(parms[i] == OmcSystemConstants.PARAMETER_CHARS[j]) {exists = true; break;}
            }
            if(!exists) return false;
        }
        return true;
    }
    /**
     * 
     *
     * @param wherePatttern
     * @param parameter
     * @return
     */
    public static String getKeyValueForWhereSelect(final String patternSelect, final String wherePatttern, final String parameter){
        String key = "";
        if(!StrUtil.isEmpty(patternSelect)) key = patternSelect.trim();
        if(!StrUtil.isEmpty(key)) key = key + OmcFoundationConstant.OQL_KEY_VALUE_Seperator;
        if(!StrUtil.isEmpty(wherePatttern)) key = key + wherePatttern.trim();
        if(!StrUtil.isEmpty(parameter) && !parameter.equals(GlobalConstants.FLAG_TYPE_ALL)){
            String[] splitedParmNew  = parameter.split(Pattern.quote(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME));
            for(int i = 0; i < splitedParmNew.length; i++){
                String[] parm = splitedParmNew[i].split(Pattern.quote(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_VALUE));
                key = key.replace(parm[0], OmcSystemConstants.PARAMETER_SPECIAL_STR);
            }
        }
        return key;
    }
    
    /**
     * 원하는 길이이 랜덤 문자열을 생성하여 리턴한다.
     *
     * @param length
     * @return
     */
    public static String randomString(int length){
        char[] chars = OmcSystemConstants.PARAMETER_CHARS;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * VO의 names를 리턴한다.
     *
     * @param vo
     * @return
     */
    public static String getNames(ObjectRootVO vo){
        String targetNames = null;
        if (vo instanceof BusinessRelationObjectVO) {
            targetNames = "-";
        } else {
            BusinessObjectRootVO bVo = (BusinessObjectRootVO)vo;
            targetNames = bVo.getNames();
        }
        return targetNames;
    }

    public static <T> String convertGenericTypeToString(T object){
        if (NullUtil.isNull(object)) {
            return "";
        }

        String returnValue = null;
        if (object instanceof ObjectRootVO) {
            ObjectRootVO vo = (ObjectRootVO)object;
            returnValue = vo.getObid();
        } else if (object instanceof ObjectRoot) {
            ObjectRoot dom = (ObjectRoot)object;
            returnValue = dom.getVo().getObid();
        } else {
            returnValue = object.toString();
        }
        return returnValue;
    }

    public static <T> String convertListToString(List<T> objectList){
        if (NullUtil.isNone(objectList)) {
            return "";
        }

        StringBuffer buf = new StringBuffer();
        for (T object : objectList) {
            if (object instanceof ObjectRootVO) {
                ObjectRootVO vo = (ObjectRootVO)object;
                buf.append("|").append(vo.getObid());
            } else if (object instanceof ObjectRoot) {
                ObjectRoot dom = (ObjectRoot)object;
                buf.append("|").append(dom.getVo().getObid());
            } else {
                buf.append("|").append(object.toString());
            }
        }
        return buf.toString().substring(1);
    }
}
