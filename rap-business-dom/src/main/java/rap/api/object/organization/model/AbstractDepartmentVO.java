/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AbstractDepartmentVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.model;


import rap.api.object.organization.model.OrganizationsVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class AbstractDepartmentVO extends OrganizationsVO {
    private Date          effectiveStartDate                                ;
    private Date          effectiveEndDate                                  ;


    public void    setEffectiveStartDate(Date effectiveStartDate){
        this.effectiveStartDate = effectiveStartDate;
    }
    public void    setEffectiveStartDate(String    effectiveStartDate){
        this.effectiveStartDate = this.omcConvertStr2Date(effectiveStartDate);
    }
    public void    setEffectiveEndDate(Date effectiveEndDate){
        this.effectiveEndDate = effectiveEndDate;
    }
    public void    setEffectiveEndDate(String    effectiveEndDate){
        this.effectiveEndDate = this.omcConvertStr2Date(effectiveEndDate);
    }
    public Date getEffectiveStartDate(){
        return effectiveStartDate;
    }
    public Date getEffectiveEndDate(){
        return effectiveEndDate;
    }
}

