/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasCheckSubPointVO.java
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
public class HasCheckSubPointVO extends BusinessRelationObjectVO {
    private Integer       allottedPoint                                     ;


    public void    setAllottedPoint(Integer allottedPoint){
        this.allottedPoint = allottedPoint;
    }
    public Integer getAllottedPoint(){
        return allottedPoint;
    }
}

