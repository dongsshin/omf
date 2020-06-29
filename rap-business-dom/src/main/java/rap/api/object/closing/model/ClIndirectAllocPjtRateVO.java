/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ClIndirectAllocPjtRateVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.closing.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ClIndirectAllocPjtRateVO extends BusinessObjectMasterVO {
    private String        targetSubsidiary                                  ;
    private String        targetAuCode                                      ;
    private String        targetDeptCode                                    ;
    private String        targetPjtCode                                     ;
    private BigDecimal    distRate                                           = new BigDecimal(0);


    public void    setTargetSubsidiary(String targetSubsidiary){
        this.targetSubsidiary = targetSubsidiary;
    }
    public void    setTargetAuCode(String targetAuCode){
        this.targetAuCode = targetAuCode;
    }
    public void    setTargetDeptCode(String targetDeptCode){
        this.targetDeptCode = targetDeptCode;
    }
    public void    setTargetPjtCode(String targetPjtCode){
        this.targetPjtCode = targetPjtCode;
    }
    public void    setDistRate(BigDecimal distRate){
        this.distRate = distRate;
    }
    public String getTargetSubsidiary(){
        return targetSubsidiary;
    }
    public String getTargetAuCode(){
        return targetAuCode;
    }
    public String getTargetDeptCode(){
        return targetDeptCode;
    }
    public String getTargetPjtCode(){
        return targetPjtCode;
    }
    public BigDecimal getDistRate(){
        return distRate;
    }
}

