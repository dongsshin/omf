/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : CodeDetailSearchVO.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2017. 6. 26. djkim Initial
 * ===========================================
 */
package com.rap.code.model;

import rap.api.object.common.model.CodeDetailVO;

/**
 *
 * <pre>
 * Class : GrandProjectSearchVO
 * Description : TODO
 * </pre>
 *
 * @author dongjo.kim
 */

public class CodeDetailSearchVO extends CodeDetailVO {
    private String codeMasterName;
    private String divisionCode;
    private String itemCode;

    public String getCodeMasterName(){
        return codeMasterName;
    }

    public void setCodeMasterName(String codeMasterName){
        this.codeMasterName = codeMasterName;
    }

    public String getDivisionCode(){
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }

    
    /**
     * 
     * 
     * @return the itemCode
     */
    public String getItemCode(){
        return itemCode;
    }

    
    /**
     * 
     * 
     * @param itemCode the itemCode to set
     */
    public void setItemCode(String itemCode){
        this.itemCode = itemCode;
    }
}
