/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : FundVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.bizplan.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class FundVO extends BusinessObjectMasterVO {
    private String        fundType                                          ;
    private String        fundCompanyCode                                   ;
    private String        fundCode                                          ;
    private String        accountUnitCode                                   ;
    private String        displayYn                                         ;


    public void    setFundType(String fundType){
        this.fundType = fundType;
    }
    public void    setFundCompanyCode(String fundCompanyCode){
        this.fundCompanyCode = fundCompanyCode;
    }
    public void    setFundCode(String fundCode){
        this.fundCode = fundCode;
    }
    public void    setAccountUnitCode(String accountUnitCode){
        this.accountUnitCode = accountUnitCode;
    }
    public void    setDisplayYn(String displayYn){
        this.displayYn = displayYn;
    }
    public String getFundType(){
        return fundType;
    }
    public String getFundCompanyCode(){
        return fundCompanyCode;
    }
    public String getFundCode(){
        return fundCode;
    }
    public String getAccountUnitCode(){
        return accountUnitCode;
    }
    public String getDisplayYn(){
        return displayYn;
    }
}

