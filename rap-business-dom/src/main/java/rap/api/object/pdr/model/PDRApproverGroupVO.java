/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PDRApproverGroupVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pdr.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PDRApproverGroupVO extends BusinessObjectMasterVO {
    private String        managementType                                    ;
    private String        divisionCode                                      ;
    private String        selectItemCode                                    ;
    private String        selectValueCode                                   ;


    public void    setManagementType(String managementType){
        this.managementType = managementType;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setSelectItemCode(String selectItemCode){
        this.selectItemCode = selectItemCode;
    }
    public void    setSelectValueCode(String selectValueCode){
        this.selectValueCode = selectValueCode;
    }
    public String getManagementType(){
        return managementType;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getSelectItemCode(){
        return selectItemCode;
    }
    public String getSelectValueCode(){
        return selectValueCode;
    }
}

