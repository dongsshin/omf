/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : EssentialApproverLogVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.workflow.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class EssentialApproverLogVO extends BusinessObjectMasterVO {
    private String        targetUserObid                                    ;
    private String        opType                                            ;
    private String        targetObid                                        ;


    public void    setTargetUserObid(String targetUserObid){
        this.targetUserObid = targetUserObid;
    }
    public void    setOpType(String opType){
        this.opType = opType;
    }
    public void    setTargetObid(String targetObid){
        this.targetObid = targetObid;
    }
    public String getTargetUserObid(){
        return targetUserObid;
    }
    public String getOpType(){
        return opType;
    }
    public String getTargetObid(){
        return targetObid;
    }
}

