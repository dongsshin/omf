package com.rap.omc.framework.resolver;


import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.annotation.SCRequestDataset;
import com.rap.omc.framework.exception.FoundationBaseException;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.BaseFoundationUtil;
import com.rap.omc.util.NullUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

/**
 * <pre>
 * Class : SmartClientArgumentResolver
 * Description : TODO
 * </pre>
 * 
 * @author jongjung.kwon
 */
public class SmartClientArgumentResolver implements HandlerMethodArgumentResolver {

    //private ObjectMapper objectMapper = new ObjectMapper();
    static final Logger LOGGER = LoggerFactory.getLogger(SmartClientArgumentResolver.class);
    /**
     * 
     * @param parameter
     * @return
     * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#supportsParameter(org.springframework.core.MethodParameter)
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter){
        LOGGER.debug("SmartClientArgumentResolver supportsParameter : " + parameter.getParameterName());
        if (parameter != null) {
            for (Annotation annotation : parameter.getParameterAnnotations()) {
                if (SCRequestDataset.class.isAssignableFrom(annotation.annotationType())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.method.support.ModelAndViewContainer, org.springframework.web.context.request.NativeWebRequest, org.springframework.web.bind.support.WebDataBinderFactory)
     */
    
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception{

        String requestData = webRequest.getParameter(GlobalConstants.SMARTCLIENT_FORM_KEY);
        
        JSONObject obj = JSONObject.fromObject(requestData);
        
        for (Annotation annotation : methodParameter.getParameterAnnotations()) {
          if (annotation.annotationType().equals(SCRequestDataset.class)) {        

              SCRequestDataset requestDataset = (SCRequestDataset)annotation;
              String dataSetId = requestDataset.value();
              
              if (methodParameter.getParameterType() != null && obj != null && methodParameter.getParameterType().equals(List.class)) {
                                  
                  JSONArray dataSetArr = obj.getJSONArray(dataSetId);
                  
                  ParameterizedType pt = (ParameterizedType)methodParameter.getGenericParameterType();
                  Type typeArguments[] = pt.getActualTypeArguments();
                  Type voType=null;
                  Class<?> cls = null;
                  
                  if (typeArguments != null && typeArguments.length > 0) {
                       voType = typeArguments[0];
                       cls = this.getTypeClass(voType);
                       return parseJsonArrayToList(dataSetArr, cls);
                  }
              }else if(methodParameter.getParameterType() != null && obj != null && methodParameter.getParameterType().equals(String.class)){
                  return obj.getString(dataSetId);
              }else if(methodParameter.getParameterType() != null && obj != null && methodParameter.getParameterType().equals(int.class)){
                  return obj.getInt(dataSetId);
              }else if(methodParameter.getParameterType() != null){
                  Class cls = methodParameter.getParameterType();
                  JSONObject dataSetObj = obj.getJSONObject(dataSetId);
                  return parseJsonObjectToVo(dataSetObj, cls);
              }
          }
        }
        return new Object();  
    }

    /**
     * 
     *
     * @param dataSetArr
     * @param cls
     * @return
     * @throws Exception
     */
    private Object parseJsonArrayToList( JSONArray dataSetArr, Class<?> cls)
            throws Exception{
        
          List<Object> list = new ArrayList<Object>();   
          JSONObject jo;
          
          for(int i=0; i<dataSetArr.size(); i++){
              jo = dataSetArr.getJSONObject(i);                     
              list.add(parseJsonObjectToVo(jo, cls));
          }
            
          return list;
    }
    
    /**
    *
    * @param methodParameter
    * @param obj
    * @param dataSetId
    * @return
    * @throws Exception
    */
   @SuppressWarnings("rawtypes")
private Object parseJsonArrayToStringList( JSONArray dataSetArr, Class<?> cls)
           throws Exception{
       
         List<String> list = new ArrayList<String>();   
         JSONObject jo;
         String keyAttribute;
         
         for(int i=0; i<dataSetArr.size(); i++){
             jo = dataSetArr.getJSONObject(i);             
             Iterator itr = jo.keys();
             while(itr.hasNext()){
                 keyAttribute = (String)itr.next();
                 String keyValue = jo.getString(keyAttribute);
                 list.add(keyValue);
             }
         }
           
         return list;
   }
   /**
    * 
    *
    * @param dataSetObj
    * @param cls
    * @return
    * @throws ClassNotFoundException
    */
   private Class<?> makeClass(JSONObject dataSetObj, Class<?> cls) throws ClassNotFoundException{
        Class<?> newCls = cls;
        if(null != dataSetObj.get("className") && null != dataSetObj.get("assignedParamClassFlag")) {
            if(dataSetObj.getBoolean("assignedParamClassFlag")) {
                String className =  dataSetObj.getString("className");
                newCls = Class.forName(BaseFoundationUtil.getPackageInfo(className) + className +"VO");
            }
        }
        return newCls;
    }
    /**
     * 
     *
     * @param dataSetObj
     * @param cls
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    private Object parseJsonObjectToVo(JSONObject dataSetObj, Class cls) throws Exception{
        
        String keyAttribute = null;
        String setMethodString = "set";
        String methodString = null;
        
        Object bean = makeClass(dataSetObj, cls).newInstance();
        Iterator itr = dataSetObj.keys();
        String valueObj  ="";
        Map<String,Object> outData = new HashMap<String,Object>();
        Map<String,Object> originalOutData = new HashMap<String,Object>();
        
        boolean isMapCreated = false;
        while(itr.hasNext()){
            boolean isFound = false;
            keyAttribute = (String)itr.next();
            methodString = setMethodString + keyAttribute.substring(0, 1).toUpperCase() + keyAttribute.substring(1);
            try {
                Method[] methods = bean.getClass().getMethods();
                Object classTypeobj = dataSetObj.get(keyAttribute).getClass();
                for (int i = 0; i <= methods.length - 1; i++) {
                    if(!classTypeobj.equals(JSONNull.class)){
                        if (methodString.equals(methods[i].getName())) {
                            String parameterType = methods[i].getParameterTypes()[0].getName();
                            if("attributeList".equalsIgnoreCase(keyAttribute)){
                                methods[i].invoke(bean, parseJsonArrayToList(dataSetObj.getJSONArray(keyAttribute),  Class.forName("omc.foundation.model.VariableAttribute")));                                
                                isFound = true;break;
                            }else if("partList".equalsIgnoreCase(keyAttribute)){
                              methods[i].invoke(bean, parseJsonArrayToStringList(dataSetObj.getJSONArray(keyAttribute),  Class.forName("java.lang.String")));                                
                              isFound = true;break;
                            }else if(isNumeric(classTypeobj) && (parameterType.endsWith("Integer") || parameterType.equals("int"))){
                                valueObj = dataSetObj.getString(keyAttribute);
                                Integer val = Integer.valueOf(valueObj);
                                methods[i].invoke(bean, val);
                                isFound = true;break;
                            }else if(isNumeric(classTypeobj) && ((parameterType.equals("Long") || parameterType.equals("long")))){
                                valueObj = dataSetObj.getString(keyAttribute);
                                Long val = Long.valueOf(valueObj);
                                methods[i].invoke(bean, val);
                                isFound = true;break;   
                            }else if(isNumeric(classTypeobj) && ((parameterType.endsWith("Float")|| parameterType.equals("float")))){
                                valueObj = dataSetObj.getString(keyAttribute);
                                Float val = Float.valueOf(valueObj.replace(",", "").replace(" ",""));
                                methods[i].invoke(bean, val);
                                isFound = true;break;
                            }else if(isNumeric(classTypeobj)  && ((parameterType.endsWith("Double") || parameterType.equals("double")))){
                                valueObj = dataSetObj.getString(keyAttribute);
                                Double val = Double.valueOf(valueObj.replace(",", "").replace(" ",""));
                                methods[i].invoke(bean, val);
                                isFound = true;break; 
                            }else if(isNumeric(classTypeobj) && (parameterType.endsWith("BigDecimal"))){
                                valueObj = dataSetObj.getString(keyAttribute);
                                BigDecimal val = new BigDecimal(valueObj.replace(",", "").replace(" ",""));
                                methods[i].invoke(bean, val);
                                isFound = true;break;
                            }else if(isBoolean(classTypeobj) && ((parameterType.endsWith("Boolean") || parameterType.equals("boolean")))){
                                valueObj = dataSetObj.getString(keyAttribute);
                                methods[i].invoke(bean, getBooleanValue( parameterType, valueObj));
                                isFound = true;break;
                            }else if(methods[i].getParameterTypes()[0].equals(dataSetObj.get(keyAttribute).getClass())){
                                methods[i].invoke(bean, dataSetObj.get(keyAttribute));
                                isFound = true;break;
                            }
                        }
                    }
                }
                if(keyAttribute.equals(OmcSystemConstants.OMC_API_OUTDATA_NAME)){
                    JSONObject outJsonObject = dataSetObj.getJSONObject(keyAttribute);
                    if( !outJsonObject.isNullObject() ) {
                        Iterator outItr = outJsonObject.keys();
                        while(outItr.hasNext()){
                            String outKeyAttribute = (String)outItr.next();
                            originalOutData.put(outKeyAttribute, outJsonObject.get(outKeyAttribute));
                        }
                    }
                }
                if(!isFound & !classTypeobj.equals(JSONNull.class)){
                    outData.put(keyAttribute, dataSetObj.getString(keyAttribute));
                    isMapCreated = true;
                }
            } catch (SecurityException e) {
                throw new FoundationBaseException("frame.error.resolver.security",e);
            } catch (IllegalAccessException e) {
                throw new FoundationBaseException("frame.error.resolver.illegalAccess",e);
            } catch (IllegalArgumentException e) {
                throw new FoundationBaseException("frame.error.resolver.illegalArgument",e);
            } catch (InvocationTargetException e) {
                throw new FoundationBaseException("frame.error.resolver.invocationTarget",e);
            }            
        }
        if(isMapCreated && bean instanceof ObjectRootVO){
            try {
                for(String key : outData.keySet()){
                    originalOutData.put(key, outData.get(key));
                }
                Method mapMethod = bean.getClass().getMethod(OmcSystemConstants.OMC_API_OUTDATA_MTH, HashMap.class);
                if(mapMethod != null) {
                    mapMethod.invoke(bean,originalOutData);
                }
            } catch (Exception e) {
                LOGGER.warn("Out Data convert Error", e);
            }
        }
        return bean;
    }
    /**
     * 
     *
     * @param type
     * @return
     * @throws Exception
     */
    private Class<?> getTypeClass(Type type) throws Exception{
        String typeString = type.toString();
        String className = typeString.substring(typeString.lastIndexOf(" ") + 1);
        Class<?> cls = Class.forName(className);
        return cls;
    }
    private boolean isNumeric(Object obj){
        if(obj.equals(Integer.class) || 
                obj.equals(Float.class) ||
                obj.equals(Double.class) ||
                obj.equals(BigDecimal.class) ||
                obj.equals(Long.class)) return true;
        return false;
    }
    private boolean getBooleanValue(Object methodParmType, String valueObj){
        if(NullUtil.isNone(valueObj)) return false;
        
        if(methodParmType.equals(Boolean.class) || methodParmType.toString().endsWith("Boolean") || methodParmType.equals(String.class)){
            String val = valueObj.toUpperCase();
            if(val.equals("TRUE") || val.equals("Y") || val.equals("YES") || val.equals("1")){
                return true;
            }
            return false;
        }else if(methodParmType.equals(Integer.class)){
            Integer val = Integer.valueOf(valueObj);
            if(val == 1){
                return true;
            }
            return false;
        }
        return false;
    }
    private boolean isBoolean(Object obj){
        if(obj.equals(String.class) || obj.equals(Integer.class) || obj.equals(Boolean.class)) return true;
        return false;
    }
    
}