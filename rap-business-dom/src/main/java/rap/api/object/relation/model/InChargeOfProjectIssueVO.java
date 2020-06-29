/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : InChargeOfProjectIssueVO.java
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
public class InChargeOfProjectIssueVO extends BusinessRelationObjectVO {
    private String        inChargeType                                      ;


    public void    setInChargeType(String inChargeType){
        this.inChargeType = inChargeType;
    }
    public String getInChargeType(){
        return inChargeType;
    }
}

