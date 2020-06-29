/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectUserActualMMVO.java
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
public class ProjectUserActualMMVO extends BusinessRelationObjectVO {
    private String        planMonth                                         ;
    private Float         manMonth                                           = (float)0;
    private Float         actualManMonth                                     = (float)0;
    private String        deptCode                                          ;


    public void    setPlanMonth(String planMonth){
        this.planMonth = planMonth;
    }
    public void    setManMonth(Float manMonth){
        this.manMonth = manMonth;
    }
    public void    setActualManMonth(Float actualManMonth){
        this.actualManMonth = actualManMonth;
    }
    public void    setDeptCode(String deptCode){
        this.deptCode = deptCode;
    }
    public String getPlanMonth(){
        return planMonth;
    }
    public Float getManMonth(){
        return manMonth;
    }
    public Float getActualManMonth(){
        return actualManMonth;
    }
    public String getDeptCode(){
        return deptCode;
    }
}

