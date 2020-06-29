/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : SubsidiaryForPDRVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pdr.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class SubsidiaryForPDRVO extends BusinessObjectMasterVO {
    private String        productionRegionCode                              ;
    private String        useFlag                                           ;
    private String        subsidiaryCode                                    ;
    private String        subsidiaryName                                    ;
    private String        valueCode                                         ;


    public void    setProductionRegionCode(String productionRegionCode){
        this.productionRegionCode = productionRegionCode;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public void    setSubsidiaryCode(String subsidiaryCode){
        this.subsidiaryCode = subsidiaryCode;
    }
    public void    setSubsidiaryName(String subsidiaryName){
        this.subsidiaryName = subsidiaryName;
    }
    public void    setValueCode(String valueCode){
        this.valueCode = valueCode;
    }
    public String getProductionRegionCode(){
        return productionRegionCode;
    }
    public String getUseFlag(){
        return useFlag;
    }
    public String getSubsidiaryCode(){
        return subsidiaryCode;
    }
    public String getSubsidiaryName(){
        return subsidiaryName;
    }
    public String getValueCode(){
        return valueCode;
    }
}

