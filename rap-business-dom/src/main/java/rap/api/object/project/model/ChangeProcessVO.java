/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ChangeProcessVO.java
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
public class ChangeProcessVO extends BusinessObjectMasterVO {
    private String        requestType                                       ;
    private String        projectChangeItem                                 ;
    private String        requestTitle                                      ;
    private String        requesterEmpNo                                    ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;
    private String        changeprocessReason                               ;
    private String        attribute6                                        ;
    private String        attribute7                                        ;
    private String        attribute8                                        ;
    private String        attribute9                                        ;
    private String        attribute10                                       ;
    private String        bizPlanTransferYn                                  = "N";


    public void    setRequestType(String requestType){
        this.requestType = requestType;
    }
    public void    setProjectChangeItem(String projectChangeItem){
        this.projectChangeItem = projectChangeItem;
    }
    public void    setRequestTitle(String requestTitle){
        this.requestTitle = requestTitle;
    }
    public void    setRequesterEmpNo(String requesterEmpNo){
        this.requesterEmpNo = requesterEmpNo;
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
    public void    setChangeprocessReason(String changeprocessReason){
        this.changeprocessReason = changeprocessReason;
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
    public void    setBizPlanTransferYn(String bizPlanTransferYn){
        this.bizPlanTransferYn = bizPlanTransferYn;
    }
    public String getRequestType(){
        return requestType;
    }
    public String getProjectChangeItem(){
        return projectChangeItem;
    }
    public String getRequestTitle(){
        return requestTitle;
    }
    public String getRequesterEmpNo(){
        return requesterEmpNo;
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
    public String getChangeprocessReason(){
        return changeprocessReason;
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
    public String getBizPlanTransferYn(){
        return bizPlanTransferYn;
    }
}

