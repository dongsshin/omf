/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : ModelRoot.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 10.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.foundation.common.model;

import com.rap.omc.api.oql.model.schema.OmcOQLRoot;
import com.rap.omc.schema.util.OmcDBMSConstants;

/**
 * <pre>
 * Class : ModelRoot
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class ModelRootVO extends OmcOQLRoot{
    private String dbmsCurrent = OmcDBMSConstants.DBMS_CURRENT;
    
    public String getDbmsCurrent(){
        return dbmsCurrent;
    }
}
