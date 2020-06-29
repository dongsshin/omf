/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AccountDivisionVO.java
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
public class AccountDivisionVO extends BusinessObjectMasterVO {
    private String        areaCode                                          ;
    private String        divisionCode                                      ;
    private String        acctDivCode                                       ;
    private String        acctDivName                                       ;
    private String        branchCode                                        ;


    public void    setAreaCode(String areaCode){
        this.areaCode = areaCode;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setAcctDivCode(String acctDivCode){
        this.acctDivCode = acctDivCode;
    }
    public void    setAcctDivName(String acctDivName){
        this.acctDivName = acctDivName;
    }
    public void    setBranchCode(String branchCode){
        this.branchCode = branchCode;
    }
    public String getAreaCode(){
        return areaCode;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getAcctDivCode(){
        return acctDivCode;
    }
    public String getAcctDivName(){
        return acctDivName;
    }
    public String getBranchCode(){
        return branchCode;
    }
}

