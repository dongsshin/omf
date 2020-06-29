/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasWeeklyReportProjectVO.java
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
public class HasWeeklyReportProjectVO extends BusinessRelationObjectVO {
    private String        category                                          ;
    private String        projectSignal                                     ;
    private String        content                                           ;
    private String        mmTotal                                           ;
    private String        budgetTotal                                       ;


    public void    setCategory(String category){
        this.category = category;
    }
    public void    setProjectSignal(String projectSignal){
        this.projectSignal = projectSignal;
    }
    public void    setContent(String content){
        this.content = content;
    }
    public void    setMmTotal(String mmTotal){
        this.mmTotal = mmTotal;
    }
    public void    setBudgetTotal(String budgetTotal){
        this.budgetTotal = budgetTotal;
    }
    public String getCategory(){
        return category;
    }
    public String getProjectSignal(){
        return projectSignal;
    }
    public String getContent(){
        return content;
    }
    public String getMmTotal(){
        return mmTotal;
    }
    public String getBudgetTotal(){
        return budgetTotal;
    }
}

