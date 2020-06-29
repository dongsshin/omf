/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : MybatisConvertMapInterceptor.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 15. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.dataaccess.mybatis.interceptor;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.object.model.CastMap;
import com.rap.omc.api.object.model.GroupByResultVO;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.EtcUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.dataaccess.paging.handler.PagingResultHandler;
import com.rap.omc.foundation.classes.model.ClassInfo;
import com.rap.omc.foundation.classes.model.ColumnInfo;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.foundation.ClassInfoUtil;
import com.rap.omc.util.NullUtil;


/**
 * <pre>
 * Class : MyBatisConvertMapInterceptor
 * Description : resultType이 CastMap으로 지정된 경우, Map을 className에 해당하는 BO/RO VO로 동적으로 변환하는 interceptor
 *               - Interceptor 처리 조건은 resultType이 CastMap으로 정의되고
 *                 map 안에 className 키에 대한 value가 존재해야 함(select 구문에 className이 있어야 함)
 *               - 일반 query, paging query, procedure call(cursor) 각각 result(List)를 꺼내는 부분이 달라 그에 대한 각각의 처리가 추가됨
 * </pre>
 *
 * @author hyeyoung.park
 */

@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }),
             @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,RowBounds.class, ResultHandler.class }) })
public class MyBatisConvertMapInterceptor implements Interceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisConvertMapInterceptor.class);
    @Override
    public Object intercept(Invocation invocation) throws Throwable{
        Object result = null;
        result = invocation.proceed();
        Object[] args = invocation.getArgs();
        if(args.length > 3){
            Object resultHandler = args[3];
            if (resultHandler instanceof PagingResultHandler) {
                result = ((PagingResultHandler)resultHandler).getResultList();
            }
        }
        interceptSub(result);
        return result;
    }
    /**
     * Map으로 넘어온 데이터를 VO생성 대상 대해서는 해당 Class의 VO로 Converting한다. 
     *
     * @param result
     * @throws Throwable
     */
    @SuppressWarnings("unchecked")
    private void interceptSub(Object result) throws Throwable{
        String className      = null;
        String queryType      = null;
        String locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale,OmcSystemConstants.OMC_LOCALE_LANG_DEFAULT);
        if (result instanceof List) {
            List<Object> list = (List<Object>)result;
            if(list.size() == 1){
                Object obj = list.get(0);
                if(obj instanceof CastMap){
                    Map<String,Integer> attributeMap    = null;
                    Map<String,Method>  methodMap       = null;
                    Map<String,String>  columnMap       = new HashMap<String,String>();
                    Map<String,MethodHandle>  methodHandlerMap= new HashMap<String,MethodHandle>();
                    Object              newClsInstance  = null;
                    CastMap<?, ?> map = (CastMap<?, ?>)obj;
                    className = (String)map.get(OmcSystemConstants.OMC_API_MYBATIS_CLASS_NAME_MAP);
                    queryType = (String)map.get(OmcSystemConstants.OMC_API_MYBATIS_QUERY_GROUPBY);
                    if(!NullUtil.isNone(className) && NullUtil.isNone(queryType)){
                        ClassInfo classInfo     = ClassInfoUtil.getClassInfo(className);
                        if(!NullUtil.isNull(classInfo)){
                            Class<?>  cls = Class.forName(classInfo.getJavaPackage() + "." + className + "VO");
                            if(NullUtil.isNull(cls)){
                                LOGGER.warn("Class({}) is not Found", classInfo.getJavaPackage() + "." + className + "VO");
                            }
                            newClsInstance = cls.newInstance();
                            attributeMap   = getAttributeMap(classInfo.getColumnList(),columnMap);
                            methodMap      = getMethods(className, map,newClsInstance,classInfo,attributeMap,columnMap,methodHandlerMap);
                            Object convertResult = convertMapToObject(className, map,newClsInstance,methodMap,methodHandlerMap,attributeMap,columnMap,locale);
                            list.set(0, convertResult);                                
                        }else{
                            LOGGER.warn("Class({}) is not Found", className);
                        }
                    }else{
                        if(OmcSystemConstants.OMC_API_MYBATIS_QUERY_GROUPBY_VALUE.equals(queryType)){
                            list.set(0, convertMapToObject(map));
                        }
                    }
                }
            }else if (list.size() > 1){
                Object obj = list.get(0);
                if(obj instanceof CastMap){
                    Map<String,Object>                    instanceBank  = new HashMap<String,Object>();
                    Map<String,Map<String,MethodHandle>>  handlerBank   = new HashMap<String,Map<String,MethodHandle>>();
                    Map<String,Map<String,Method>>        methodBank    = new HashMap<String,Map<String,Method>>();
                    Map<String,Map<String,Integer>>       attributeBank = new HashMap<String,Map<String,Integer>>();
                    Map<String,Map<String,String>>        columnBank    = new HashMap<String,Map<String,String>>();

                    Map<String,MethodHandle>  methodHandlerMap= new HashMap<String,MethodHandle>();
                    
                    for (int inx = 0; inx < list.size(); inx++) {
                        obj = list.get(inx);
                        if (obj instanceof CastMap) {
                            Map<String,Integer> attributeMap    = null;
                            Map<String,String>  columnMap       = new HashMap<String,String>();
                            Map<String,Method>  methodMap       = null;
                            Object              baseClsInstance = null;
                            Object              newClsInstance  = null;
                            CastMap<?, ?> map = (CastMap<?, ?>)obj;
                            className = (String)map.get(OmcSystemConstants.OMC_API_MYBATIS_CLASS_NAME_MAP);
                            queryType = (String)map.get(OmcSystemConstants.OMC_API_MYBATIS_QUERY_GROUPBY);
                            if(!NullUtil.isNone(className) && NullUtil.isNone(queryType)){
                                Object clsInstance = instanceBank.get(className);
                                if(clsInstance == null){
                                    ClassInfo classInfo = ClassInfoUtil.getClassInfo(className);
                                    Class<?>  cls       = Class.forName(classInfo.getJavaPackage() + "." + className + "VO");
                                    newClsInstance      = cls.newInstance();
                                    
                                    baseClsInstance     = DomUtil.copy(newClsInstance);
                                    
                                    instanceBank.put(className, newClsInstance);
                                    
                                    attributeMap = getAttributeMap(classInfo.getColumnList(),columnMap);
                                    attributeBank.put(className, attributeMap);
                                    columnBank.put(className, columnMap);
                                    
                                    methodMap = getMethods(className, map,baseClsInstance,classInfo,attributeMap,columnMap,methodHandlerMap);
                                    methodBank.put(className, methodMap);
                                    
                                    handlerBank.put(className, methodHandlerMap);
                                }else{
                                    baseClsInstance = DomUtil.copy(clsInstance);
                                    attributeMap    = attributeBank.get(className);
                                    methodMap       = methodBank.get(className);
                                    methodHandlerMap      = handlerBank.get(className);
                                    columnMap       = columnBank.get(className);
                                }
                                Object convertResult = convertMapToObject(className, map,baseClsInstance,methodMap,methodHandlerMap,attributeMap,columnMap,locale);
                                list.set(inx, convertResult);
                            }else{
                                if(OmcSystemConstants.OMC_API_MYBATIS_QUERY_GROUPBY_VALUE.equals(queryType)){
                                    list.set(inx, convertMapToObject(map));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * 
     *
     * @param map
     * @param clsInstance
     * @param methodMap
     * @param attributeMap
     * @return
     */
    private Object convertMapToObject(String className, Map<?, ?> map, Object clsInstance, Map<String,Method>  methodMap, Map<String,MethodHandle>  handlerMap, Map<String,Integer> attributeMap, Map<String,String> columnMap, String locale){
        HashMap<String, Object> outData = new HashMap<String, Object>();
        Iterator<?> itr = map.keySet().iterator();
        String orgKey = null;
        String handlerKey = null;
        MethodHandle method = null;
        //Method method = null;
        Integer dataType = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(OmcSystemConstants.OMC_API_MYBATIS_DATE_FORMAT);
        String strDate = null;
        Date date = null;
        String this_displayedClassNameKr = "";
        boolean isKo = false;
        if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)) isKo = true;
        while (itr.hasNext()) {
            orgKey = (String)itr.next();
            handlerKey = className + "." + orgKey;
            Object objValue = map.get(orgKey);
            
            //if(objValue != null){
                try {
                    //method   = methodMap.get(handlerKey);//Method로 수행하는 경우
                    method   = handlerMap.get(handlerKey);//Handler로 수행하는 경우
                    dataType = attributeMap.get(columnMap.get(orgKey));
                    if(method != null && dataType != null){
                        if(objValue == null) {
                            method.invoke(clsInstance, null);
                        }else{
                            if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_STRING || dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_USERID || dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_LONGSTRING){
                                     method.invoke(clsInstance, objValue.toString());                                
                             }else if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_INTEGER){
                                 try {
                                 method.invoke(clsInstance, ((BigDecimal)objValue).intValue());
                                 }catch(Exception e){
                                     method.invoke(clsInstance, ((BigDecimal)objValue).longValue());
                                 }
                             }else if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_FLOAT){
                                 method.invoke(clsInstance, ((BigDecimal)objValue).floatValue());
                             }
                             else if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_DATE){
                                 strDate = dateFormat.format(objValue);
                                 date    = dateFormat.parse(strDate);
                                 method.invoke(clsInstance, date);
                             }else if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_BOOLEAN){
                                 if(objValue.getClass().toString().endsWith("BigDecimal")){
                                     method.invoke(clsInstance,EtcUtil.convertIntToBoolean(((BigDecimal)objValue).intValue()));
                                 }else{
                                     method.invoke(clsInstance,EtcUtil.convertIntToBoolean(((Integer)objValue).intValue()));
                                 }
                             }else if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_LONG){
                                 method.invoke(clsInstance, ((BigDecimal)objValue).longValue());
                             }else if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_DOUBLE){
                                 method.invoke(clsInstance, ((BigDecimal)objValue).doubleValue());
                             }else if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_BIGDECIMAL){
                                 method.invoke(clsInstance, ((BigDecimal)objValue));
                             }else if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_ATTRIBUTESET){//Not Implemeted
                                 ;
                             }else if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_ARRAY){//Not Implemeted
                                 ;
                             }else if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_FILE){//Not Implemeted
                                 ;
                             }else{
                                 ;
                             }
                        }
                    }else{
                        if(objValue != null){
                            outData.put(orgKey, convertOracleToJavaObject(objValue));
                            if(isKo && orgKey.equals(OmcSystemConstants.OMC_API_CLASS_DISPLYEDKR_MAP)){
                                this_displayedClassNameKr = (String)objValue;
                            }
                        }
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                    LOGGER.warn("Converting fError {}.{}", clsInstance.getClass(), orgKey);
                }
            //}
        }
        try {
            Method mapMethod = clsInstance.getClass().getMethod(OmcSystemConstants.OMC_API_OUTDATA_MTH, HashMap.class);
            if(mapMethod != null) {
                if(isKo && !NullUtil.isNone(this_displayedClassNameKr)) outData.put(OmcSystemConstants.OMC_API_CLASS_DISPLYED_MAP, this_displayedClassNameKr);
                mapMethod.invoke(clsInstance,(HashMap<String, Object>)outData);
            }
        } catch (Exception e) {
            LOGGER.warn("Map Converting Error", e);
        }
        return clsInstance;
    }
    private GroupByResultVO convertMapToObject(Map<?, ?> map){
        GroupByResultVO groupByResultVO = new GroupByResultVO();
        groupByResultVO.setGroupBycount((Long)map.get(OmcSystemConstants.OMC_API_MYBATIS_QUERY_GROUPBY_COUNT));
        HashMap<String, Object> outData = new HashMap<String, Object>();
        Iterator<?> itr = map.keySet().iterator();
        while (itr.hasNext()) {
            String orgKey = (String)itr.next();
            Object objValue;
            try {
                objValue = convertOracleToJavaObject(1,map.get(orgKey));
                outData.put(orgKey, objValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        groupByResultVO.setOutData(outData);
        return groupByResultVO;
    }
    /**
     * 
     *
     * @param columnList
     * @return
     */
    private Map<String,Integer> getAttributeMap(List<ColumnInfo> columnList, Map<String,String> columnMap){
        Map<String,Integer> attributeMap = new HashMap<String,Integer>();
        for(ColumnInfo col : columnList){
            attributeMap.put(col.getAttributeName(), col.getDataType());
            columnMap.put(col.getColumnAlias(), col.getAttributeName());
        }
        attributeMap.put(OmcSystemConstants.OMC_API_dataFindingDirection, 1);
        columnMap.put(OmcSystemConstants.OMC_API_dataFindingDirection, OmcSystemConstants.OMC_API_dataFindingDirection);
        return attributeMap;
    }
    /**
     * 
     *
     * @param map
     * @param clsInstance
     * @param classInfo
     * @param attributeMap
     * @return
     */

    private Map<String,Method> getMethods(String className, Map<?, ?> map, Object clsInstance, ClassInfo classInfo,Map<String,Integer> attributeMap,Map<String,String>  columnMap,Map<String,MethodHandle> methodHandlerMap){
        Iterator<?> itr = map.keySet().iterator();
        Map<String,Method> methodMap  = new HashMap<String,Method>();
        Method[] methods = null;
        try {
            methods = clsInstance.getClass().getMethods();
        }catch(Exception e){
            LOGGER.warn("Map Converting Error", e);
        }    
        MethodHandles.Lookup lookup = MethodHandles.publicLookup();
        MethodHandle mh = null;
        if(!NullUtil.isNone(methods)){
            while (itr.hasNext()) {
                String orgKey = (String)itr.next();
                String handlerKey = className + "." + orgKey;
                String keyAttribute = columnMap.get(orgKey);
                if(keyAttribute != null){
                    Integer dataType = attributeMap.get(keyAttribute);
                    if(dataType != null){
                        String methodString = "set" + keyAttribute.substring(0, 1).toUpperCase() + keyAttribute.substring(1);
                        boolean isFound = false;
                        for(int i = 0; i < methods.length; i++){
                            if(methods[i].getName().equals(methodString)){
                                if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_STRING ||
                                        dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_LONGSTRING ||
                                        dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_USERID){
                                    if(methods[i].getParameterTypes()[0].equals(String.class)) isFound = true;
                                }else if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_INTEGER){
                                    if(methods[i].getParameterTypes()[0].equals(Integer.class)) isFound = true;
                                }else if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_FLOAT){
                                    if(methods[i].getParameterTypes()[0].equals(Float.class)) isFound = true;
                                }else if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_DATE){  
                                    if(methods[i].getParameterTypes()[0].equals(Date.class)) isFound = true;
                                }else if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_BOOLEAN){
                                    if(methods[i].getParameterTypes()[0].equals(Boolean.class)) isFound = true;
                                }else if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_LONG){
                                    if(methods[i].getParameterTypes()[0].equals(Long.class)) isFound = true;
                                }else if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_DOUBLE){
                                    if(methods[i].getParameterTypes()[0].equals(Double.class)) isFound = true;
                                }else if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_BIGDECIMAL){
                                    if(methods[i].getParameterTypes()[0].equals(BigDecimal.class)) isFound = true;
                                }else if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_ATTRIBUTESET){
                                    break;//Not Implemented
                                }else if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_ARRAY){
                                    break;//Not Implemented
                                }else if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_FILE){
                                    break;//Not Implemented
                                }
                                if(isFound){
                                    try {
                                        mh = lookup.unreflect(methods[i]);
                                        methodHandlerMap.put(handlerKey, mh);
                                        methodMap.put(handlerKey,methods[i]);
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return  methodMap;
    }
    @Override
    public Object plugin(Object target){
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties){
        // To change body of implemented methods use File | Settings | File Templates.
    }

    private Object convertOracleToJavaObject(Object objValue) throws Exception{
        Object convertObject = null;
        if (objValue instanceof BigDecimal) {
            convertObject = ((BigDecimal)objValue).intValue();
        } else if (objValue instanceof Timestamp) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(OmcSystemConstants.OMC_JAVA_DATE_FORMAT_DATE);
            String strDate = dateFormat.format(objValue);
            Date date = dateFormat.parse(strDate);
            convertObject = date;
        }else {
            convertObject = objValue;
        }
        return convertObject;
    }
    private Object convertOracleToJavaObject(int dataType, Object objValue) throws Exception{
        Object convertObject = null;
        if (objValue instanceof BigDecimal) {
            convertObject = objValue;
        } else if (objValue instanceof Timestamp) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(OmcSystemConstants.OMC_JAVA_DATE_FORMAT_DATE);
            String strDate = dateFormat.format(objValue);
            Date date = dateFormat.parse(strDate);
            convertObject = date;
        } else {
            convertObject = objValue;
        }
        return convertObject;
    }
}
