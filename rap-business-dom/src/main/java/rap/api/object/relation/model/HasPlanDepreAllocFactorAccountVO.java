/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasPlanDepreAllocFactorAccountVO.java
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
public class HasPlanDepreAllocFactorAccountVO extends BusinessRelationObjectVO {
    private Float         allocRate                                          = (float)0;
    private String        versionObid                                       ;
    private String        currencyCode                                      ;


    public void    setAllocRate(Float allocRate){
        this.allocRate = allocRate;
    }
    public void    setVersionObid(String versionObid){
        this.versionObid = versionObid;
    }
    public void    setCurrencyCode(String currencyCode){
        this.currencyCode = currencyCode;
    }
    public Float getAllocRate(){
        return allocRate;
    }
    public String getVersionObid(){
        return versionObid;
    }
    public String getCurrencyCode(){
        return currencyCode;
    }
}

