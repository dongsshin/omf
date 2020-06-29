/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasPlanBudgetCutAccountVO.java
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
public class HasPlanBudgetCutAccountVO extends BusinessRelationObjectVO {
    private String        accountCode                                       ;
    private String        planYear                                          ;
    private Float         month01Rate                                        = (float)1;
    private Float         month02Rate                                        = (float)1;
    private Float         month03Rate                                        = (float)1;
    private Float         month04Rate                                        = (float)1;
    private Float         month05Rate                                        = (float)1;
    private Float         month06Rate                                        = (float)1;
    private Float         month07Rate                                        = (float)1;
    private Float         month08Rate                                        = (float)1;
    private Float         month09Rate                                        = (float)1;
    private Float         month10Rate                                        = (float)1;
    private Float         month11Rate                                        = (float)1;
    private Float         month12Rate                                        = (float)1;


    public void    setAccountCode(String accountCode){
        this.accountCode = accountCode;
    }
    public void    setPlanYear(String planYear){
        this.planYear = planYear;
    }
    public void    setMonth01Rate(Float month01Rate){
        this.month01Rate = month01Rate;
    }
    public void    setMonth02Rate(Float month02Rate){
        this.month02Rate = month02Rate;
    }
    public void    setMonth03Rate(Float month03Rate){
        this.month03Rate = month03Rate;
    }
    public void    setMonth04Rate(Float month04Rate){
        this.month04Rate = month04Rate;
    }
    public void    setMonth05Rate(Float month05Rate){
        this.month05Rate = month05Rate;
    }
    public void    setMonth06Rate(Float month06Rate){
        this.month06Rate = month06Rate;
    }
    public void    setMonth07Rate(Float month07Rate){
        this.month07Rate = month07Rate;
    }
    public void    setMonth08Rate(Float month08Rate){
        this.month08Rate = month08Rate;
    }
    public void    setMonth09Rate(Float month09Rate){
        this.month09Rate = month09Rate;
    }
    public void    setMonth10Rate(Float month10Rate){
        this.month10Rate = month10Rate;
    }
    public void    setMonth11Rate(Float month11Rate){
        this.month11Rate = month11Rate;
    }
    public void    setMonth12Rate(Float month12Rate){
        this.month12Rate = month12Rate;
    }
    public String getAccountCode(){
        return accountCode;
    }
    public String getPlanYear(){
        return planYear;
    }
    public Float getMonth01Rate(){
        return month01Rate;
    }
    public Float getMonth02Rate(){
        return month02Rate;
    }
    public Float getMonth03Rate(){
        return month03Rate;
    }
    public Float getMonth04Rate(){
        return month04Rate;
    }
    public Float getMonth05Rate(){
        return month05Rate;
    }
    public Float getMonth06Rate(){
        return month06Rate;
    }
    public Float getMonth07Rate(){
        return month07Rate;
    }
    public Float getMonth08Rate(){
        return month08Rate;
    }
    public Float getMonth09Rate(){
        return month09Rate;
    }
    public Float getMonth10Rate(){
        return month10Rate;
    }
    public Float getMonth11Rate(){
        return month11Rate;
    }
    public Float getMonth12Rate(){
        return month12Rate;
    }
}

