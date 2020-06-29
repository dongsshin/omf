/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PIMSModuleVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PIMSModuleVO extends BusinessObjectMasterVO {
    private String        modulePartNo                                      ;
    private String        divisionCode                                      ;
    private String        affiliateCode                                     ;
    private String        moduleClassCode                                   ;
    private String        pimsModule                                        ;


    public void    setModulePartNo(String modulePartNo){
        this.modulePartNo = modulePartNo;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setAffiliateCode(String affiliateCode){
        this.affiliateCode = affiliateCode;
    }
    public void    setModuleClassCode(String moduleClassCode){
        this.moduleClassCode = moduleClassCode;
    }
    public void    setPimsModule(String pimsModule){
        this.pimsModule = pimsModule;
    }
    public String getModulePartNo(){
        return modulePartNo;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getAffiliateCode(){
        return affiliateCode;
    }
    public String getModuleClassCode(){
        return moduleClassCode;
    }
    public String getPimsModule(){
        return pimsModule;
    }
}

