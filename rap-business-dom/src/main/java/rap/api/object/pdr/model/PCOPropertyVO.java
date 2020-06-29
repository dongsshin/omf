/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PCOPropertyVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pdr.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PCOPropertyVO extends BusinessObjectMasterVO {
    private String        pcoTypeCode                                       ;
    private Integer       reasonCode                                        ;
    private String        approvalRequestEmployee                           ;
    private Date          pcoEndDate                                        ;
    private Date          pcoEffectiveDate                                  ;
    private Integer       pcoGradeCode                                      ;
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
    private Integer       pcoId                                             ;
    private String        rejectFlag                                        ;


    public void    setPcoTypeCode(String pcoTypeCode){
        this.pcoTypeCode = pcoTypeCode;
    }
    public void    setReasonCode(Integer reasonCode){
        this.reasonCode = reasonCode;
    }
    public void    setApprovalRequestEmployee(String approvalRequestEmployee){
        this.approvalRequestEmployee = approvalRequestEmployee;
    }
    public void    setPcoEndDate(Date pcoEndDate){
        this.pcoEndDate = pcoEndDate;
    }
    public void    setPcoEndDate(String    pcoEndDate){
        this.pcoEndDate = this.omcConvertStr2Date(pcoEndDate);
    }
    public void    setPcoEffectiveDate(Date pcoEffectiveDate){
        this.pcoEffectiveDate = pcoEffectiveDate;
    }
    public void    setPcoEffectiveDate(String    pcoEffectiveDate){
        this.pcoEffectiveDate = this.omcConvertStr2Date(pcoEffectiveDate);
    }
    public void    setPcoGradeCode(Integer pcoGradeCode){
        this.pcoGradeCode = pcoGradeCode;
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
    public void    setPcoId(Integer pcoId){
        this.pcoId = pcoId;
    }
    public void    setRejectFlag(String rejectFlag){
        this.rejectFlag = rejectFlag;
    }
    public String getPcoTypeCode(){
        return pcoTypeCode;
    }
    public Integer getReasonCode(){
        return reasonCode;
    }
    public String getApprovalRequestEmployee(){
        return approvalRequestEmployee;
    }
    public Date getPcoEndDate(){
        return pcoEndDate;
    }
    public Date getPcoEffectiveDate(){
        return pcoEffectiveDate;
    }
    public Integer getPcoGradeCode(){
        return pcoGradeCode;
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
    public Integer getPcoId(){
        return pcoId;
    }
    public String getRejectFlag(){
        return rejectFlag;
    }
}

