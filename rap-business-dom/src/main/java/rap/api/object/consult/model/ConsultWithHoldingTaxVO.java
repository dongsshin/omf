/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultWithHoldingTaxVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.consult.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ConsultWithHoldingTaxVO extends BusinessObjectMasterVO {
    private String        withholdingType                                   ;
    private String        residenceType                                     ;
    private String        businessType                                      ;
    private String        nationCode                                        ;
    private String        detailType                                        ;
    private String        nationKorNm                                       ;
    private String        nationEngNm                                       ;
    private BigDecimal    withholdingTaxRate                                 = new BigDecimal(0);
    private BigDecimal    withholdingTax                                     = new BigDecimal(0);
    private BigDecimal    residenceTax                                       = new BigDecimal(0);
    private String        residenceYn                                       ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;


    public void    setWithholdingType(String withholdingType){
        this.withholdingType = withholdingType;
    }
    public void    setResidenceType(String residenceType){
        this.residenceType = residenceType;
    }
    public void    setBusinessType(String businessType){
        this.businessType = businessType;
    }
    public void    setNationCode(String nationCode){
        this.nationCode = nationCode;
    }
    public void    setDetailType(String detailType){
        this.detailType = detailType;
    }
    public void    setNationKorNm(String nationKorNm){
        this.nationKorNm = nationKorNm;
    }
    public void    setNationEngNm(String nationEngNm){
        this.nationEngNm = nationEngNm;
    }
    public void    setWithholdingTaxRate(BigDecimal withholdingTaxRate){
        this.withholdingTaxRate = withholdingTaxRate;
    }
    public void    setWithholdingTax(BigDecimal withholdingTax){
        this.withholdingTax = withholdingTax;
    }
    public void    setResidenceTax(BigDecimal residenceTax){
        this.residenceTax = residenceTax;
    }
    public void    setResidenceYn(String residenceYn){
        this.residenceYn = residenceYn;
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
    public String getWithholdingType(){
        return withholdingType;
    }
    public String getResidenceType(){
        return residenceType;
    }
    public String getBusinessType(){
        return businessType;
    }
    public String getNationCode(){
        return nationCode;
    }
    public String getDetailType(){
        return detailType;
    }
    public String getNationKorNm(){
        return nationKorNm;
    }
    public String getNationEngNm(){
        return nationEngNm;
    }
    public BigDecimal getWithholdingTaxRate(){
        return withholdingTaxRate;
    }
    public BigDecimal getWithholdingTax(){
        return withholdingTax;
    }
    public BigDecimal getResidenceTax(){
        return residenceTax;
    }
    public String getResidenceYn(){
        return residenceYn;
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

