/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PCRPropertyVO.java
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
public class PCRPropertyVO extends BusinessObjectMasterVO {
    private String        statusCode                                        ;
    private String        pcoContactEmployee                                ;
    private Integer       reasonCode                                        ;
    private Date          dueDate                                           ;
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
    private Integer       pcrId                                             ;


    public void    setStatusCode(String statusCode){
        this.statusCode = statusCode;
    }
    public void    setPcoContactEmployee(String pcoContactEmployee){
        this.pcoContactEmployee = pcoContactEmployee;
    }
    public void    setReasonCode(Integer reasonCode){
        this.reasonCode = reasonCode;
    }
    public void    setDueDate(Date dueDate){
        this.dueDate = dueDate;
    }
    public void    setDueDate(String    dueDate){
        this.dueDate = this.omcConvertStr2Date(dueDate);
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
    public void    setPcrId(Integer pcrId){
        this.pcrId = pcrId;
    }
    public String getStatusCode(){
        return statusCode;
    }
    public String getPcoContactEmployee(){
        return pcoContactEmployee;
    }
    public Integer getReasonCode(){
        return reasonCode;
    }
    public Date getDueDate(){
        return dueDate;
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
    public Integer getPcrId(){
        return pcrId;
    }
}

