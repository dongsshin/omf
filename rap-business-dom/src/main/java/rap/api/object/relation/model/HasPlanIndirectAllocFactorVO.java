/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasPlanIndirectAllocFactorVO.java
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
public class HasPlanIndirectAllocFactorVO extends BusinessRelationObjectVO {
    private String        versionObid                                       ;
    private Integer       allocNo                                           ;
    private Float         manMonth                                           = (float)0;
    private String        allocYn                                           ;
    private String        notAllocatedYn                                    ;
    private String        allocFactorCode                                   ;


    public void    setVersionObid(String versionObid){
        this.versionObid = versionObid;
    }
    public void    setAllocNo(Integer allocNo){
        this.allocNo = allocNo;
    }
    public void    setManMonth(Float manMonth){
        this.manMonth = manMonth;
    }
    public void    setAllocYn(String allocYn){
        this.allocYn = allocYn;
    }
    public void    setNotAllocatedYn(String notAllocatedYn){
        this.notAllocatedYn = notAllocatedYn;
    }
    public void    setAllocFactorCode(String allocFactorCode){
        this.allocFactorCode = allocFactorCode;
    }
    public String getVersionObid(){
        return versionObid;
    }
    public Integer getAllocNo(){
        return allocNo;
    }
    public Float getManMonth(){
        return manMonth;
    }
    public String getAllocYn(){
        return allocYn;
    }
    public String getNotAllocatedYn(){
        return notAllocatedYn;
    }
    public String getAllocFactorCode(){
        return allocFactorCode;
    }
}

