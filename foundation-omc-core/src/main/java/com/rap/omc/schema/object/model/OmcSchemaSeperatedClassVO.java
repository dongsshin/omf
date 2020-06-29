/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaSeperatedClassVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 28.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;

import com.rap.omc.api.object.model.BaseModel;


/**
 * <pre>
 * Class : OmcSchemaSeperatedClassVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaSeperatedClassVO extends BaseModel{
    private String names;

    
    public String getNames(){
        return names;
    }

    
    public void setNames(String names){
        this.names = names;
    }


    /**
     * @param names
     */
    public OmcSchemaSeperatedClassVO(String names) {
        super();
        this.names = names;
    }
    
}
