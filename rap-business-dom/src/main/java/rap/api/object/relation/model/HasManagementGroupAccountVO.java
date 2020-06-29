/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasManagementGroupAccountVO.java
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
public class HasManagementGroupAccountVO extends BusinessRelationObjectVO {
    private String        useFlag                                           ;
    private String        bgtControlType                                    ;
    private String        diverControlType                                  ;
    private String        budgetInputType                                   ;
    private String        budgetInputActivityType                           ;
    private String        bmsIfYn                                           ;
    private String        budgetInputProjectType                            ;
    private String        editableFlag                                       = "Y";


    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public void    setBgtControlType(String bgtControlType){
        this.bgtControlType = bgtControlType;
    }
    public void    setDiverControlType(String diverControlType){
        this.diverControlType = diverControlType;
    }
    public void    setBudgetInputType(String budgetInputType){
        this.budgetInputType = budgetInputType;
    }
    public void    setBudgetInputActivityType(String budgetInputActivityType){
        this.budgetInputActivityType = budgetInputActivityType;
    }
    public void    setBmsIfYn(String bmsIfYn){
        this.bmsIfYn = bmsIfYn;
    }
    public void    setBudgetInputProjectType(String budgetInputProjectType){
        this.budgetInputProjectType = budgetInputProjectType;
    }
    public void    setEditableFlag(String editableFlag){
        this.editableFlag = editableFlag;
    }
    public String getUseFlag(){
        return useFlag;
    }
    public String getBgtControlType(){
        return bgtControlType;
    }
    public String getDiverControlType(){
        return diverControlType;
    }
    public String getBudgetInputType(){
        return budgetInputType;
    }
    public String getBudgetInputActivityType(){
        return budgetInputActivityType;
    }
    public String getBmsIfYn(){
        return bmsIfYn;
    }
    public String getBudgetInputProjectType(){
        return budgetInputProjectType;
    }
    public String getEditableFlag(){
        return editableFlag;
    }
}

