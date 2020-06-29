/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PartDevelopmentRequestVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PartDevelopmentRequestVO extends BusinessObjectMasterVO {
    private String        requestNo                                         ;
    private String        gpdmName                                          ;
    private String        affiliateCode                                     ;
    private String        divisionCode                                      ;
    private String        purchaser                                         ;
    private String        modelSuffix                                       ;
    private String        projectCode                                       ;
    private String        projectName                                       ;
    private String        basePartNo                                        ;
    private Float         expectedEstimatedPrice                            ;
    private String        expectedEstimatedPriceCur                         ;
    private Float         developmentTargetPrice                            ;
    private String        developmentTargetPriceCur                         ;
    private String        attribute8                                        ;
    private String        attribute9                                        ;
    private String        attribute10                                       ;
    private String        partNo                                            ;
    private String        partNoLink1                                       ;
    private String        partNoLink2                                       ;
    private String        partNoLink3                                       ;


    public void    setRequestNo(String requestNo){
        this.requestNo = requestNo;
    }
    public void    setGpdmName(String gpdmName){
        this.gpdmName = gpdmName;
    }
    public void    setAffiliateCode(String affiliateCode){
        this.affiliateCode = affiliateCode;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setPurchaser(String purchaser){
        this.purchaser = purchaser;
    }
    public void    setModelSuffix(String modelSuffix){
        this.modelSuffix = modelSuffix;
    }
    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public void    setProjectName(String projectName){
        this.projectName = projectName;
    }
    public void    setBasePartNo(String basePartNo){
        this.basePartNo = basePartNo;
    }
    public void    setExpectedEstimatedPrice(Float expectedEstimatedPrice){
        this.expectedEstimatedPrice = expectedEstimatedPrice;
    }
    public void    setExpectedEstimatedPriceCur(String expectedEstimatedPriceCur){
        this.expectedEstimatedPriceCur = expectedEstimatedPriceCur;
    }
    public void    setDevelopmentTargetPrice(Float developmentTargetPrice){
        this.developmentTargetPrice = developmentTargetPrice;
    }
    public void    setDevelopmentTargetPriceCur(String developmentTargetPriceCur){
        this.developmentTargetPriceCur = developmentTargetPriceCur;
    }
    public void    setAttribute8(String attribute8){
        this.attribute8 = attribute8;
    }
    public void    setAttribute9(String attribute9){
        this.attribute9 = attribute9;
    }
    public void    setAttribute10(String attribute10){
        this.attribute10 = attribute10;
    }
    public void    setPartNo(String partNo){
        this.partNo = partNo;
    }
    public void    setPartNoLink1(String partNoLink1){
        this.partNoLink1 = partNoLink1;
    }
    public void    setPartNoLink2(String partNoLink2){
        this.partNoLink2 = partNoLink2;
    }
    public void    setPartNoLink3(String partNoLink3){
        this.partNoLink3 = partNoLink3;
    }
    public String getRequestNo(){
        return requestNo;
    }
    public String getGpdmName(){
        return gpdmName;
    }
    public String getAffiliateCode(){
        return affiliateCode;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getPurchaser(){
        return purchaser;
    }
    public String getModelSuffix(){
        return modelSuffix;
    }
    public String getProjectCode(){
        return projectCode;
    }
    public String getProjectName(){
        return projectName;
    }
    public String getBasePartNo(){
        return basePartNo;
    }
    public Float getExpectedEstimatedPrice(){
        return expectedEstimatedPrice;
    }
    public String getExpectedEstimatedPriceCur(){
        return expectedEstimatedPriceCur;
    }
    public Float getDevelopmentTargetPrice(){
        return developmentTargetPrice;
    }
    public String getDevelopmentTargetPriceCur(){
        return developmentTargetPriceCur;
    }
    public String getAttribute8(){
        return attribute8;
    }
    public String getAttribute9(){
        return attribute9;
    }
    public String getAttribute10(){
        return attribute10;
    }
    public String getPartNo(){
        return partNo;
    }
    public String getPartNoLink1(){
        return partNoLink1;
    }
    public String getPartNoLink2(){
        return partNoLink2;
    }
    public String getPartNoLink3(){
        return partNoLink3;
    }
}

