/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PRIPropertyVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pdr.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PRIPropertyVO extends BusinessObjectVO {
    private String        modelName                                         ;
    private String        customerSuffixCode                                ;
    private String        productVarianceCode                               ;
    private String        latestFlag                                        ;
    private String        approvalRequestEmployee                           ;
    private Date          approvalRequestDate                               ;
    private String        transferFlag                                      ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;
    private String        attribute6                                        ;
    private String        attribute7                                        ;
    private String        attribute8                                        ;
    private String        attribute9                                        ;
    private String        attribute10                                       ;
    private String        gpinApproveFlag                                   ;
    private Integer       priId                                             ;
    private Date          approvalDate                                      ;
    private String        divisionCode                                      ;
    private Integer       pdrId                                             ;
    private Integer       act001Delay                                       ;


    public void    setModelName(String modelName){
        this.modelName = modelName;
    }
    public void    setCustomerSuffixCode(String customerSuffixCode){
        this.customerSuffixCode = customerSuffixCode;
    }
    public void    setProductVarianceCode(String productVarianceCode){
        this.productVarianceCode = productVarianceCode;
    }
    public void    setLatestFlag(String latestFlag){
        this.latestFlag = latestFlag;
    }
    public void    setApprovalRequestEmployee(String approvalRequestEmployee){
        this.approvalRequestEmployee = approvalRequestEmployee;
    }
    public void    setApprovalRequestDate(Date approvalRequestDate){
        this.approvalRequestDate = approvalRequestDate;
    }
    public void    setApprovalRequestDate(String    approvalRequestDate){
        this.approvalRequestDate = this.omcConvertStr2Date(approvalRequestDate);
    }
    public void    setTransferFlag(String transferFlag){
        this.transferFlag = transferFlag;
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
    public void    setAttribute6(String attribute6){
        this.attribute6 = attribute6;
    }
    public void    setAttribute7(String attribute7){
        this.attribute7 = attribute7;
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
    public void    setGpinApproveFlag(String gpinApproveFlag){
        this.gpinApproveFlag = gpinApproveFlag;
    }
    public void    setPriId(Integer priId){
        this.priId = priId;
    }
    public void    setApprovalDate(Date approvalDate){
        this.approvalDate = approvalDate;
    }
    public void    setApprovalDate(String    approvalDate){
        this.approvalDate = this.omcConvertStr2Date(approvalDate);
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setPdrId(Integer pdrId){
        this.pdrId = pdrId;
    }
    public void    setAct001Delay(Integer act001Delay){
        this.act001Delay = act001Delay;
    }
    public String getModelName(){
        return modelName;
    }
    public String getCustomerSuffixCode(){
        return customerSuffixCode;
    }
    public String getProductVarianceCode(){
        return productVarianceCode;
    }
    public String getLatestFlag(){
        return latestFlag;
    }
    public String getApprovalRequestEmployee(){
        return approvalRequestEmployee;
    }
    public Date getApprovalRequestDate(){
        return approvalRequestDate;
    }
    public String getTransferFlag(){
        return transferFlag;
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
    public String getAttribute6(){
        return attribute6;
    }
    public String getAttribute7(){
        return attribute7;
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
    public String getGpinApproveFlag(){
        return gpinApproveFlag;
    }
    public Integer getPriId(){
        return priId;
    }
    public Date getApprovalDate(){
        return approvalDate;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public Integer getPdrId(){
        return pdrId;
    }
    public Integer getAct001Delay(){
        return act001Delay;
    }
}

