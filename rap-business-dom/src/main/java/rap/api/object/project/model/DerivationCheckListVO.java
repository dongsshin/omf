/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DerivationCheckListVO.java
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
public class DerivationCheckListVO extends BusinessObjectMasterVO {
    private String        fromObid                                          ;
    private String        approval1st                                       ;
    private String        approval1stYn                                      = "N";
    private String        eventCode                                         ;
    private String        approval1stYyyymmdd                               ;
    private String        approval2nd                                       ;
    private String        approval2ndYn                                      = "N";
    private String        approval2ndYyyymmdd                               ;


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setApproval1st(String approval1st){
        this.approval1st = approval1st;
    }
    public void    setApproval1stYn(String approval1stYn){
        this.approval1stYn = approval1stYn;
    }
    public void    setEventCode(String eventCode){
        this.eventCode = eventCode;
    }
    public void    setApproval1stYyyymmdd(String approval1stYyyymmdd){
        this.approval1stYyyymmdd = approval1stYyyymmdd;
    }
    public void    setApproval2nd(String approval2nd){
        this.approval2nd = approval2nd;
    }
    public void    setApproval2ndYn(String approval2ndYn){
        this.approval2ndYn = approval2ndYn;
    }
    public void    setApproval2ndYyyymmdd(String approval2ndYyyymmdd){
        this.approval2ndYyyymmdd = approval2ndYyyymmdd;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getApproval1st(){
        return approval1st;
    }
    public String getApproval1stYn(){
        return approval1stYn;
    }
    public String getEventCode(){
        return eventCode;
    }
    public String getApproval1stYyyymmdd(){
        return approval1stYyyymmdd;
    }
    public String getApproval2nd(){
        return approval2nd;
    }
    public String getApproval2ndYn(){
        return approval2ndYn;
    }
    public String getApproval2ndYyyymmdd(){
        return approval2ndYyyymmdd;
    }
}

