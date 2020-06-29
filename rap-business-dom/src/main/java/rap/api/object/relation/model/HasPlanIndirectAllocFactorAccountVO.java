/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasPlanIndirectAllocFactorAccountVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.model;


import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class HasPlanIndirectAllocFactorAccountVO extends BusinessRelationObjectVO {
    private String        planProjectCode                                   ;
    private String        accountCode                                       ;
    private Float         allocRate                                          = (float)0;


    public void    setPlanProjectCode(String planProjectCode){
        this.planProjectCode = planProjectCode;
    }
    public void    setAccountCode(String accountCode){
        this.accountCode = accountCode;
    }
    public void    setAllocRate(Float allocRate){
        this.allocRate = allocRate;
    }
    public String getPlanProjectCode(){
        return planProjectCode;
    }
    public String getAccountCode(){
        return accountCode;
    }
    public Float getAllocRate(){
        return allocRate;
    }
}

