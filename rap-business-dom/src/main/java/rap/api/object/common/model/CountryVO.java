/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : CountryVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.common.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class CountryVO extends BusinessObjectMasterVO {
    private String        regionCode                                        ;
    private String        isoFlag                                           ;
    private String        euFlag                                            ;
    private String        enabledFlag                                       ;


    public void    setRegionCode(String regionCode){
        this.regionCode = regionCode;
    }
    public void    setIsoFlag(String isoFlag){
        this.isoFlag = isoFlag;
    }
    public void    setEuFlag(String euFlag){
        this.euFlag = euFlag;
    }
    public void    setEnabledFlag(String enabledFlag){
        this.enabledFlag = enabledFlag;
    }
    public String getRegionCode(){
        return regionCode;
    }
    public String getIsoFlag(){
        return isoFlag;
    }
    public String getEuFlag(){
        return euFlag;
    }
    public String getEnabledFlag(){
        return enabledFlag;
    }
}

