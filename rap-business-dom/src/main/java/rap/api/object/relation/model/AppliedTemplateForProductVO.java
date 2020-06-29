/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AppliedTemplateForProductVO.java
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
public class AppliedTemplateForProductVO extends BusinessRelationObjectVO {
    private Boolean       isDefault                                          = (Boolean)false;


    public void    setIsDefault(Boolean isDefault){
        this.isDefault = isDefault;
    }
    public Boolean getIsDefault(){
        return isDefault;
    }
}

