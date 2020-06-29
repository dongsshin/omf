/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasEssentialApproverByProductsVO.java
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
public class HasEssentialApproverByProductsVO extends BusinessRelationObjectVO {
    private String        state                                             ;
    private String        step                                              ;
    private String        requiredAction                                    ;
    private String        essentialFlag                                     ;


    public void    setState(String state){
        this.state = state;
    }
    public void    setStep(String step){
        this.step = step;
    }
    public void    setRequiredAction(String requiredAction){
        this.requiredAction = requiredAction;
    }
    public void    setEssentialFlag(String essentialFlag){
        this.essentialFlag = essentialFlag;
    }
    public String getState(){
        return state;
    }
    public String getStep(){
        return step;
    }
    public String getRequiredAction(){
        return requiredAction;
    }
    public String getEssentialFlag(){
        return essentialFlag;
    }
}

