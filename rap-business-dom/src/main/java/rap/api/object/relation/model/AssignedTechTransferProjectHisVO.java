/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AssignedTechTransferProjectHisVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.model;


import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class AssignedTechTransferProjectHisVO extends BusinessRelationObjectVO {
    private String        actionFlag                                        ;
    private String        changeReason                                      ;


    public void    setActionFlag(String actionFlag){
        this.actionFlag = actionFlag;
    }
    public void    setChangeReason(String changeReason){
        this.changeReason = changeReason;
    }
    public String getActionFlag(){
        return actionFlag;
    }
    public String getChangeReason(){
        return changeReason;
    }
}

