/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcExpressionFunctionUserDefined.java
 * ===========================================
 * Modify Date    Modifier    Description
 * -------------------------------------------
 * 2017. 1. 25.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.util.expression.expr;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.util.NullUtil;


/**
 * <pre>
 * Class : OmcExpressionFunctionUserDefined
 * Description : TODO
 * </pre>
 *
 * @author s_dongsshin
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class OmcUserDefinedFunctionExpression extends OmcFunctionExpression{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcUserDefinedFunctionExpression.class);
    private String programName;
    private String programMethodName;
    private Set<String> argumentList;
    private BusinessObjectRootVO bizVO;
    private BusinessObjectRootVO cotextVO;
    private String menuName;
    
    private Map contextMap;

    public OmcUserDefinedFunctionExpression(String originalExpression,BusinessObjectMasterVO currentUserObj,BusinessObjectRootVO bizObj, BusinessObjectRootVO contextObj, Map contextMap, String menuName) {
        super(originalExpression,currentUserObj);
        if(originalExpression.substring(0,1).equals("!")) {
            originalExpression = originalExpression.substring(1);
            this.setOriginalExpression(originalExpression);
        }
        this.argumentList      = OmcExpressionUtil.getListForPrgramString(originalExpression);
        String programAndMethodName = OmcExpressionUtil.getProgramNMethodName(originalExpression);
        
        int lastIndex          = programAndMethodName.lastIndexOf(".");
        String program         = programAndMethodName.substring(0,lastIndex);
        String method          = programAndMethodName.substring(lastIndex+1);
        this.programName       = program;
        this.programMethodName = method;

        this.bizVO             = bizObj;
        this.cotextVO          = contextObj;
        if(NullUtil.isNone(contextMap)) contextMap = new HashMap<Object,Object>();
        if(this.argumentList != null){
            for(String str : this.argumentList){
                String[] strArray = str.split(":");
                if(strArray.length == 2){
                    contextMap.put(strArray[0], strArray[1]);
                }
            }
        }
        this.contextMap        = contextMap;
        this.menuName          = menuName;
    }
    public Set<String> getArgumentList(){
        return argumentList;
    }

    public BusinessObjectRootVO getBizVO(){
        return bizVO;
    }


    public BusinessObjectRootVO getCotextVO(){
        return cotextVO;
    }


    public String getMenuName(){
        return menuName;
    }


    public Map getContextMap(){
        return contextMap;
    }

    public void setBizVO(BusinessObjectRootVO bizVO){
        this.bizVO = bizVO;
    }


    public void setCotextVO(BusinessObjectRootVO cotextVO){
        this.cotextVO = cotextVO;
    }


    public void setMenuName(String menuName){
        this.menuName = menuName;
    }


    public void setContextMap(Map contextMap){
        this.contextMap = contextMap;
    }


    public String getProgramName(){
        return programName;
    }


    public String getProgramMethodName(){
        return programMethodName;
    }
    public void setProgramName(String programName){
        this.programName = programName;
    }
    public void setProgramMethodName(String programMethodName){
        this.programMethodName = programMethodName;
    }

    public void eval(boolean isNot){
        if(this.execute(isNot)) this.setResolvedExpression("TRUE");
    }
    protected boolean execute(boolean isNot){
        boolean returnValue = false;
        try{
            Object objct = getRunClassForAccess(this.programName);
            Method[] methodArray = objct.getClass().getDeclaredMethods();
            for(int i = 0; i < methodArray.length; i++){
                if(methodArray[i].getName().equals(this.programMethodName))
                {
                    Object obj = methodArray[i].getReturnType();
                    if(!obj.equals(boolean.class)){
                        LOGGER.debug("Object obj:" + obj);
                        throw new FoundationException("[Foundation] Method(" + this.programMethodName + ") return type is not boolean");
                    }
                    returnValue = (boolean)methodArray[i].invoke(objct, null);
                    break;
                }
            }
            //Method method = objct.getClass().getMethod(this.programMethodName, null);
            //returnValue = (Boolean)method.invoke(objct, null);
        } catch (SecurityException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return false;
        } catch (FoundationException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        /*catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new FoundationException("api.object.error.trigger.noSuchMethod",e);
        } */
        if(isNot) return !returnValue;
        return returnValue;
    }
    private Object getRunClassForAccess(String classNames){
        Object runClass = null;
        try {
            Class<?> cls = Class.forName(classNames);
            Constructor constructor =cls.getDeclaredConstructor(BusinessObjectMasterVO.class,BusinessObjectRootVO.class,BusinessObjectRootVO.class,HashMap.class,String.class);
            runClass = constructor.newInstance(this.getCurrentUserObj(),this.getBizVO(),this.getCotextVO(),this.getContextMap(),this.getMenuName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            LOGGER.error(e.getMessage());
            throw new FoundationException("omc.error.getexpression.OmcUserDefinedFunctionExpression", new Object[] {classNames},e);
        }
        return runClass;
    }
}
