/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : SubDepartmentVO.java
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
public class SubDepartmentVO extends BusinessRelationObjectVO {
    private Date          effectiveDateFrom                                 ;
    private Date          effectiveDateTo                                   ;
    private String        useYn                                              = "Y";


    public void    setEffectiveDateFrom(Date effectiveDateFrom){
        this.effectiveDateFrom = effectiveDateFrom;
    }
    public void    setEffectiveDateFrom(String    effectiveDateFrom){
        this.effectiveDateFrom = this.omcConvertStr2Date(effectiveDateFrom);
    }
    public void    setEffectiveDateTo(Date effectiveDateTo){
        this.effectiveDateTo = effectiveDateTo;
    }
    public void    setEffectiveDateTo(String    effectiveDateTo){
        this.effectiveDateTo = this.omcConvertStr2Date(effectiveDateTo);
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public Date getEffectiveDateFrom(){
        return effectiveDateFrom;
    }
    public Date getEffectiveDateTo(){
        return effectiveDateTo;
    }
    public String getUseYn(){
        return useYn;
    }
}

