/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ECMSExRateVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ECMSExRateVO extends BusinessObjectMasterVO {
    private String        exrateVersion                                     ;
    private String        exrateBeforeVersion                               ;
    private String        exrateDesc                                        ;
    private String        currentYn                                         ;


    public void    setExrateVersion(String exrateVersion){
        this.exrateVersion = exrateVersion;
    }
    public void    setExrateBeforeVersion(String exrateBeforeVersion){
        this.exrateBeforeVersion = exrateBeforeVersion;
    }
    public void    setExrateDesc(String exrateDesc){
        this.exrateDesc = exrateDesc;
    }
    public void    setCurrentYn(String currentYn){
        this.currentYn = currentYn;
    }
    public String getExrateVersion(){
        return exrateVersion;
    }
    public String getExrateBeforeVersion(){
        return exrateBeforeVersion;
    }
    public String getExrateDesc(){
        return exrateDesc;
    }
    public String getCurrentYn(){
        return currentYn;
    }
}

