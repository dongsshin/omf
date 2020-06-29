/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ECOEssentialApproverItemVO.java
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
public class ECOEssentialApproverItemVO extends BusinessObjectMasterVO {
    private String        obidCategory                                      ;
    private String        state                                             ;
    private String        step                                              ;
    private String        userId                                            ;
    private String        requiredAction                                    ;
    private String        approverRole                                      ;
    private Boolean       isEssential                                        = (Boolean)false;


    public void    setObidCategory(String obidCategory){
        this.obidCategory = obidCategory;
    }
    public void    setState(String state){
        this.state = state;
    }
    public void    setStep(String step){
        this.step = step;
    }
    public void    setUserId(String userId){
        this.userId = userId;
    }
    public void    setRequiredAction(String requiredAction){
        this.requiredAction = requiredAction;
    }
    public void    setApproverRole(String approverRole){
        this.approverRole = approverRole;
    }
    public void    setIsEssential(Boolean isEssential){
        this.isEssential = isEssential;
    }
    public String getObidCategory(){
        return obidCategory;
    }
    public String getState(){
        return state;
    }
    public String getStep(){
        return step;
    }
    public String getUserId(){
        return userId;
    }
    public String getRequiredAction(){
        return requiredAction;
    }
    public String getApproverRole(){
        return approverRole;
    }
    public Boolean getIsEssential(){
        return isEssential;
    }
}

