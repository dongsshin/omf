/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaDefaultModelSchema.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 1. 31.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaDefaultModelSchema
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class OmcSchemaDefaultModelSchema {
    StringBuffer attributeValueList = new StringBuffer();

    public void makeAttributeValueList(ArrayList<String> attributeNames) {
        try {
            for (String attributeName : attributeNames) {
                String attrName = attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
                Method method = this.getClass().getMethod( "get" + attrName );
                attributeValueList.append(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME
                    + attributeName
                    + OmcSystemConstants.ATTRIBUTE_DELIMINATOR_VALUE
                    + method.invoke ( this, new Object[] {} )
                );
            }
        } catch (Exception e) {
            throw new ApplicationException (e);
        }
    }
    public void makeAttributeValueList(String attributeName) {
        try {
            String attrName = attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
            Method method = this.getClass().getMethod( "get" + attrName );
            attributeValueList.append(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME
                + attributeName
                + OmcSystemConstants.ATTRIBUTE_DELIMINATOR_VALUE
                + method.invoke ( this, new Object[] {} )
            );
        } catch (Exception e) {
            throw new ApplicationException (e);
        }
    }
    public String getAttributeValueList() {
        return attributeValueList
                .substring(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME.length(), attributeValueList.length())
                .toString();
    }
    public Map<String, Object> toMap() {
        Map resultMap = new HashMap();
        try {
            Method[] methods = this.getClass().getDeclaredMethods();
            for (Method method : methods ) {
                String methodName = method.getName();
                if (   methodName.startsWith("get") ) {
                    String key  = methodName.substring(3);
                    key         = key.substring(0, 1).toLowerCase() + key.substring(1);
                    resultMap.put(  key   , method.invoke(this, new Object[] {} ) );
                }
            }
        } catch (Exception e) {
            throw new ApplicationException (e);
        }
        return resultMap;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods ) {
            String methodName = method.getName();
            if (  methodName.startsWith("get") ) {
                String key  = methodName.substring(3);
                key         = key.substring(0, 1).toLowerCase() + key.substring(1);
                try {
                    result.append("\t" +  key  + " : " + (method.invoke(this, new Object[] {} ) ) + "\n");
                } catch (Exception e) {
                    result.append("\t" +  key  + " : " + "Error : " + e.getMessage()  + "\n");
                }
            }
        }
        return result.toString();
    }
}
