/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcOQLRoot.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2016. 7. 25.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.api.oql.model.schema;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.util.PropertyUtil;
import com.rap.omc.dataaccess.paging.model.PagingEntity;




/**
 * <pre>
 * Class : OmcOQLRoot
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcOQLRoot extends PagingEntity{
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtil.class);
    
    public Object getAttribute(String attribute){
        StringBuffer methodBufStr = new StringBuffer();
        methodBufStr.append("get").append(attribute.substring(0, 1).toUpperCase()).append(attribute.substring(1));
        try {
            Method method = this.getClass().getMethod(methodBufStr.toString());
            return(method.invoke(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return(null);
    }
}
