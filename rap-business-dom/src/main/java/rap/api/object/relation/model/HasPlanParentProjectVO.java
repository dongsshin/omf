/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasPlanParentProjectVO.java
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
public class HasPlanParentProjectVO extends BusinessRelationObjectVO {
    private String        parentProjectType                                  = "Parent";


    public void    setParentProjectType(String parentProjectType){
        this.parentProjectType = parentProjectType;
    }
    public String getParentProjectType(){
        return parentProjectType;
    }
}

