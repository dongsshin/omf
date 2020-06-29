/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DefinedRoleVO.java
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
public class DefinedRoleVO extends BusinessRelationObjectVO {
    private String        affiliateUnitList                                  = "None";


    public void    setAffiliateUnitList(String affiliateUnitList){
        this.affiliateUnitList = affiliateUnitList;
    }
    public String getAffiliateUnitList(){
        return affiliateUnitList;
    }
}

