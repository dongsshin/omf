/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasPlanIndirectAllocFactorDeptVO.java
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
public class HasPlanIndirectAllocFactorDeptVO extends BusinessRelationObjectVO {
    private Float         allocRate                                          = (float)0;


    public void    setAllocRate(Float allocRate){
        this.allocRate = allocRate;
    }
    public Float getAllocRate(){
        return allocRate;
    }
}

