/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AppliedProductVO.java
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
public class AppliedProductVO extends BusinessObjectMasterVO {
    private String        companyCode                                       ;
    private String        companyName                                       ;
    private String        divisionCode                                      ;
    private String        divisionName                                      ;
    private String        useFlag                                           ;
    private String        attribute01                                       ;
    private String        attribute02                                       ;
    private String        attribute03                                       ;


    public void    setCompanyCode(String companyCode){
        this.companyCode = companyCode;
    }
    public void    setCompanyName(String companyName){
        this.companyName = companyName;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setDivisionName(String divisionName){
        this.divisionName = divisionName;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public void    setAttribute01(String attribute01){
        this.attribute01 = attribute01;
    }
    public void    setAttribute02(String attribute02){
        this.attribute02 = attribute02;
    }
    public void    setAttribute03(String attribute03){
        this.attribute03 = attribute03;
    }
    public String getCompanyCode(){
        return companyCode;
    }
    public String getCompanyName(){
        return companyName;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getDivisionName(){
        return divisionName;
    }
    public String getUseFlag(){
        return useFlag;
    }
    public String getAttribute01(){
        return attribute01;
    }
    public String getAttribute02(){
        return attribute02;
    }
    public String getAttribute03(){
        return attribute03;
    }
}

