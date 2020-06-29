/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AbstractAccountDepartmentVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.model;


import rap.api.object.organization.model.AbstractDepartmentVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class AbstractAccountDepartmentVO extends AbstractDepartmentVO {
    private String        departmentCode                                    ;
    private String        accountingUnitCode                                ;
    private String        affiliateCode                                     ;
    private String        legalEntityName                                   ;
    private String        costType                                          ;
    private String        rndFlag                                           ;
    private String        unlockUser                                        ;


    public void    setDepartmentCode(String departmentCode){
        this.departmentCode = departmentCode;
    }
    public void    setAccountingUnitCode(String accountingUnitCode){
        this.accountingUnitCode = accountingUnitCode;
    }
    public void    setAffiliateCode(String affiliateCode){
        this.affiliateCode = affiliateCode;
    }
    public void    setLegalEntityName(String legalEntityName){
        this.legalEntityName = legalEntityName;
    }
    public void    setCostType(String costType){
        this.costType = costType;
    }
    public void    setRndFlag(String rndFlag){
        this.rndFlag = rndFlag;
    }
    public void    setUnlockUser(String unlockUser){
        this.unlockUser = unlockUser;
    }
    public String getDepartmentCode(){
        return departmentCode;
    }
    public String getAccountingUnitCode(){
        return accountingUnitCode;
    }
    public String getAffiliateCode(){
        return affiliateCode;
    }
    public String getLegalEntityName(){
        return legalEntityName;
    }
    public String getCostType(){
        return costType;
    }
    public String getRndFlag(){
        return rndFlag;
    }
    public String getUnlockUser(){
        return unlockUser;
    }
}

