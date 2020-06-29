/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : Projects2B2BVO.java
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
public class Projects2B2BVO extends BusinessRelationObjectVO {
    private Float         developCost                                       ;
    private Float         developMm                                         ;


    public void    setDevelopCost(Float developCost){
        this.developCost = developCost;
    }
    public void    setDevelopMm(Float developMm){
        this.developMm = developMm;
    }
    public Float getDevelopCost(){
        return developCost;
    }
    public Float getDevelopMm(){
        return developMm;
    }
}

