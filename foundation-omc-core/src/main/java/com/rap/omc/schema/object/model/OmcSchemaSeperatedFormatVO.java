/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaSeperatedFormatVO.java
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
 * Class : OmcSchemaSeperatedFormatVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaSeperatedFormatVO extends BaseModel{
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
    public OmcSchemaSeperatedFormatVO(String names) {
        super();
        this.names = names;
    }
    
}
