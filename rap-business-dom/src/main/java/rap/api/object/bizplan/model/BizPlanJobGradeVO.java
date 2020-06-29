/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BizPlanJobGradeVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.bizplan.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class BizPlanJobGradeVO extends BusinessObjectMasterVO {
    private String        planYear                                          ;
    private String        upperjobgrade                                     ;
    private Integer       promotionYears                                    ;
    private String        promotionFlag                                     ;


    public void    setPlanYear(String planYear){
        this.planYear = planYear;
    }
    public void    setUpperjobgrade(String upperjobgrade){
        this.upperjobgrade = upperjobgrade;
    }
    public void    setPromotionYears(Integer promotionYears){
        this.promotionYears = promotionYears;
    }
    public void    setPromotionFlag(String promotionFlag){
        this.promotionFlag = promotionFlag;
    }
    public String getPlanYear(){
        return planYear;
    }
    public String getUpperjobgrade(){
        return upperjobgrade;
    }
    public Integer getPromotionYears(){
        return promotionYears;
    }
    public String getPromotionFlag(){
        return promotionFlag;
    }
}

