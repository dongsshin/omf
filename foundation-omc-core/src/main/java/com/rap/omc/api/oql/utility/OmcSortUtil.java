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
package com.rap.omc.api.oql.utility;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.util.NullUtil;
import com.rap.omc.api.oql.model.schema.OmcOQLRoot;

/**
 * <pre>
 * Class : SortUtil
 * Description : ObjectRootVO에 대한 List를 Sorting 하는 기능을 제공함
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class OmcSortUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(OmcSortUtil.class);

    /**
     * List Sort (단일 조건)
     *
     * @param voList Sorting 대상 VO List
     * @param comparator Sorting 기준이 되는 VO의 attribute 명
     * @param reverse 순서 (true-내림차순, false-오름차순)
     */
    public static void sort(List<? extends OmcOQLRoot> voList, String comparator, boolean reverse){
        Collections.sort(voList, new DynamicComparator<OmcOQLRoot>(comparator, reverse));
    }

    /**
     * List Sort (단일 조건)
     *
     * @param voList Sorting 대상 VO List
     * @param comparator Sorting 기준
     */
    public static void sort(List<? extends OmcOQLRoot> voList, OmcOQLComparator comparator){
        Collections.sort(voList,
                new DynamicComparator<OmcOQLRoot>(comparator.getAttributeName(), comparator.isReverse()));
    }

    /**
     * List Sort (다중 조건)
     *
     * @param voList Sorting 대상 VO List
     * @param comparatorList Sorting 기준 List
     */
    @SuppressWarnings("unchecked")
    public static void sort(List<? extends OmcOQLRoot> voList, List<OmcOQLComparator> comparatorList){
        if (!NullUtil.isNone(comparatorList)) {
            ComparatorChain chain = new ComparatorChain();
            for (OmcOQLComparator comparator : comparatorList) {
                chain.addComparator(new DynamicComparator<OmcOQLRoot>(comparator.getAttributeName()),comparator.isReverse());
            }
            Collections.sort(voList, chain);
        }
    }
    
    public static List<OmcOQLRoot> uniquized(List<? extends OmcOQLRoot> objList, String uniqueAttrStr, int processLevel,boolean testMode){

        List<OmcOQLRoot> rtnObjList = new ArrayList<OmcOQLRoot>();
        String[] splited   = uniqueAttrStr.split(Pattern.quote(":"));
        int splitedCnt = splited.length;
        String[] valueSave = new String[splitedCnt];
        for(int i = 0; i < splitedCnt; i++) valueSave[i] = "...";
        
        OmcComUtility.logWrite("----------------------------------------------------------00001", 1, testMode);
        
        ArrayList<OmcOQLComparator> comparatorList = new ArrayList<OmcOQLComparator>();
        for(String str : splited)
        {
            comparatorList.add(new OmcOQLComparator(str,false));
        }
        OmcComUtility.logWrite("----------------------------------------------------------00002", 1, testMode);
        OmcSortUtil.sort(objList, comparatorList);
        OmcComUtility.logWrite("----------------------------------------------------------00003", 1, testMode);
        StringBuffer attrVaue = new StringBuffer();
        
        for(OmcOQLRoot obj : objList)
        {
            boolean isSame = true;
            for(int i = 0; i < splitedCnt; i++)
            {
                attrVaue.setLength(0);
                //String tempStr = (String)obj.getAttribute(splited[i]);
                attrVaue.append((String)obj.getAttribute(splited[i]));
                if(!attrVaue.toString().equals(valueSave[i]))  isSame = false;
                valueSave[i] = attrVaue.toString();
            }
            if(!isSame) rtnObjList.add(obj);
        }
        OmcComUtility.logWrite("----------------------------------------------------------00004", 1, testMode);
        return(rtnObjList);
    }
    public static void sort(List<? extends OmcOQLRoot> objList, String uniqueAttrStr, int processLevel,boolean testMode){
        sort(objList,uniqueAttrStr,false,processLevel+1,testMode);
    }
    public static void sort(List<? extends OmcOQLRoot> objList, String uniqueAttrStr, boolean reverse, int processLevel,boolean testMode){

        String[] splited   = uniqueAttrStr.split(Pattern.quote(":"));
        
        ArrayList<OmcOQLComparator> comparatorList = new ArrayList<OmcOQLComparator>();
        for(String str : splited)
        {
            comparatorList.add(new OmcOQLComparator(str,reverse));
        }
        sort(objList, comparatorList);
    }
    
    public static class OmcOQLComparator {

        // VO의 attribute명
        private String attributeName;

        // false = forward sort order; true = reverse sort order
        private boolean reverse;

        /**
         * @param attributeName
         * @param reverse
         */
        public OmcOQLComparator(String attributeName) {
            this.attributeName = attributeName;
            this.reverse = false;
        }

        /**
         * @param attributeName
         * @param reverse
         */
        public OmcOQLComparator(String attributeName, boolean reverse) {
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

        public DynamicComparator(String attributeName) {
            this.attributeName = attributeName;
            this.reverse = false;
        }

        public DynamicComparator(String attributeName, boolean reverse) {
            super();
            this.attributeName = attributeName;
            this.reverse = reverse;
        }

        @Override
        public int compare(Object o1, Object o2){
            String methodString = "get" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
            try {
                Method method1 = o1.getClass().getMethod(methodString);
                Object value1 = method1.invoke(o1);

                Method method2 = o2.getClass().getMethod(methodString);
                Object value2 = method2.invoke(o2);

                if (reverse) {
                    Object temp = null;
                    temp = value1;
                    value1 = value2;
                    value2 = temp;
                }

                if (value1 instanceof Integer) {
                    return ((Integer)value1).compareTo((Integer)value2);
                } else if (value1 instanceof Long) {
                    return ((Long)value1).compareTo((Long)value2);
                } else if (value1 instanceof Date) {
                    return ((Date)value1).compareTo((Date)value2);
                } else {
                    return ((String)value1).compareToIgnoreCase((String)value2);
                }

            } catch (Exception e) {
                LOGGER.warn("OMC Compare Error Occur({}).", e.getMessage());
            }
            return 0;
        }
    }
}
