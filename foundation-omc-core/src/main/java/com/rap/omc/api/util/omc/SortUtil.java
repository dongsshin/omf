/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : SortUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 5. 22. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.util.omc;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.common.model.OmcCompareResult;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;

/**
 * <pre>
 * Class : SortUtil
 * Description : ObjectRootVO에 대한 List를 Sorting 하는 기능을 제공함
 * </pre>
 *
 * @author hyeyoung.park
 */
public class SortUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SortUtil.class);

    /**
     *
     *
     * @param objList
     * @param uniqueAttrStr : uniquized 대상 속성(Pattern:  attr1:attr2:attr3)
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> uniquized(List<? extends ObjectRootVO> objList,String uniqueAttrStr){
        List<T> rtnObjList = new ArrayList<T>();

        String[] splited   = uniqueAttrStr.split(Pattern.quote(":"));
        int splitedCnt = splited.length;
        String[] valueSave = new String[splitedCnt];
        for(int i = 0; i < splitedCnt; i++) valueSave[i] = "...";

        ArrayList<OmcComparator> comparatorList = new ArrayList<OmcComparator>();
        for(String str : splited)
        {
            comparatorList.add(new OmcComparator(str,false));
        }
        SortUtil.sort(objList, comparatorList);
        StringBuffer attrVaue = new StringBuffer();
        for(ObjectRootVO obj : objList)
        {
            boolean isSame = true;
            for(int i = 0; i < splitedCnt; i++)
            {
                attrVaue.setLength(0);
                String strValue = "";
                if(splited[i].startsWith(OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX)){
                    strValue = (String)obj.getOutDataAttributeValue(splited[i].substring(4));
                }else{
                    strValue = (String)obj.getAttribute(splited[i]);
                }
                attrVaue.append(strValue);
                if(!attrVaue.toString().equals(valueSave[i]))  isSame = false;
                valueSave[i] = attrVaue.toString();
            }
            if(!isSame) rtnObjList.add((T)obj);
        }
        return(rtnObjList);
    }

    /**
     *
     *
     * @param objList
     * @param groupByAttrStr : group by 대상 속성(Pattern:  attr1:attr2:attr3)
     * @param maxAttrStr : max 속성 리스트(Pattern:  attr4:attr5:attr6)
     * @param minAttrStr : min 속성 리스트(Pattern:  attr7)
     * @param sumAttrStr : sum 속성 리스트(Pattern:  attr8:attr9)
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> groupBy(List<? extends ObjectRootVO> objList,
                                             String groupByAttrStr,
                                             String maxAttrStr,
                                             String minAttrStr,
                                             String sumAttrStr){
        List<T> rtnObjList = new ArrayList<T>();
        int omcGroupByCount = 1;
        if(NullUtil.isNone(objList)) return rtnObjList;
        if(objList.size() == 1){
            ObjectRootVO savedObjectVO = new ObjectRootVO();
            savedObjectVO = objList.get(0);
            savedObjectVO.getOutData().put(GlobalConstants.OMC_GROUP_BY_COUNT, 1);
            rtnObjList.add((T)savedObjectVO);
            return rtnObjList;
        }
        if(StrUtil.isEmpty(groupByAttrStr)) throw new FoundationException("Group By Attribute list cannot be empty.");
        String[] splited   = groupByAttrStr.split(Pattern.quote(":"));
        int splitedCnt = splited.length;
        String[] valueSave = new String[splitedCnt];
        String[] maxSplited = null;
        String[] minSplited = null;
        String[] sumSplited = null;

        if(!StrUtil.isEmpty(maxAttrStr)) maxSplited   = maxAttrStr.split(Pattern.quote(":"));
        if(!StrUtil.isEmpty(minAttrStr)) minSplited   = minAttrStr.split(Pattern.quote(":"));
        if(!StrUtil.isEmpty(sumAttrStr)) sumSplited   = sumAttrStr.split(Pattern.quote(":"));

        for(int i = 0; i < splitedCnt; i++) valueSave[i] = "...";
        ArrayList<OmcComparator> comparatorList = new ArrayList<OmcComparator>();
        for(String str : splited)
        {
            comparatorList.add(new OmcComparator(str,false));
        }
        SortUtil.sort(objList, comparatorList);
        StringBuffer attrVaue = new StringBuffer();

        ObjectRootVO savedObjectVO = new ObjectRootVO();
        boolean isFirst = true;
        OmcCompareResult compareRslt = null;
        boolean isSame = true;
        for(ObjectRootVO obj : objList)
        {
            isSame = true;
            for(int i = 0; i < splitedCnt; i++)
            {
                attrVaue.setLength(0);
                if(splited[i].startsWith(OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX)){
                    attrVaue.append((String)obj.getOutDataAttributeValue(splited[i].substring(4)));
                }else{
                    attrVaue.append((String)obj.getAttribute(splited[i]));
                }
                if(!attrVaue.toString().equals(valueSave[i]))  isSame = false;
                valueSave[i] = attrVaue.toString();
            }
            if(isSame){
                omcGroupByCount++;
                if(maxSplited != null && maxSplited.length > 0){
                    for(int i = 0; i < maxSplited.length; i++){
                        compareRslt = getCompareValueResult(savedObjectVO,obj,maxSplited[i]);
                        if(!NullUtil.isNull(compareRslt)){
                            if(compareRslt.getCompareResult() < 0){
                                if(maxSplited[i].startsWith(OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX)){
                                    savedObjectVO.setOutDataAttributeValue(maxSplited[i].substring(4), compareRslt.getValue2());
                                }else{
                                    savedObjectVO.setAttributeValue(maxSplited[i], compareRslt.getValue2());
                                }
                            }
                        }
                    }
                }
                if(minSplited != null && minSplited.length > 0){
                    for(int i = 0; i < minSplited.length; i++){
                        compareRslt = getCompareValueResult(savedObjectVO,obj,minSplited[i]);
                        if(!NullUtil.isNull(compareRslt)){
                            if(compareRslt.getCompareResult() > 0){
                                if(minSplited[i].startsWith(OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX)){
                                    savedObjectVO.setOutDataAttributeValue(minSplited[i].substring(4), compareRslt.getValue2());
                                }else{
                                    savedObjectVO.setAttributeValue(minSplited[i], compareRslt.getValue2());
                                }
                            }
                        }
                    }
                }
                if(sumSplited != null && sumSplited.length > 0){
                    for(int i = 0; i < sumSplited.length; i++){
                        compareRslt = getCompareValueResult(savedObjectVO,obj,sumSplited[i]);
                        if (!NullUtil.isNull(compareRslt) && !NullUtil.isNull(compareRslt.getClassKind())){
                            if (compareRslt.getClassKind() instanceof Integer) {
                                Integer val1 = (Integer)compareRslt.getValue1();
                                if(NullUtil.isNull(val1)) val1 = 0;
                                Integer val2 = (Integer)compareRslt.getValue2();
                                if(NullUtil.isNull(val2)) val2 = 0;
                                Integer sum = val1 + val2;
                                if(sumSplited[i].startsWith(OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX)){
                                    savedObjectVO.setOutDataAttributeValue(sumSplited[i].substring(4), sum);
                                }else{
                                    savedObjectVO.setAttributeValue(sumSplited[i], sum);
                                }
                            } else if (compareRslt.getClassKind() instanceof Long) {
                                Long val1 = (Long)compareRslt.getValue1();
                                if(NullUtil.isNull(val1)) val1 = 0l;
                                Long val2 = (Long)compareRslt.getValue2();
                                if(NullUtil.isNull(val2)) val2 = 0l;
                                Long sum = val1 + val2;
                                if(sumSplited[i].startsWith(OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX)){
                                    savedObjectVO.setOutDataAttributeValue(sumSplited[i].substring(4), sum);
                                }else{
                                    savedObjectVO.setAttributeValue(sumSplited[i], sum);
                                }
                            } else if (compareRslt.getClassKind() instanceof Float) {
                                Float val1 = (Float)compareRslt.getValue1();
                                if(NullUtil.isNull(val1)) val1 = 0f;
                                Float val2 = (Float)compareRslt.getValue2();
                                if(NullUtil.isNull(val2)) val2 = 0f;
                                Float sum = val1 + val2;
                                if(sumSplited[i].startsWith(OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX)){
                                    savedObjectVO.setOutDataAttributeValue(sumSplited[i].substring(4), sum);
                                }else{
                                    savedObjectVO.setAttributeValue(sumSplited[i], sum);
                                }
                            } else if (compareRslt.getClassKind() instanceof Double) {
                                Double val1 = (Double)compareRslt.getValue1();
                                if(NullUtil.isNull(val1)) val1 = 0d;
                                Double val2 = (Double)compareRslt.getValue2();
                                if(NullUtil.isNull(val2)) val2 = 0d;
                                Double sum = val1 + val2;
                                if(sumSplited[i].startsWith(OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX)){
                                    savedObjectVO.setOutDataAttributeValue(sumSplited[i].substring(4), sum);
                                }else{
                                    savedObjectVO.setAttributeValue(sumSplited[i], sum);
                                }
                            } else if (compareRslt.getClassKind() instanceof BigDecimal) {
                                BigDecimal val1 = (BigDecimal)compareRslt.getValue1();
                                if(NullUtil.isNull(val1)) val1 = new BigDecimal(0);
                                BigDecimal val2 = (BigDecimal)compareRslt.getValue2();
                                if(NullUtil.isNull(val2)) val2 = new BigDecimal(0);
                                BigDecimal sum = val1.add(val2);
                                if(sumSplited[i].startsWith(OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX)){
                                    savedObjectVO.setOutDataAttributeValue(sumSplited[i].substring(4), sum);
                                }else{
                                    savedObjectVO.setAttributeValue(sumSplited[i], sum);
                                }
                            } else if (compareRslt.getClassKind() instanceof String) {
                                //stBuf.setLength(0);
                                //stBuf.append(NullUtil.nvl((String)compareRslt.getValue1(),"")).append(OmcSystemConstants.DELIMINATOR_VALUE_FOR_JAVA);
                                //stBuf.append(NullUtil.nvl((String)compareRslt.getValue2(),""));
                                //String str = stBuf.toString();
                                //savedObjectVO.setAttributeValue(sumSplited[i], "");
                                ;
                            }else{
                                throw new FoundationException("Cannot sum for Attribute(" + sumSplited[i] + ")");
                            }
                        }
                    }
                }
            }else{
                if(!isFirst) {
                    savedObjectVO.setOutDataAttributeValue(GlobalConstants.OMC_GROUP_BY_COUNT, omcGroupByCount);
                    rtnObjList.add((T)savedObjectVO);
                    omcGroupByCount = 1;
                }
                savedObjectVO = obj;
            }
            isFirst = false;
        }
        //OmcComUtility.logWrite("----------------------------------------------------------00005", 1, true);
        if(!isFirst) {
            savedObjectVO.getOutData().put(GlobalConstants.OMC_GROUP_BY_COUNT, omcGroupByCount);
            rtnObjList.add((T)savedObjectVO);
        }
        //OmcComUtility.logWrite("----------------------------------------------------------00006", 1, true);
        return(rtnObjList);
    }

    private static OmcCompareResult getCompareValueResult(Object o1, Object o2, String attributeName){
        OmcCompareResult compareResult = new OmcCompareResult();
        String methodString = "";
        boolean isOutData = false;
        if(attributeName.startsWith(OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX)){
            methodString = "getOutDataAttributeValue";
            isOutData = true;
        }else{
            methodString = "get" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
        }
        try {
            Method method1 = null;
            Object value1 = null;
            Method method2 = null;
            Object value2 = null;
            if(isOutData){
                method1 = o1.getClass().getMethod(methodString,String.class);
                value1 = method1.invoke(o1,attributeName.substring(4));
                method2 = o2.getClass().getMethod(methodString,String.class);
                value2 = method2.invoke(o2,attributeName.substring(4));
            }else{
                method1 = o1.getClass().getMethod(methodString);
                value1 = method1.invoke(o1);
                method2 = o2.getClass().getMethod(methodString);
                value2 = method2.invoke(o2);
            }
            Object classKind = null;
            int compareValue = 0;
            if(value1 == null && value2 != null) compareValue = -1;
            if(value1 == null && value2 == null) compareValue = 0;
            if(value1 != null && value2 == null) compareValue = 1;
            if(value1 != null && value2 != null){
                if (value1 instanceof Integer) {
                    classKind = new Integer(0);
                    compareValue = ((Integer)value1).compareTo((Integer)value2);
                } else if (value1 instanceof Long) {
                    classKind = new Long(0);
                    compareValue = ((Long)value1).compareTo((Long)value2);
                } else if (value1 instanceof Date) {
                    classKind = new Date();
                    compareValue = ((Date)value1).compareTo((Date)value2);
                } else if (value1 instanceof Float) {
                    classKind = new Float(0);
                    compareValue = ((Float)value1).compareTo((Float)value2);
                } else if (value1 instanceof BigDecimal) {
                    classKind = new BigDecimal(0);
                    compareValue = ((BigDecimal)value1).compareTo((BigDecimal)value2);
                } else if (value1 instanceof Boolean) {
                    classKind = new Boolean(false);
                    compareValue = ((Boolean)value1).compareTo((Boolean)value2);
                }else {
                    classKind = new String("");
                    compareValue = ((String)value1).compareToIgnoreCase((String)value2);
                }
            }
            compareResult = new OmcCompareResult(classKind,value1,value2,compareValue);
        } catch (Exception e) {
            throw new FoundationException("Cannot compare Attribute(" + attributeName + "), ClassName: " + ((ObjectRootVO)o1).getClassName());
        }
        return compareResult;
    }

    /**
     * List Sort (단일 조건)
     *
     * @param voList Sorting 대상 VO List
     * @param comparator Sorting 기준이 되는 VO의 attribute 명
     * @param reverse 순서 (true-내림차순, false-오름차순)
     */
    public static void sort(List<? extends ObjectRootVO> voList, String comparatorList, boolean reverse){

        String[] splited   = comparatorList.split(Pattern.quote(":"));
        List<OmcComparator> omcComparatorList = new ArrayList<OmcComparator>();
        for(int i = 0 ; i < splited.length; i++ ){
            omcComparatorList.add(new OmcComparator(splited[i],reverse));
        }
        sort(voList, omcComparatorList);
    }

    /**
     * List Sort (단일 조건)
     *
     * @param voList Sorting 대상 VO List
     * @param comparator Sorting 기준
     */
    public static void sort(List<? extends ObjectRootVO> voList, OmcComparator comparator){
        Collections.sort(voList,
                new DynamicComparator<ObjectRootVO>(comparator.getAttributeName(), comparator.isReverse()));
    }

    /**
     * List Sort (다중 조건)
     *
     * @param voList Sorting 대상 VO List
     * @param comparatorList Sorting 기준 List
     */
    @SuppressWarnings("unchecked")
    public static void sort(List<? extends ObjectRootVO> voList, List<OmcComparator> comparatorList){
        if (!NullUtil.isNone(comparatorList)) {
            ComparatorChain chain = new ComparatorChain();
            for (OmcComparator comparator : comparatorList) {
                chain.addComparator(new DynamicComparator<ObjectRootVO>(comparator.getAttributeName()), comparator.isReverse());
            }
            Collections.sort(voList, chain);
        }
    }

    public static class OmcComparator {

        // VO의 attribute명
        private String attributeName;

        // false = forward sort order; true = reverse sort order
        private boolean reverse;
        
        private boolean isOutData = false;
        
        /**
         * @param attributeName
         * @param reverse
         */
        public OmcComparator(String attributeName) {
            this.attributeName = attributeName;
            if(attributeName.startsWith(OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX)) isOutData = true;
            this.reverse = false;
        }

        /**
         * @param attributeName
         * @param reverse
         */
        public OmcComparator(String attributeName, boolean reverse) {
            if(attributeName.startsWith(OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX)) isOutData = true;
            this.attributeName = attributeName;
            this.reverse = reverse;
        }

        /**
         *
         *
         * @return the attributeName
         */
        public String getAttributeName(){
            return attributeName;
        }

        /**
         *
         *
         * @param attributeName the attributeName to set
         */
        public void setAttributeName(String attributeName){
            this.attributeName = attributeName;
        }

        /**
         *
         *
         * @return the reverse
         */
        public boolean isReverse(){
            return reverse;
        }

        /**
         *
         *
         * @param reverse the reverse to set
         */
        public void setReverse(boolean reverse){
            this.reverse = reverse;
        }

    }

    static class DynamicComparator<T> implements Comparator<T> {

        private String attributeName = null;

        // false = forward sort order; true = reverse sort order
        private boolean reverse;
        
        private boolean isOutData = false;

        public DynamicComparator(String attributeName) {
            this.attributeName = attributeName;
            this.reverse = false;
            if(attributeName.startsWith(OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX)) isOutData = true;
        }

        public DynamicComparator(String attributeName, boolean reverse) {
            super();
            this.attributeName = attributeName;
            this.reverse = reverse;
            if(attributeName.startsWith(OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX)) isOutData = true;
        }

        @Override
        public int compare(Object o1, Object o2){
            String methodString = "get" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
            if(this.isOutData) methodString = "getOutDataAttributeValue";
            try {
                Method method1 = null;
                Object value1 = null;
                Method method2 = null;
                Object value2 = null;
                if(this.isOutData){
                    method1 = o1.getClass().getMethod(methodString,String.class);
                    value1 = method1.invoke(o1,this.attributeName.substring(4));
                    method2 = o2.getClass().getMethod(methodString,String.class);
                    value2 = method2.invoke(o2,this.attributeName.substring(4));
                }else{
                    method1 = o1.getClass().getMethod(methodString);
                    value1 = method1.invoke(o1);
                    method2 = o2.getClass().getMethod(methodString);
                    value2 = method2.invoke(o2);
                }
                if (reverse) {
                    Object temp = null;
                    temp   = value1;
                    value1 = value2;
                    value2 = temp;
                }
                if(value1 == null && value2 != null) return -1;
                if(value1 == null && value2 == null) return 0;
                if(value1 != null && value2 == null) return 1;
                if (value1 instanceof Integer) {
                    return ((Integer)value1).compareTo((Integer)value2);
                } else if (value1 instanceof Long) {
                    return ((Long)value1).compareTo((Long)value2);
                } else if (value1 instanceof Date) {
                    return ((Date)value1).compareTo((Date)value2);
                } else if (value1 instanceof Float) {
                    return ((Float)value1).compareTo((Float)value2);
                }else {
                    return ((String)value1).compareToIgnoreCase((String)value2);
                }

            } catch (Exception e) {
                LOGGER.warn("OMC Compare Error Occur({}).", e.getMessage());
            }
            return 0;
        }
    }
}
