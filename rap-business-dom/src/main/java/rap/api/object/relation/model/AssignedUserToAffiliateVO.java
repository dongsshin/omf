/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AssignedUserToAffiliateVO.java
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
public class AssignedUserToAffiliateVO extends BusinessRelationObjectVO {
    private Integer       orderValue                                        ;


    public void    setOrderValue(Integer orderValue){
        this.orderValue = orderValue;
    }
    public Integer getOrderValue(){
        return orderValue;
    }
}

