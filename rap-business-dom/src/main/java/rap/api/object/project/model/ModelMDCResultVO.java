/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ModelMDCResultVO.java
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
public class ModelMDCResultVO extends BusinessObjectMasterVO {
    private String        bsrModelName                                      ;
    private String        bsrBapUser                                        ;
    private String        bsrIotInfo                                        ;


    public void    setBsrModelName(String bsrModelName){
        this.bsrModelName = bsrModelName;
    }
    public void    setBsrBapUser(String bsrBapUser){
        this.bsrBapUser = bsrBapUser;
    }
    public void    setBsrIotInfo(String bsrIotInfo){
        this.bsrIotInfo = bsrIotInfo;
    }
    public String getBsrModelName(){
        return bsrModelName;
    }
    public String getBsrBapUser(){
        return bsrBapUser;
    }
    public String getBsrIotInfo(){
        return bsrIotInfo;
    }
}

