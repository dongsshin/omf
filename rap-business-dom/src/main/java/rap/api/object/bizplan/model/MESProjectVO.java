/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : MESProjectVO.java
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
public class MESProjectVO extends BusinessObjectMasterVO {
    private String        titlesEng                                         ;
    private String        corporationCode                                   ;
    private String        affiliateCode                                     ;
    private String        accountUnitCode                                   ;
    private String        divisionCode                                      ;
    private String        projectType                                       ;
    private String        totalCurrencyCode                                 ;
    private String        plEmpNo                                           ;
    private String        pjtDeptCode                                       ;
    private String        discardFlag                                       ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;


    public void    setTitlesEng(String titlesEng){
        this.titlesEng = titlesEng;
    }
    public void    setCorporationCode(String corporationCode){
        this.corporationCode = corporationCode;
    }
    public void    setAffiliateCode(String affiliateCode){
        this.affiliateCode = affiliateCode;
    }
    public void    setAccountUnitCode(String accountUnitCode){
        this.accountUnitCode = accountUnitCode;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setProjectType(String projectType){
        this.projectType = projectType;
    }
    public void    setTotalCurrencyCode(String totalCurrencyCode){
        this.totalCurrencyCode = totalCurrencyCode;
    }
    public void    setPlEmpNo(String plEmpNo){
        this.plEmpNo = plEmpNo;
    }
    public void    setPjtDeptCode(String pjtDeptCode){
        this.pjtDeptCode = pjtDeptCode;
    }
    public void    setDiscardFlag(String discardFlag){
        this.discardFlag = discardFlag;
    }
    public void    setAttribute1(String attribute1){
        this.attribute1 = attribute1;
    }
    public void    setAttribute2(String attribute2){
        this.attribute2 = attribute2;
    }
    public void    setAttribute3(String attribute3){
        this.attribute3 = attribute3;
    }
    public void    setAttribute4(String attribute4){
        this.attribute4 = attribute4;
    }
    public void    setAttribute5(String attribute5){
        this.attribute5 = attribute5;
    }
    public String getTitlesEng(){
        return titlesEng;
    }
    public String getCorporationCode(){
        return corporationCode;
    }
    public String getAffiliateCode(){
        return affiliateCode;
    }
    public String getAccountUnitCode(){
        return accountUnitCode;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getProjectType(){
        return projectType;
    }
    public String getTotalCurrencyCode(){
        return totalCurrencyCode;
    }
    public String getPlEmpNo(){
        return plEmpNo;
    }
    public String getPjtDeptCode(){
        return pjtDeptCode;
    }
    public String getDiscardFlag(){
        return discardFlag;
    }
    public String getAttribute1(){
        return attribute1;
    }
    public String getAttribute2(){
        return attribute2;
    }
    public String getAttribute3(){
        return attribute3;
    }
    public String getAttribute4(){
        return attribute4;
    }
    public String getAttribute5(){
        return attribute5;
    }
}

