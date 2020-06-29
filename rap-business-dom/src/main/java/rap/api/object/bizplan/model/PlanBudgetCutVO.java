/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PlanBudgetCutVO.java
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
public class PlanBudgetCutVO extends BusinessObjectMasterVO {
    private Integer       sno                                               ;
    private String        versionObid                                       ;


    public void    setSno(Integer sno){
        this.sno = sno;
    }
    public void    setVersionObid(String versionObid){
        this.versionObid = versionObid;
    }
    public Integer getSno(){
        return sno;
    }
    public String getVersionObid(){
        return versionObid;
    }
}

