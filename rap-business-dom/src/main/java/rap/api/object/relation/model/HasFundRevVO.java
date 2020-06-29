/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasFundRevVO.java
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
public class HasFundRevVO extends BusinessRelationObjectVO {
    private String        fundingAmount                                      = "N";
    private Float         fundingRate                                        = (float)0;


    public void    setFundingAmount(String fundingAmount){
        this.fundingAmount = fundingAmount;
    }
    public void    setFundingRate(Float fundingRate){
        this.fundingRate = fundingRate;
    }
    public String getFundingAmount(){
        return fundingAmount;
    }
    public Float getFundingRate(){
        return fundingRate;
    }
}

