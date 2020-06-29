/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DevUnitMgtVersionVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.budget.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class DevUnitMgtVersionVO extends BusinessObjectMasterVO {
    private String        version                                           ;
    private String        changeReason                                      ;


    public void    setVersion(String version){
        this.version = version;
    }
    public void    setChangeReason(String changeReason){
        this.changeReason = changeReason;
    }
    public String getVersion(){
        return version;
    }
    public String getChangeReason(){
        return changeReason;
    }
}

