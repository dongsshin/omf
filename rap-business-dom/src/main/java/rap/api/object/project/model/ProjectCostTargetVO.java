/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectCostTargetVO.java
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
public class ProjectCostTargetVO extends BusinessObjectMasterVO {
    private String        fromObid                                          ;
    private String        eventCode                                         ;
    private BigDecimal    osCostTarget                                       = new BigDecimal(0);


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setEventCode(String eventCode){
        this.eventCode = eventCode;
    }
    public void    setOsCostTarget(BigDecimal osCostTarget){
        this.osCostTarget = osCostTarget;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getEventCode(){
        return eventCode;
    }
    public BigDecimal getOsCostTarget(){
        return osCostTarget;
    }
}

