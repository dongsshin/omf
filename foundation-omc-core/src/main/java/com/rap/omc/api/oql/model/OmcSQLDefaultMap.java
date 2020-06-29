/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSQLDefaultMap.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2016. 7. 18.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.api.oql.model;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.rap.omc.dataaccess.paging.model.PagingEntity;





/**
 * <pre>
 * Class : OmcSQLDefaultMap
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */

public class OmcSQLDefaultMap extends PagingEntity{
    private String sql;
    

    public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	@SuppressWarnings("unchecked")
    public Map<String, Object> toMap() {
        @SuppressWarnings("rawtypes")
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
            ;
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
    public void setAttribute(String attribute, String value){
        StringBuffer methodBufStr = new StringBuffer();
        methodBufStr.append("set").append(attribute.substring(0, 1).toUpperCase()).append(attribute.substring(1));
        try {
            Method method = this.getClass().getMethod(methodBufStr.toString(), String.class);
            method.invoke(this, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
