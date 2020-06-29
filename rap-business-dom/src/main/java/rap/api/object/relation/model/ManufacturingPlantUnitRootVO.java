/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ManufacturingPlantUnitRootVO.java
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
public class ManufacturingPlantUnitRootVO extends BusinessRelationObjectVO {
    private String        partNo                                            ;
    private String        divisionCode                                      ;
    private String        affiliateCode                                     ;


    public void    setPartNo(String partNo){
        this.partNo = partNo;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setAffiliateCode(String affiliateCode){
        this.affiliateCode = affiliateCode;
    }
    public String getPartNo(){
        return partNo;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getAffiliateCode(){
        return affiliateCode;
    }
}

